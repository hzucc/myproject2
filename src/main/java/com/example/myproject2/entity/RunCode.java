/*
 *@author ChenCheng
 *@date 2019/10/9
 */
package com.example.myproject2.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class RunCode {
    private int runCodeId;
    private int submitCodeId;
    private String runCodeFile;
    private String codeType;
    private String testDataPath;
    private int testDataSerial;
    private String status;
    private short runTime;
    private int runMemory;

    public RunCode() {
    }
    public RunCode(int submitCodeId, String runCodeFile, String codeType, String testDataPath, int testDataSerial) {
        this.runCodeId = runCodeId;
        this.submitCodeId = submitCodeId;
        this.runCodeFile = runCodeFile;
        this.codeType = codeType;
        this.testDataPath = testDataPath;
        this.testDataSerial = testDataSerial;
        this.status = status;
        this.runTime = runTime;
        this.runMemory = runMemory;
    }

    public int getRunCodeId() {
        return runCodeId;
    }

    public void setRunCodeId(int runCodeId) {
        this.runCodeId = runCodeId;
    }

    public int getSubmitCodeId() {
        return submitCodeId;
    }

    public void setSubmitCodeId(int submitCodeId) {
        this.submitCodeId = submitCodeId;
    }

    public String getRunCodeFile() {
        return runCodeFile;
    }

    public void setRunCodeFile(String runCodeFile) {
        this.runCodeFile = runCodeFile;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getTestDataPath() {
        return testDataPath;
    }

    public void setTestDataPath(String testDataPath) {
        this.testDataPath = testDataPath;
    }

    public int getTestDataSerial() {
        return testDataSerial;
    }

    public void setTestDataSerial(int testDataSerial) {
        this.testDataSerial = testDataSerial;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public short getRunTime() {
        return runTime;
    }

    public void setRunTime(short runTime) {
        this.runTime = runTime;
    }

    public int getRunMemory() {
        return runMemory;
    }

    public void setRunMemory(int runMemory) {
        this.runMemory = runMemory;
    }

    @Override
    public String toString() {
        return "RunCode{" +
                "runCodeId=" + runCodeId +
                ", submitCodeId=" + submitCodeId +
                ", runCodeFile='" + runCodeFile + '\'' +
                ", codeType='" + codeType + '\'' +
                ", testDataPath='" + testDataPath + '\'' +
                ", testDataSerial=" + testDataSerial +
                ", status='" + status + '\'' +
                ", runTime=" + runTime +
                ", runMemory=" + runMemory +
                '}';
    }
}
