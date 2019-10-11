package com.example.myproject2.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface JudgeCode {
    public void getCompilePL() throws IOException, ClassNotFoundException, InterruptedException, InstantiationException, IllegalAccessException;
    public void compilePL(Map<String, String> map) throws IOException, InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException;
    public void getRunPL() throws IllegalAccessException, InstantiationException, ClassNotFoundException, InterruptedException, NoSuchMethodException, IOException, InvocationTargetException;
    public void runPL(Map<String, String> map) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InterruptedException, NoSuchMethodException, InvocationTargetException, IOException;

}
