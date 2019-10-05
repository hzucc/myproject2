/*
 *@author ChenCheng
 *@date 2019/10/4
 */
package com.example.myproject2.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class SubmitCodeListPage {
    private int problemId;
    private String problemName;
    private String codeType;
    private String userName;
    private String judgeStatus;

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

    public String getJudgeStatus() {
        return judgeStatus;
    }

    public void setJudgeStatus(String judgeStatus) {
        this.judgeStatus = judgeStatus;
    }

    @Override
    public String toString() {
        return "SubmitCodeListPage{" +
                "problemId=" + problemId +
                ", problemName='" + problemName + '\'' +
                ", codeType='" + codeType + '\'' +
                ", userName='" + userName + '\'' +
                ", judgeStatus='" + judgeStatus + '\'' +
                '}';
    }
}
