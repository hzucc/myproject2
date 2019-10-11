/*
 *@author ChenCheng
 *@date 2019/10/10
 */
package com.example.myproject2.judge_util;

import java.util.HashMap;
import java.util.Map;

public class HandleType {
    private static final Map<String, String> typeHandle = new HashMap<String, String>(){{
        put("c", "C");
        put("c/c++", "C_cpp");
        put("java", "Java");
    }};
    private static final Map<String, String> suffixName = new HashMap<String, String>(){{
        put("c", "c");
        put("c/c++", "cpp");
        put("java", "java");
    }};
    public static String typeHandle(String codeType) {
        return typeHandle.get(codeType);
    }
    public static String getSuffixName(String codeType) {
        return suffixName.get(codeType);
    }
}
