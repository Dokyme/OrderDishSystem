package com.odss.seu.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.service.StatisticsQueryService;
import com.odss.seu.vo.Statistics;
import com.odss.seu.vo.ViewLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/statistics")
public class StatisticsController {

    private StatisticsQueryService statisticsQueryService;

    @Autowired
    public StatisticsController(StatisticsQueryService statisticsQueryService) {
        this.statisticsQueryService = statisticsQueryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(ViewLevel.Summary.class)
    public List<Statistics> queryStatistics(@RequestParam Integer step, @RequestParam Date startTime, @RequestParam Date endTime) {
        List<Statistics> statisticsList;
        switch (step) {
            case 0:
                statisticsList = statisticsQueryService.queryAllStatisticsByYear(startTime, endTime);
                break;
            case 1:
                statisticsList = statisticsQueryService.queryAllStatisticsByMonth(startTime, endTime);
                break;
            case 2:
                statisticsList = statisticsQueryService.queryAllStatisticsByDay(startTime, endTime);
                break;
            default:
                statisticsList = null;
        }
        return statisticsList;
    }
}









