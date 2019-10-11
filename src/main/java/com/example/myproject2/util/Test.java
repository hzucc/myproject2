/*
 *@author ChenCheng
 *@date 2019/9/29
 */
package com.example.myproject2.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        Process exec = Runtime.getRuntime().exec("docker exec 4d3cc89e8a02 cat Main.i");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        String str = null;
        while (( str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }
        exec.waitFor();
        exec.destroy();

    }
}
