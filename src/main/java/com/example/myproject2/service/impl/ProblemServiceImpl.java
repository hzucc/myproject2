/*
 *@author ChenCheng
 *@date 2019/9/29
 */
package com.example.myproject2.service.impl;

import com.example.myproject2.dao.*;
import com.example.myproject2.entity.MemoryLimit;
import com.example.myproject2.entity.Problem;
import com.example.myproject2.entity.TimeLimit;
import com.example.myproject2.entity.UpdateTestDataMap;
import com.example.myproject2.service.ProblemService;
import com.example.myproject2.util.FileUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

@Service
public class ProblemServiceImpl implements ProblemService {
    @Value("${myproject2.testDataPath}")
    private String testDataPath;
    @Autowired
    private ProblemDao problemDao;
    @Autowired
    private TestDataDao testDataDao;
    @Autowired
    private UpdateTestDataMap updateTestDataMap;
    @Autowired
    private TimeLimitDao timeLimitDao;
    @Autowired
    private MemoryLimitDao memoryLimitDao;
    @Autowired
    private TableCountDao tableCountDao;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public List<Problem> getProlemList(int page, int limit) {
        return problemDao.selectProblemList((page - 1) * limit, limit);
    }

    @Override
    public List<Map<String, Object>> getProblemList(int page, int limit) {
        List<Map<String, Object>> maps = problemDao.selectProblemIdAndProblemNameList((page - 1) * limit, limit);
        return maps;
    }

    @Override
    public Problem getProblemByProblemId(int problemId) {
        Problem problem = problemDao.selectProblemByProblemId(problemId);
        return problem;
    }

    @Transactional
    @Override
    public boolean deleteProblem(int[] problemIds) {
        boolean success = true;
        try {
            if (problemIds != null) {
                for (int problemId : problemIds) {
                    String path = problemDao.selectTestDataPath(problemId);
                    //删除压缩的测试数据.zip
                    if (path != null && path.equals("")) {
                        File file = new File(path);
                        File fileParent = file.getParentFile();
                        if (file.exists()) {
                            file.delete();
                        }
                        //删除解压出来的测试数据
                        if (path.endsWith(".zip")) {
                            if (fileParent.exists()) {
                                FileUtil.delete(fileParent);
                            }
                        }
                    }
                    problemDao.deleteProblem(problemId);
                }
            }
        } catch (Exception e) {
            success = false;
            throw e;
        } finally {
            return success;
        }
    }

    @Transactional
    @Override
    public void updateTestData(int problemId, Part part) throws IOException {
        updateTestDataMap.add(problemId);
        //1.删除旧数据
        String oldTestDataPath = problemDao.selectTestDataPath(problemId);
        if (oldTestDataPath != null) {
            FileUtil.delete(new File(oldTestDataPath));
        }
        testDataDao.deleteTestData(problemId);
        //2.生成新记录
        File workFile = new File(testDataPath + "/problemId_" + problemId);
        workFile.mkdirs();
        String zipFilePath = workFile.getPath() + "/problem_" + problemId + ".zip";
        System.out.println(part.getSize());
        part.write(zipFilePath);
        problemDao.updateTestDataPath(problemId, zipFilePath);

        //3,处理分发样例点
        List<String> testDataPaths = new ArrayList<>();
        List<ZipEntry> inputs = new ArrayList<>();
        Map<String, ZipEntry> outputs = new HashMap<>();
        List<String> test_data_paths = new ArrayList<>();
        ZipFile zipFile = new ZipFile(zipFilePath);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            if (!zipEntry.isDirectory()) {
                String name = zipEntry.getName();
                if (name.endsWith(".in")) {
                    inputs.add(zipEntry);
                } else if (name.endsWith(".out")) {
                    outputs.put(name, zipEntry);
                }
            }
        }
        int count = 1;
        byte[] bytes = new byte[1 << 10];
        for (ZipEntry input : inputs) {
            String name = input.getName();
            String prefixName = name.substring(0, name.lastIndexOf("."));
            if (outputs.containsKey(prefixName + ".out")) {
                ZipEntry output = outputs.get(prefixName + ".out");
                File testWorkFile = new File(workFile.getPath() + "/t" + count);
                testWorkFile.mkdir();
                //test.in
                File inputFile = new File(testWorkFile.getPath() + "/test.in");
                inputFile.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(inputFile);
                InputStream inputStream = zipFile.getInputStream(input);
                int len = -1;
                while ((len = inputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, len);
                }
                fileOutputStream.close();
                inputStream.close();
                //test.out
                File outputFile = new File(testWorkFile.getPath() + "/test.out");
                outputFile.createNewFile();
                fileOutputStream = new FileOutputStream(outputFile);
                inputStream = zipFile.getInputStream(output);
                len = -1;
                while ((len = inputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, len);
                }
                fileOutputStream.close();
                inputStream.close();
                testDataPaths.add(testWorkFile.getPath());
                ++count;
            }
        }
        if (!testDataPaths.isEmpty()) {
            testDataDao.insertTestData(problemId, testDataPaths);

        }
        updateTestDataMap.remove(problemId);
    }

    @Override
    public Map<String, Object> getTestDataMessage(int problemId) {
        Map<String, Object> map = new HashMap<>();
        if (updateTestDataMap.contains(problemId)) {
            map.put("testDataStatus", "正在分发中");
        } else {
            String path = problemDao.selectTestDataPath(problemId);
            if (path == null || path.isEmpty()) {
                map.put("testDataStatus", "暂无");
            } else {
                map.put("testDataStatus", "已上传");
                File file = new File(path);
                map.put("fileSize", file.length() >> 10);
            }
        }
        return map;
    }

    @Override
    public String getTestDataPath(int problemId) {
        String path = problemDao.selectTestDataPath(problemId);
        return path;
    }

    @Transactional
    @Override
    public int updateProblem(Problem problem) {
        int problemId = problem.getProblemId();
        boolean problemExist = problemDao.selectProblemId(problem.getProblemId());
        if (!problemExist) {
            problemDao.insertProblem(problem);
            problemId = problemDao.selectProblemIdByProblemName(problem.getProblemName());
            problem.setProblemId(problemId);
            problemDao.setLimit(problem.getTimeLimit(), problem.getMemoryLimit(), problem.getProblemId());
        } else {
            problemDao.updateProbelm(problem);
        }
        return problemId;
    }

    @Override
    public Map<String, Object> getProblemLimit(int problemId) {
        Map<String, Object> map = new HashMap<>();
        TimeLimit timeLimit = timeLimitDao.selectTimeLimit(problemId);
        MemoryLimit memoryLimit = memoryLimitDao.selectMemoryLimit(problemId);
        map.put("c_cppTimeLimit", timeLimit.getC_cppTimeLimit());
        map.put("c_cppMemoryLimit", memoryLimit.getC_cppMemoryLimit());
        map.put("javaTimeLimit", timeLimit.getJavaTimeLimit());
        map.put("javaMemoryLimit", memoryLimit.getJavaMemoryLimit());
        return map;
    }

    @Override
    public int getProblemCount() {
        int problemCount = tableCountDao.selectTableCount("problem");
        return problemCount;
    }
}
