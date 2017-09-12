package com.odss.seu.service;

import com.odss.seu.vo.Statistics;


import java.util.Date;

public interface StatisticsQueryService
{
    Statistics queryAllStatisticsByYear(Date startTime, Date endTime);

    Statistics queryAllStatisticsByMonth(Date startTime, Date endTime);

    Statistics queryAllStatisticsByDay(Date startTime, Date endTime);

}