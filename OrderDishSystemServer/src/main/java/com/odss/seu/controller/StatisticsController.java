package com.odss.seu.controller;


import com.odss.seu.service.StatisticsQueryService;
import com.odss.seu.vo.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
@RequestMapping(value="/statistics")
public class StatisticsController {
//    起始时间，结束时间
//    按照年，按照月，按照天

    private StatisticsQueryService statisticsQueryService;

    @Autowired
    public StatisticsController(StatisticsQueryService statisticsQueryService)
    {
        this.statisticsQueryService = statisticsQueryService;
    }


    //    按照年
    @RequestMapping(value = "/byyear", method = RequestMethod.GET)
    public Statistics queryStatisticsByYear(Date startTime, Date endTime) {
        Statistics statistics = statisticsQueryService.queryAllStatisticsByYear(startTime, endTime);
        return statistics;
    }


    //    按照月
    @RequestMapping(value = "/bymonth", method = RequestMethod.GET)
    public Statistics queryStatisticsByMonth(Date startTime, Date endTime) {
        Statistics statistics = statisticsQueryService.queryAllStatisticsByMonth(startTime, endTime);
        return statistics;
    }

    //    按照天
    @RequestMapping(value = "/byday", method = RequestMethod.GET)
    public Statistics queryStatisticsByDay(Date startTime, Date endTime) {
        Statistics statistics = statisticsQueryService.queryAllStatisticsByDay(startTime, endTime);
        return statistics;
    }

}









