package com.example.myproject2.judge_util;/*
 *@author ChenCheng
 *@date 2019/9/27
 */

public class RunResult {
    private String result;
    private int runTime;
    private int runMemory;
    private String message;

    @Override
    public String toString() {
        return "RunResult{" +
                "result='" + result + '\'' +
                ", runTime=" + runTime +
                ", runMemory=" + runMemory +
                ", message='" + message + '\'' +
                '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public int getRunMemory() {
        return runMemory;
    }

    public void setRunMemory(int runMemory) {
        this.runMemory = runMemory;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
