/*
 *@author ChenCheng
 *@date 2019/9/28
 */
package com.example.myproject2.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class Problem {
    private int problemId;
    private String problemName;
    private int acceptNumber;
    private int submitNumber;
    private String problemContent;
    private String testDataPath;
    @Autowired
    private TimeLimit timeLimit;
    @Autowired
    private MemoryLimit memoryLimit;
    @Autowired
    private TestData testData;

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public int getAcceptNumber() {
        return acceptNumber;
    }

    public void setAcceptNumber(int acceptNumber) {
        this.acceptNumber = acceptNumber;
    }

    public int getSubmitNumber() {
        return submitNumber;
    }

    public void setSubmitNumber(int submitNumber) {
        this.submitNumber = submitNumber;
    }

    public String getProblemContent() {
        return problemContent;
    }

    public void setProblemContent(String problemContent) {
        this.problemContent = problemContent;
    }

    public String getTestDataPath() {
        return testDataPath;
    }

    public void setTestDataPath(String testDataPath) {
        this.testDataPath = testDataPath;
    }

    public TimeLimit getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(TimeLimit timeLimit) {
        this.timeLimit = timeLimit;
    }

    public MemoryLimit getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(MemoryLimit memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public TestData getTestData() {
        return testData;
    }

    public void setTestData(TestData testData) {
        this.testData = testData;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "problemId=" + problemId +
                ", problemName='" + problemName + '\'' +
                ", acceptNumber=" + acceptNumber +
                ", submitNumber=" + submitNumber +
                ", problemContent='" + problemContent + '\'' +
                ", testDataPath='" + testDataPath + '\'' +
                ", timeLimit=" + timeLimit +
                ", memoryLimit=" + memoryLimit +
                ", testData=" + testData +
                '}';
    }
}
