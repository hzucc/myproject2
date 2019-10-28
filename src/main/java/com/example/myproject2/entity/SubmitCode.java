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
    private Timestamp submit_time;
    private String codeValue;
    private String codeType;
    private short fontSize;
    private String theme;
    private int judgeTestNumber;
    private int testNumber;
    private String judgeStatus;
    private String compileMessage;

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

    public Timestamp getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(Timestamp submit_time) {
        this.submit_time = submit_time;
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

    public String getJudgeStatus() {
        return judgeStatus;
    }

    public void setJudgeStatus(String judgeStatus) {
        this.judgeStatus = judgeStatus;
    }

    public int getJudgeTestNumber() {
        return judgeTestNumber;
    }

    public void setJudgeTestNumber(int judgeTestNumber) {
        this.judgeTestNumber = judgeTestNumber;
    }

    public int getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(int testNumber) {
        this.testNumber = testNumber;
    }

    public String getCompileMessage() {
        return compileMessage;
    }

    public void setCompileMessage(String compileMessage) {
        this.compileMessage = compileMessage;
    }

    @Override
    public String toString() {
        return "SubmitCode{" +
                "submitCodeId=" + submitCodeId +
                ", userId=" + userId +
                ", problemId=" + problemId +
                ", submit_time=" + submit_time +
                ", codeValue='" + codeValue + '\'' +
                ", codeType='" + codeType + '\'' +
                ", fontSize=" + fontSize +
                ", theme='" + theme + '\'' +
                ", judgeTestNumber=" + judgeTestNumber +
                ", testNumber=" + testNumber +
                ", judgeStatus='" + judgeStatus + '\'' +
                ", compileMessage='" + compileMessage + '\'' +
                '}';
    }
}
