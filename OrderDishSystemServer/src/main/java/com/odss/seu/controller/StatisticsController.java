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
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/statistics")
public class StatisticsController {

    private StatisticsQueryService statisticsQueryService;

    @Autowired
    public StatisticsController(StatisticsQueryService statisticsQueryService) {
        this.statisticsQueryService = statisticsQueryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(ViewLevel.Summary.class)
    public List<Statistics> queryStatistics(@RequestParam Integer step, @RequestParam String startTime, @RequestParam String endTime) {
        System.out.println("queryStatistics");
        List<Statistics> statisticsList;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startTime1 = simpleDateFormat.parse(startTime);
            Date endTime1 = simpleDateFormat.parse(endTime);
            switch (step) {
                case 0:
                    statisticsList = statisticsQueryService.queryAllStatisticsByYear(startTime1, endTime1);
                    break;
                case 1:
                    statisticsList = statisticsQueryService.queryAllStatisticsByMonth(startTime1, endTime1);
                    break;
                case 2:
                    statisticsList = statisticsQueryService.queryAllStatisticsByDay(startTime1, endTime1);
                    break;
                default:
                    statisticsList = null;
            }
            return statisticsList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}









