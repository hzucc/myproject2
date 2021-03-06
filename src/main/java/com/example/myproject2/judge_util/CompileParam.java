package com.example.myproject2.judge_util;/*
 *@author ChenCheng
 *@date 2019/9/27
 */


import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class CompileParam {
    private String codeType;
    private File compileFile;
    public CompileParam() {
    }

    public CompileParam(String codeType, File compileFile) {
        this.codeType = codeType;
        this.compileFile = compileFile;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public File getCompileFile() {
        return compileFile;
    }

    public void setCompileFile(File compileFile) {
        this.compileFile = compileFile;
    }
}
