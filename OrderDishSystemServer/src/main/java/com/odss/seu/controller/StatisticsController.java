package com.odss.seu.controller;


import com.odss.seu.service.StatisticsQueryService;
import com.odss.seu.vo.Statistics;
import com.sun.org.glassfish.external.statistics.Statistic;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
<<<<<<< HEAD

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
=======
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Year;
>>>>>>> 893a1017823142782e5ebc69948d91236890753a
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

<<<<<<< HEAD
@Controller
@RequestMapping(value="/statistics")
public class StatisticsController {
//    起始时间，结束时间
//    按照年，按照月，按照天
=======
@RestController
@RequestMapping(value = "/statistics")
public class StatisticsController {
>>>>>>> 893a1017823142782e5ebc69948d91236890753a

    private StatisticsQueryService sqs;

    @Autowired
<<<<<<< HEAD
    public StatisticsController(StatisticsQueryService statisticsqueryService) {
        this.sqs = statisticsqueryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Statistics> queryStatistics(String StartTime, String EndTime, String Step) {
//        Integer step = Integer.parseInt(Step);
//        Long istartTime = Long.parseLong(StartTime);
//        Long iendTime = Long.parseLong(EndTime);
//        List<Statistics> sst = sqs.queryAllStatistics(istartTime, iendTime, step);
//        return sst;
//
//    }
//}
        System.out.println("get there_the statistics conller");
        List<Statistics> statistics=new ArrayList<Statistics>();
        System.out.println("after get the new listtttttttttttttttttttttttttttttttttttttttttttttttt");
        Calendar calendar=Calendar.getInstance();//先将data转为calendar
//        calendar.set(2017,9,3);
//        Date date=calendar.getTime();
//        statistics.setstartTime(date);
//        statistics.setendTime(date);
//        statistics.setIncome(666666);
//        statistics.setSellNum(6);
//        statistics.setFantai(2);
        System.out.println("--------------------starttime----"+StartTime+"------------------------------------------");
        System.out.println("--------------------endtime----"+EndTime+"------------------------------------------");
        System.out.println("----------------------------"+Step+"------------------------------------------");
        System.out.println("before chang the form of the steppppppppppppppppppppppppppppppppppppppppppppppppppp");
        Integer step=Integer.parseInt(Step);
        System.out.println("chang the form of the steppppppppppppppppppppppppppppppppppppppppppppppppppp");
        Integer startTime=Integer.parseInt(StartTime);
        Integer endTime=Integer.parseInt(EndTime);

        if(step.equals(0)) {
//            return statistics;
            statistics = queryStatisticsByYear(startTime, endTime);
        }
        else if(step.equals(1))
        {
//            return statistics;
            statistics = queryStatisticsByMonth(startTime, endTime);
        }
        else if(step.equals(2))
        {
            System.out.println("get to know the step is 222222222222222222222222222222222222222222222222222222222");
//            return statistics;
            statistics = queryStatisticsByMonth(startTime, endTime);
        }
//        return statistics;
        return statistics;
    }

//
//    public static Date getDate(Integer datetime) {
//
//        Date dateTime = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String dateString = formatter.format(currentTime);
//        ParsePosition pos = new ParsePosition(8);
//        Date currentTime_2 = formatter.parse(dateString, pos);
//        return currentTime_2;
//    }
////
    //    按照年
    public List<Statistics> queryStatisticsByYear(Integer startTime, Integer endTime) {
        List<Statistics> statisticss =this.sqs.queryAllStatisticsByYear(startTime, endTime);
        return statisticss;
    }

    //    按照月
//    @RequestMapping(value = "/bymonth", method = RequestMethod.GET)
    public List<Statistics> queryStatisticsByMonth(Integer startTime, Integer endTime) {
        List<Statistics> statisticss = this.sqs.queryAllStatisticsByMonth(startTime, endTime);
        return statisticss;
    }

    //    按照天
//    @RequestMapping(value = "/byday", method = RequestMethod.GET)
    public List<Statistics> queryStatisticsByDay(Integer startTime, Integer endTime) {
        List<Statistics> statisticss = this.sqs.queryAllStatisticsByDay(startTime, endTime);
        return statisticss;
    }

=======
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
>>>>>>> 893a1017823142782e5ebc69948d91236890753a
}









