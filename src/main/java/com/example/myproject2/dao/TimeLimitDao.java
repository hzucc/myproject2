/*
 *@author ChenCheng
 *@date 2019/10/3
 */
package com.example.myproject2.dao;

import com.example.myproject2.entity.TimeLimit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeLimitDao {
    public void updateTimeLimit(TimeLimit timeLimit);

    public TimeLimit selectTimeLimit(int problemId);
}
