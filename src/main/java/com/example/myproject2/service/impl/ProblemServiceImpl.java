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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
public class ProblemServiceImpl implements ProblemService {
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

    public ProblemServiceImpl() {
        testDataPath = getClass().getClassLoader().getResource("/").getPath() + "/myproject2_data/testdata";
    }

    @Override
    public List<Problem> getProlemList(int page, int limit) {
        return problemDao.selectProblemList((page - 1) * limit, limit);
    }

    @Override
    public List<Problem> getProblemIdAndProblemNameList(int page, int limit) {
        return problemDao.selectProblemIdAndProblemNameList((page - 1) * limit, limit);
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
                    String path = getClass().getClassLoader().getResource("/") + "/" + problemDao.selectTestDataPath(problemId);
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
            FileUtil.delete(new File(oldTestDataPath).getParentFile());
        }
        testDataDao.deleteTestData(problemId);
        //2.生成新记录
        File workFile = new File(testDataPath + "/problemId_" + problemId);
        workFile.mkdir();
        String zipFilePath = workFile.getPath() + "/problem_" + problemId + ".zip";
        part.write(zipFilePath);

        String test_data_path = zipFilePath.substring(zipFilePath.indexOf("/myproject2_data"));
        problemDao.updateTestDataPath(problemId, test_data_path);

        //3,处理分发样例点
        List<String> testDataPaths = new ArrayList<>();
        List<String> inputs = new ArrayList<>();
        Set<String> outputs = new HashSet<>();

        ZipFile zipFile = new ZipFile(zipFilePath);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            if (!zipEntry.isDirectory()) {
                String name = zipEntry.getName();
                if (name.endsWith(".in")) {
                    inputs.add(name);
                } else if (name.endsWith(".out")) {
                    outputs.add(name);
                }
            }
        }
        int count = 1;
        byte[] bytes = new byte[1 << 10];
        for (String inputName : inputs) {
            String prefixName = inputName.substring(0, inputName.lastIndexOf("."));
            String outputName = prefixName + ".out";
            boolean isPaired_InputAndOutput = outputs.contains(outputName);
            if (isPaired_InputAndOutput) {
                ZipEntry input = zipFile.getEntry(inputName);
                ZipEntry output = zipFile.getEntry(outputName);
                File testWorkFile = new File(workFile.getPath(), "t" + count);
                testWorkFile.mkdir();
                //test.in
                File inputFile = new File(testWorkFile.getPath(), "test.in");
                FileOutputStream fileOutputStream = new FileOutputStream(inputFile);
                InputStream inputStream = zipFile.getInputStream(input);
                int len = -1;
                while ((len = inputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, len);
                }
                fileOutputStream.close();
                inputStream.close();
                //test.out
                File outputFile = new File(testWorkFile.getPath(),  "test.out");
                outputFile.createNewFile();
                fileOutputStream = new FileOutputStream(outputFile);
                inputStream = zipFile.getInputStream(output);
                len = -1;
                while ((len = inputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, len);
                }
                fileOutputStream.close();
                inputStream.close();

                //testDataPaths.add(testWorkFile.getPath());

                String path = testWorkFile.getPath().substring(testWorkFile.getPath().indexOf("/myproject2_data"));
                testDataPaths.add(path);

                ++count;
            }
        }
        if (!testDataPaths.isEmpty()) {
            testDataDao.insertTestData(problemId, testDataPaths);
        }
        updateTestDataMap.remove(problemId);
    }

    @Override
    public Map<String, Object> getTestDataMessage(int problemId) throws IOException {
        Map<String, Object> map = new HashMap<>();
        if (updateTestDataMap.contains(problemId)) {
            map.put("testDataStatus", "正在分发中");
        } else {
            String path = problemDao.selectTestDataPath(problemId);
            if (path == null || path.isEmpty()) {
                map.put("testDataStatus", "暂无");
            } else {
                map.put("testDataStatus", "已上传");
                File file = new File(getClass().getClassLoader().getResource("/").getPath() + path);
                map.put("fileSize", file.length() >> 10);
            }
        }
        return map;
    }

    @Override
    public String getTestDataPath(int problemId) {
        String path = getClass().getClassLoader().getResource("/").getPath() + problemDao.selectTestDataPath(problemId);
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
        } else {
            problemDao.updateProblem(problem);
        }
        TimeLimit timeLimit = problem.getTimeLimit();
        timeLimit.setProblemId(problemId);
        MemoryLimit memoryLimit = problem.getMemoryLimit();
        memoryLimit.setProblemId(problemId);
        memoryLimitDao.updateMemoryLimit(memoryLimit);
        timeLimitDao.updateTimeLimit(timeLimit);
        return problemId;
    }

    @Override
    public List<Object> getProblemLimit(int problemId) {
        List<Object> limits = new ArrayList<>(2);
        TimeLimit timeLimit = timeLimitDao.selectTimeLimit(problemId);
        MemoryLimit memoryLimit = memoryLimitDao.selectMemoryLimit(problemId);
        limits.add(timeLimit);
        limits.add(memoryLimit);
        return limits;
    }

    @Override
    public List<Short> getProblemLimit(int problemId, String codeType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Short> limits = new ArrayList<>(2);
        if (codeType.equals("c") || codeType.equals("c/c++")) {
            codeType = "c_cpp";
        }
        char[] chars = codeType.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        String codeType1 = String.valueOf(chars);
        //获取 timeLimit
        String getTimeLimitMethonName = "get" + codeType1 + "TimeLimit";
        TimeLimit timeLimit = timeLimitDao.selectTimeLimit(problemId);
        Method getTimeLimitMethon = TimeLimit.class.getMethod(getTimeLimitMethonName);
        limits.add((Short) getTimeLimitMethon.invoke(timeLimit));
        //获取 memoryLimit
        String getMemoryLimitMethonName = "get" + codeType1 + "MemoryLimit";
        MemoryLimit memoryLimit = memoryLimitDao.selectMemoryLimit(problemId);
        Method getMemoryLimitMethon = MemoryLimit.class.getMethod(getMemoryLimitMethonName);
        limits.add((Short) getMemoryLimitMethon.invoke(memoryLimit));
        return limits;
    }

    @Override
    public int getProblemCount() {
        int problemCount = tableCountDao.selectTableCount("problem");
        return problemCount;
    }

    @Override
    public Problem getProblem(int problemId) {
        Problem problem = problemDao.selectProblem2ByProblemId(problemId);
        MemoryLimit memoryLimit = memoryLimitDao.selectMemoryLimit(problemId);
        TimeLimit timeLimit = timeLimitDao.selectTimeLimit(problemId);
        problem.setTimeLimit(timeLimit);
        problem.setMemoryLimit(memoryLimit);
        return problem;
    }
}
