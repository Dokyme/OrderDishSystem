package com.odss.seu.controller;


import com.odss.seu.service.StatisticsQueryService;
import com.odss.seu.vo.Statistics;
import com.sun.org.glassfish.external.statistics.Statistic;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/statistics")
public class StatisticsController {

    private StatisticsQueryService sqs;

    @Autowired
    public StatisticsController(StatisticsQueryService sqs) {
        this.sqs = sqs;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Statistics> queryStatistics(@RequestParam String startTime, @RequestParam String endTime, @RequestParam String step) {
        try {
            List<Statistics> statistics = new ArrayList<Statistics>();
            Integer iStep = Integer.parseInt(step);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dStartTime = simpleDateFormat.parse(startTime);
            Date dEndTime = simpleDateFormat.parse(endTime);
            if (iStep == 0) {
                statistics = sqs.queryAllStatistics(dStartTime.getTime(), dEndTime.getTime(), Calendar.YEAR);
            } else if (iStep == 1) {
                statistics = sqs.queryAllStatistics(dStartTime.getTime(), dEndTime.getTime(), Calendar.MONTH);
            } else if (iStep == 2) {
                statistics = sqs.queryAllStatistics(dStartTime.getTime(), dEndTime.getTime(), Calendar.DATE);
            }
            return statistics;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}









