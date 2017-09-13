package com.odss.seu.controller;


import com.odss.seu.service.StatisticsQueryService;
import com.odss.seu.vo.Statistics;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/statistics")
public class StatisticsController {
    //    按照年
    @RequestMapping(value = "/byyear", method = RequestMethod.GET)
    public Statistics queryStatisticsByYear(Integer startTime, Integer endTime) {
//        Statistics statistics = StatisticsQueryService.queryAllStatisticsByYear(startTime, endTime);
//        return statistics;
    }


    //    按照月
    @RequestMapping(value = "/bymonth", method = RequestMethod.GET)
    public Statistics queryStatisticsByMonth(Integer startTime, Integer endTime) {
//        Statistics statistics = StatisticsQueryService.queryAllStatisticsByMonth(startTime, endTime);
//        return statistics;
    }

    //    按照天
    @RequestMapping(value = "/byday", method = RequestMethod.GET)
    public Statistics queryStatisticsByDay(Integer startTime, Integer endTime) {
//        Statistics statistics = StatisticsQueryService.queryAllStatisticsByDay(startTime, endTime);
//        return statistics;
    }
}









