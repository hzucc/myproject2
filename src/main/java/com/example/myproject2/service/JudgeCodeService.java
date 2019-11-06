package com.example.myproject2.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface JudgeCodeService {
    public void compilePL() throws IOException, InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException;
    public void runPL() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InterruptedException, NoSuchMethodException, InvocationTargetException, IOException;
}
