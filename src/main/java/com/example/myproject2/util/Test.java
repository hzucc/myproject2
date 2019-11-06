/*
 *@author ChenCheng
 *@date 2019/9/29
 */
package com.example.myproject2.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        final TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>() {{
            put(0, 5);
            put(20, 4);
            put(40, 3);
            put(60, 2);
            put(80, 1);
            put(100, 0);
        }};
        Map.Entry<Integer, Integer> integerIntegerEntry = treeMap.floorEntry(22);
        System.out.println(integerIntegerEntry.getValue());
    }
}
