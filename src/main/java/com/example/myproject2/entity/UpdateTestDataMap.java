/*
 *@author ChenCheng
 *@date 2019/10/2
 */
package com.example.myproject2.entity;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
/*
* 一个支持同步的集合，
* 存储正在进行【上传上传题目测试样例】任务的对应题目id
* */

@Component
public class UpdateTestDataMap extends ConcurrentSkipListSet<Integer>{
}
