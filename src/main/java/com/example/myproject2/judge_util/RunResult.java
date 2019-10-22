package com.example.myproject2.judge_util;/*
 *@author ChenCheng
 *@date 2019/9/27
 */

public class RunResult {
    private String result;
    private String runTime;
    private String runMemory;
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

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getRunMemory() {
        return runMemory;
    }

    public void setRunMemory(String runMemory) {
        this.runMemory = runMemory;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
