/*
 *@author ChenCheng
 *@date 2019/10/31
 */
package com.example.myproject2.entity;

import com.example.myproject2.dao.RunCodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

@Component
public class RunPLQueue {
    private Queue<Map<String, String>> runPL = new LinkedList<>();
    @Autowired
    private RunCodeDao runCodeDao;

    public synchronized Map<String, String> pull() {
        Map<String, String> map;
        if (runPL.isEmpty()) {
            List<Map<String, String>> maps = runCodeDao.selectRunCodeList(100);
            runPL.addAll(maps);
        }
        map = runPL.poll();
        if (map != null && !map.isEmpty()) {
            int runCodeId = Integer.valueOf(String.valueOf(map.get("runCodeId")));
            runCodeDao.updateStatus(runCodeId, "running");
        }
        return map;
    }
}
