package com.forum.areas.log.services;

import com.forum.areas.log.models.service.LogServiceModel;

import java.util.Set;

public interface LoggerService {

    Set<LogServiceModel> findAllLogs();

    void create(LogServiceModel logServiceModel);

    void deleteAll();
}
