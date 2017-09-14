package com.odss.seu.service;

import com.odss.seu.vo.Statistics;


import java.util.Date;
import java.util.List;

public interface StatisticsQueryService
{
    List<Statistics> queryAllStatisticsByYear(Date startTime, Date endTime);

    List<Statistics> queryAllStatisticsByMonth(Date startTime, Date endTime);

    List<Statistics> queryAllStatisticsByDay(Date startTime, Date endTime);

}