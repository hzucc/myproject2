package com.example.myproject2.judge_util;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public interface JudgeCode {
    //预处理,编译
    public CompileResult compile(CompileParam compileParam) throws ClassNotFoundException, IOException, InterruptedException;

    //运行
    public RunResult run(RunParam runParam) throws IOException, InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}


