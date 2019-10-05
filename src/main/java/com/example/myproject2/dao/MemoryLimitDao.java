/*
 *@author ChenCheng
 *@date 2019/10/4
 */
package com.example.myproject2.dao;

import com.example.myproject2.entity.MemoryLimit;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryLimitDao {
    public MemoryLimit selectMemoryLimit(int problemId);
}
