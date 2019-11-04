/*
 *@author ChenCheng
 *@date 2019/10/9
 */
package com.example.myproject2.entity;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CompileSuffixMap {
    private static final Map<String, String> map = new HashMap<String, String>() {{
        put("c", ".c");
        put("c/c++", ".cpp");
        put("java", ".java");
        put("go", ".go");
        put("python3", ".py");
    }};
    public String handleType(String codeType) {
        return map.get(codeType);
    }
}
