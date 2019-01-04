package com.forum.areas.log.models.service;

import java.time.LocalDateTime;

public class LogServiceModel {

    private String user;

    private String operation;

    private String tableName;

    private LocalDateTime modifyingDate;

    public LogServiceModel() {
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public LocalDateTime getModifyingDate() {
        return this.modifyingDate;
    }

    public void setModifyingDate(LocalDateTime modifyingDate) {
        this.modifyingDate = modifyingDate;
    }
}
