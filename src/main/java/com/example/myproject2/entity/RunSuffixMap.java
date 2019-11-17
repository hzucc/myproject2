/*
 *@author ChenCheng
 *@date 2019/10/9
 */
package com.example.myproject2.entity;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RunSuffixMap {
    private static final Map<String, String> map = new HashMap<String, String>() {{
        put("c", "");
        put("c_cpp", "");
        put("java", "class");
        put("go", "");
        put("python3", "pyc");
    }};
    public String handleType(String codeType) {
        return map.get(codeType);
    }
}
