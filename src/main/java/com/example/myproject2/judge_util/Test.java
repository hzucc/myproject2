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
        String codeType = "python3";
        JudgeCode judgeCode = new JudgeCode(true);
        CompileParam compileParam = new CompileParam();
        compileParam.setCodeType(codeType);
        compileParam.setCompileFile(new File("/myproject2/compile/t2/Main.py"));
        CompileResult compile = judgeCode.compile(compileParam);
        if (compile.isCompileSuccess()) {
            RunParam runParam = new RunParam();
            runParam.setTimeLimit((short) 2);
            runParam.setMemoryLimit((short) 200);
            runParam.setCodeType(codeType);
            runParam.setInput(new File("/myproject2/testdata/problemId_1041/t1/test.in"));
            runParam.setOutput(new File("/myproject2/testdata/problemId_1041/t1/test.out"));
            runParam.setRunFile(compile.getRunFile());
            RunResult run = judgeCode.run(runParam);
            System.out.println(run.getResult());
        }
    }
}
