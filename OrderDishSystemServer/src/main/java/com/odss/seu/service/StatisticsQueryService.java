package com.odss.seu.service;

import com.odss.seu.vo.Statistics;

import java.util.List;

public interface StatisticsQueryService
{
    List<Statistics> queryAllStatistics(Long startTime, Long endTime,Integer step);
}