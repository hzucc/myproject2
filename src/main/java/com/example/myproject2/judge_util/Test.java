package com.example.myproject2.judge_util;
/*
 *@author ChenCheng
 *@date 2019/9/28
 */

import java.io.File;

/*
 * codeType:  1.c  2.c/cpp  3.java
 * */
public class Test {
    public static void main(String[] args) throws Exception {
        String codeType = "c/c++";
        JudgeCode judgeCode = new JudgeCode();

        CompileParam compileParam = new CompileParam();
        compileParam.setCompileFile(new File("/myproject2/compile/t2/Main.cpp"));
        compileParam.setCodeType(codeType);
        CompileResult compileResult = judgeCode.compile(compileParam);

        if (compileResult.isCompileSuccess()) {
            RunParam runParam = new RunParam();
            runParam.setInput(new File("/myproject2/testdata/problemId_1041/t1/test.in"));
            runParam.setOutput(new File("/myproject2/testdata/problemId_1041/t1/test.out"));
            runParam.setRunFile(compileResult.getRunFile());
            runParam.setCodeType(codeType);
            runParam.setTimeLimit(2);
            runParam.setMemoryLimit(400);
            RunResult runResult = judgeCode.run(runParam);
            System.out.println(runResult);
        } else {
            System.out.println(compileResult.getErrorMessage());
        }
    }
}
