/*
 *@author ChenCheng
 *@date 2019/11/6
 */
package com.example.myproject2.dao.SubmitCodeDaoResult;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Scope(scopeName = "prototype")
public class SubmitCode1 {
    private int problemId;
    private String problemName;
    private String codeType;
    private String userName;
    private String status;
    private Timestamp submitTime;
    private short judgeTestNum;
    private short testNum;

    @Override
    public String toString() {
        return "SubmitCode1{" +
                "problemId=" + problemId +
                ", problemName='" + problemName + '\'' +
                ", codeType='" + codeType + '\'' +
                ", userName='" + userName + '\'' +
                ", status='" + status + '\'' +
                ", submitTime=" + submitTime +
                ", judgeTestNum=" + judgeTestNum +
                ", testNum=" + testNum +
                '}';
    }

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

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    public short getJudgeTestNum() {
        return judgeTestNum;
    }

    public void setJudgeTestNum(short judgeTestNum) {
        this.judgeTestNum = judgeTestNum;
    }

    public short getTestNum() {
        return testNum;
    }

    public void setTestNum(short testNum) {
        this.testNum = testNum;
    }
}
