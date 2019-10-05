/*
 *@author ChenCheng
 *@date 2019/10/5
 */
package com.example.myproject2.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class TableCount {
    private int tableCountId;
    private String tableName;
    private int tableNumber;

    @Override
    public String toString() {
        return "TableCount{" +
                "tableCountId=" + tableCountId +
                ", tableName='" + tableName + '\'' +
                ", tableNumber=" + tableNumber +
                '}';
    }

    public int getTableCountId() {
        return tableCountId;
    }

    public void setTableCountId(int tableCountId) {
        this.tableCountId = tableCountId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
