/*
 *@author ChenCheng
 *@date 2019/9/30
 */
package com.example.myproject2.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class TimeLimit {
    private int timeLimitId;
    private int problemId;
    private short c_cppTimeLimit;
    private short javaTimeLimit;
    private short goTimeLimit;
    private short python3TimeLimit;

    @Override
    public String toString() {
        return "TimeLimit{" +
                "timeLimitId=" + timeLimitId +
                ", problemId=" + problemId +
                ", c_cppTimeLimit=" + c_cppTimeLimit +
                ", javaTimeLimit=" + javaTimeLimit +
                ", goTimeLimit=" + goTimeLimit +
                ", python3TimeLimit=" + python3TimeLimit +
                '}';
    }

    public short getGoTimeLimit() {
        return goTimeLimit;
    }

    public void setGoTimeLimit(short goTimeLimit) {
        this.goTimeLimit = goTimeLimit;
    }

    public short getPython3TimeLimit() {
        return python3TimeLimit;
    }

    public void setPython3TimeLimit(short python3TimeLimit) {
        this.python3TimeLimit = python3TimeLimit;
    }

    public int getTimeLimitId() {
        return timeLimitId;
    }

    public void setTimeLimitId(int timeLimitId) {
        this.timeLimitId = timeLimitId;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public short getC_cppTimeLimit() {
        return c_cppTimeLimit;
    }

    public void setC_cppTimeLimit(short c_cppTimeLimit) {
        this.c_cppTimeLimit = c_cppTimeLimit;
    }

    public short getJavaTimeLimit() {
        return javaTimeLimit;
    }

    public void setJavaTimeLimit(short javaTimeLimit) {
        this.javaTimeLimit = javaTimeLimit;
    }

}
