/*
 *@author ChenCheng
 *@date 2019/10/4
 */
package com.example.myproject2.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Scope(scopeName = "prototype")
public class SubmitCode {
    private int submitCodeId;
    private int userId;
    private int problemId;
    private Timestamp submitTime;
    private String codeValue;
    private String codeType;
    private short fontSize;
    private String theme;
    private short judgeTestNum;
    private short testNum;
    private String status;
    private String compileMessage;

    @Override
    public String toString() {
        return "SubmitCode{" +
                "submitCodeId=" + submitCodeId +
                ", userId=" + userId +
                ", problemId=" + problemId +
                ", submitTime=" + submitTime +
                ", codeValue='" + codeValue + '\'' +
                ", codeType='" + codeType + '\'' +
                ", fontSize=" + fontSize +
                ", theme='" + theme + '\'' +
                ", judgeTestNum=" + judgeTestNum +
                ", testNum=" + testNum +
                ", status='" + status + '\'' +
                ", compileMessage='" + compileMessage + '\'' +
                '}';
    }

    public int getSubmitCodeId() {
        return submitCodeId;
    }

    public void setSubmitCodeId(int submitCodeId) {
        this.submitCodeId = submitCodeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public short getFontSize() {
        return fontSize;
    }

    public void setFontSize(short fontSize) {
        this.fontSize = fontSize;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompileMessage() {
        return compileMessage;
    }

    public void setCompileMessage(String compileMessage) {
        this.compileMessage = compileMessage;
    }
}
