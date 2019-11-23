/*
 *@author ChenCheng
 *@date 2019/9/29
 */
package com.example.myproject2.util;

import java.lang.reflect.Method;
public class Test {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException {
        System.out.println(A.getS());
    }
}
class A{
    private static int s = 1;
    static {
        if (s == 1) {
            s = 2;
        }
    }

    public static int getS() {
        return s;
    }

    public static void setS(int s) {
        A.s = s;
    }
}