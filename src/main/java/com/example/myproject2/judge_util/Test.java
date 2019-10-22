package com.example.myproject2.judge_util;/*
 *@author ChenCheng
 *@date 2019/9/28
 */

import java.io.File;
import java.util.Scanner;
/*
 * codeType:  1.c  2.c/cpp  3.java
 *
 * */
public class Test {
    public static void main(String[] args) throws Exception {
        String codeType = "java";
        JudgeCode judgeCode = (JudgeCode) Class.forName("com.example.myproject2.judge_util.JudgeCode_" + HandleType.typeHandle(codeType)).newInstance();
        CompileParam compileParam = new CompileParam();
        compileParam.setCompileFile(new File("/home/cc/Main.java"));
        compileParam.setCodeType(codeType);
        CompileResult compileResult = judgeCode.compile(compileParam);
        if (compileResult.isCompileSuccess()) {
            RunParam runParam = new RunParam();
            runParam.setInput(new File("/home/cc/test.in"));
            runParam.setOutput(new File("/home/cc/test.out"));
            runParam.setRunFile(compileResult.getRunFile());
            runParam.setCodeType(codeType);
            runParam.setTimeLimit(2);
            runParam.setMemoryLimit(200);
            RunResult runResult = judgeCode.run(runParam);
            System.out.println(runResult);
        } else {
            System.out.println(compileResult.getErrorMessage());
        }
    }
}
