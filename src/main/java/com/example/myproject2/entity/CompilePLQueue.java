/*
 *@author ChenCheng
 *@date 2019/10/31
 */
package com.example.myproject2.entity;

import com.example.myproject2.dao.SubmitCodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CompilePLQueue {
    private Queue<Map<String, String>> compilePL = new LinkedList<>();
    @Autowired
    private SubmitCodeDao submitCodeDao;
    public synchronized Map<String, String> pull() {
        Map<String, String> map;
        if (compilePL.isEmpty()) {
            List<Map<String, String>> maps = submitCodeDao.selectSubmitCodeInWaitingList(50);
            compilePL.addAll(maps);
        }
        map = compilePL.poll();
        if (map != null && !map.isEmpty()) {
            int submitCodeId = Integer.valueOf(String.valueOf(map.get("submitCodeId")));
            submitCodeDao.updateStatus(submitCodeId, "compiling");
        }
        return map;
    }
}
