/*
 *@author ChenCheng
 *@date 2019/10/4
 */
package com.example.myproject2.service.impl;

import com.example.myproject2.dao.SubmitCodeDao;
import com.example.myproject2.dao.TableCountDao;
import com.example.myproject2.entity.CompileSuffixMap;
import com.example.myproject2.entity.SubmitCode;
import com.example.myproject2.entity.SubmitCodeListPage;
import com.example.myproject2.service.SubmitCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class SubmitCodeServiceImpl implements SubmitCodeService {
    @Value("${myproject2.compile}")
    private String compileFilepath;
    @Autowired
    private SubmitCodeDao submitCodeDao;
    @Autowired
    private TableCountDao tableCountDao;
    @Autowired
    private CompileSuffixMap compileSuffixMap;
    @Override
    public void addSubmitCode(SubmitCode submitCode) throws IOException {
        String codeValue = submitCode.getCodeValue();
        String compileSuffixName = compileSuffixMap.handleType(submitCode.getCodeType());
        File workFile = new File(compileFilepath, UUID.randomUUID().toString());
        workFile.mkdir();
        File compileFile = new File(workFile.getPath(), "Main" + compileSuffixName);
        compileFile.createNewFile();

        //File compileFile = File.createTempFile("Main", compileSuffixName, new File(compileFilepath));
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(compileFile)));
        printWriter.write(codeValue);
        printWriter.close();
        submitCode.setCodeValue(compileFile.getPath());
        submitCodeDao.insertSubmitCode(submitCode);
    }

    @Override
    public List<Map<String, String>> getSubmitCodeList(int page, int limit) {
        List<Map<String, String>> submitCodeListPages = submitCodeDao.selectSubmitCodeList((page - 1) * limit, limit);
        return submitCodeListPages;
    }

    @Override
    public int getSubmitCodeCount() {
        return tableCountDao.selectTableCount("submit_code");
    }

}
