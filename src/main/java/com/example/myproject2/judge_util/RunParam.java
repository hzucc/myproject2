package com.example.myproject2.judge_util;/*
 *@author ChenCheng
 *@date 2019/9/27
 */

import java.io.File;

public class RunParam {
    private String codeType;
    private File runFile;
    private File input;
    private File output;
    private short timeLimit;
    private short memoryLimit;

    public RunParam() {
    }

    public RunParam(String codeType, File runFile, File input, File output, short timeLimit, short memoryLimit) {
        this.codeType = codeType;
        this.runFile = runFile;
        this.input = input;
        this.output = output;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public File getRunFile() {
        return runFile;
    }

    public void setRunFile(File runFile) {
        this.runFile = runFile;
    }

    public File getInput() {
        return input;
    }

    public void setInput(File input) {
        this.input = input;
    }

    public File getOutput() {
        return output;
    }

    public void setOutput(File output) {
        this.output = output;
    }

    public short getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(short timeLimit) {
        this.timeLimit = timeLimit;
    }

    public short getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(short memoryLimit) {
        this.memoryLimit = memoryLimit;
    }
}
