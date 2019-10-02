/*
 *@author ChenCheng
 *@date 2019/9/30
 */
package com.example.myproject2.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class MemoryLimit {
    private int memoryLimitId;
    private int problemId;
    private int c_cppMemoryLimit;
    private int javaMemoryLimit;

    public int getMemoryLimitId() {
        return memoryLimitId;
    }

    public void setMemoryLimitId(int memoryLimitId) {
        this.memoryLimitId = memoryLimitId;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public int getC_cppMemoryLimit() {
        return c_cppMemoryLimit;
    }

    public void setC_cppMemoryLimit(int c_cppMemoryLimit) {
        this.c_cppMemoryLimit = c_cppMemoryLimit;
    }

    public int getJavaMemoryLimit() {
        return javaMemoryLimit;
    }

    public void setJavaMemoryLimit(int javaMemoryLimit) {
        this.javaMemoryLimit = javaMemoryLimit;
    }

    @Override
    public String toString() {
        return "MemoryLimit{" +
                "memoryLimitId=" + memoryLimitId +
                ", problemId=" + problemId +
                ", c_cppMemoryLimit=" + c_cppMemoryLimit +
                ", javaMemoryLimit=" + javaMemoryLimit +
                '}';
    }
}
