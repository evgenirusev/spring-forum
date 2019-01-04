package com.forum.comparators;


import com.forum.areas.log.models.service.LogServiceModel;

import java.util.Comparator;

public class LoggerComparatorByDate implements Comparator<LogServiceModel> {
    @Override
    public int compare(LogServiceModel log1, LogServiceModel log2) {
        return log1.getModifyingDate().compareTo(log2.getModifyingDate());
    }
}
