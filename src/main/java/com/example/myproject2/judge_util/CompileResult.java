package com.example.myproject2.judge_util;/*
 *@author ChenCheng
 *@date 2019/9/27
 */

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;


public class CompileResult {
    private boolean compileSuccess;
    private String errorMessage;
    private File runFile;

    public boolean isCompileSuccess() {
        return compileSuccess;
    }

    public void setCompileSuccess(boolean compileSuccess) {
        this.compileSuccess = compileSuccess;
    }

    public File getRunFile() {
        return runFile;
    }

    public void setRunFile(File runFile) {
        this.runFile = runFile;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
