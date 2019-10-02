/*
 *@author ChenCheng
 *@date 2019/9/30
 */
package com.example.myproject2.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class TestData {
    private int testDataId;
    private int problemId;
    private short testDataOrderNumber;
    private int dataPath;

    public int getTestDataId() {
        return testDataId;
    }

    public void setTestDataId(int testDataId) {
        this.testDataId = testDataId;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public int getDataPath() {
        return dataPath;
    }

    public void setDataPath(int dataPath) {
        this.dataPath = dataPath;
    }

    public short getTestDataOrderNumber() {
        return testDataOrderNumber;
    }

    public void setTestDataOrderNumber(short testDataOrderNumber) {
        this.testDataOrderNumber = testDataOrderNumber;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "testDataId=" + testDataId +
                ", problemId=" + problemId +
                ", testDataOrderNumber=" + testDataOrderNumber +
                ", dataPath=" + dataPath +
                '}';
    }
}
