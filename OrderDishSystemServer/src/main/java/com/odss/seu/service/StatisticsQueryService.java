package com.odss.seu.service;

import com.odss.seu.vo.Statistics;

import java.util.List;

public interface StatisticsQueryService
{

//    public List<Statistics> queryAllStatistics(Long istartTime, Long iendTime, Integer step);

    List<Statistics> queryAllStatisticsByDay(Integer startTime, Integer endTime);

    List<Statistics> queryAllStatisticsByYear(Integer startTime, Integer endTime);

    List<Statistics> queryAllStatisticsByMonth(Integer startTime, Integer endTime);

}