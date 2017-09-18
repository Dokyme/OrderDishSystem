//package com.odss.seu.service;
//
//
//import com.odss.seu.mapper.OrderMapper;
//import com.odss.seu.vo.Dish;
//import com.odss.seu.vo.Order;
//import com.odss.seu.vo.OrderExample;
//import com.odss.seu.vo.Statistics;
//import com.sun.org.apache.xpath.internal.operations.Or;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class StatisticsQueryServiceImpl implements StatisticsQueryService {
//
//    private OrderMapper orderMapper;
//
//    @Autowired
//    public StatisticsQueryServiceImpl(OrderMapper orderMapper)
//    {
//        this.orderMapper = orderMapper;
//    }
//
//    Integer tableNumber = 30;
//
//    private List<Order> getOrdersWithinRange(Date startTime, Date endTime) {
//        OrderExample orderExample = new OrderExample();
//        orderExample.createCriteria().andTimeBetween(startTime, endTime);
//        return orderMapper.selectByExample(orderExample);
//    }
//
//    private Float calculateTotal(Order order) {
//        Float total = 0f;
//        for (Dish dish : order.getDishes()) {
//            total += dish.getPrice();
//        }
//        return total;
//    }
//
//    private Statistics calculateStatistics(List<Order> orders) {
//        Statistics statistics = new Statistics();
//        int numOfTables = 0;
//        statistics.setSellNum(0);
//        statistics.setIncome(0f);
//        statistics.setFantai(0);
//        for (Order order : orders) {
//            statistics.setSellNum(statistics.getSellNum() + order.getDishes().size());
//            statistics.setIncome(statistics.getIncome() + calculateTotal(order));
//            numOfTables++;
//        }
//        return statistics;
//    }
//
//    @Override
//    public List<Statistics> queryAllStatistics(Long istartTime, Long iendTime, Integer step) {
//        SimpleDateFormat statisticsTimeFmt;//statistics的time格式
//        Long numberOfIterations = 0l;//statistics的数量——迭代的次数
//        Calendar itrCalendar = Calendar.getInstance();//calendar迭代器，代表某个时间范围内的起始位置
//        Calendar itrEnd = Calendar.getInstance();//calendar迭代器，代表某个时间范围内的结束位置，比itrCalenar始终大一个分度
//
//        Date startTime = new Date(istartTime);
//        Date endTime = new Date(iendTime);
//
//        Calendar cStartTime = Calendar.getInstance();
//        cStartTime.setTime(startTime);
//        Calendar cEndTime = Calendar.getInstance();
//        cEndTime.setTime(endTime);
//
//        //起始时间和结束时间调整，如：按月查，就把起点和重点的日期改为1和31日
//        //计算迭代次数
//        //初始化statisticsTimeFmt的格式
//        if (step == Calendar.YEAR) {
//            cStartTime.set(cStartTime.get(Calendar.YEAR), Calendar.JANUARY, 1);
//            cEndTime.set(cEndTime.get(Calendar.YEAR), Calendar.DECEMBER, 31);
//            numberOfIterations = new Long(cEndTime.get(Calendar.YEAR) - cStartTime.get(Calendar.YEAR));
//            statisticsTimeFmt = new SimpleDateFormat("yyyy");
//            itrEnd.set(cStartTime.get(Calendar.YEAR) + 1, cStartTime.get(Calendar.MONTH), cStartTime.get(Calendar.DATE));
//        } else if (step == Calendar.MONTH) {
//            cStartTime.set(cStartTime.get(Calendar.YEAR), cStartTime.get(Calendar.MONTH), 1);
//            cEndTime.set(cEndTime.get(Calendar.YEAR), cEndTime.get(Calendar.MONTH), 31);
//            numberOfIterations = new Long((cEndTime.get(Calendar.YEAR) - cStartTime.get(Calendar.YEAR)) * 12 + cEndTime.get(Calendar.MONTH) - cStartTime.get(Calendar.MONTH));
//            statisticsTimeFmt = new SimpleDateFormat("yyyy-MM");
//            itrEnd.set(cStartTime.get(Calendar.YEAR), cStartTime.get(Calendar.MONTH) + 1, cStartTime.get(Calendar.DATE));
//        } else {
//            numberOfIterations = (cEndTime.getTimeInMillis() - cStartTime.getTimeInMillis()) / (1000 * 3600 * 24);
//            statisticsTimeFmt = new SimpleDateFormat("yyyy-MM-dd");
//            itrEnd.set(cStartTime.get(Calendar.YEAR), cStartTime.get(Calendar.MONTH), cStartTime.get(Calendar.DATE) + 1);
//        }itrCalendar.setTime(cStartTime.getTime());
//
//        List<Statistics> statList = new ArrayList<>();
//
//
//        for (int i = -1; i < numberOfIterations; i++, itrCalendar.add(step, 1), itrEnd.add(step, 1)) {
//            //itrCalendar每次递增一个step（一天或一个月或一年）
//            Statistics stat = calculateStatistics(getOrdersWithinRange(itrCalendar.getTime(), itrEnd.getTime()));
//            stat.setTime(statisticsTimeFmt.format(itrCalendar.getTime()));
//            statList.add(stat);
//        }
//
//        return statList;
//    }
//}
package com.odss.seu.service;

import com.odss.seu.mapper.OrderMapper;
import com.odss.seu.vo.Dish;
import com.odss.seu.vo.Order;
import com.odss.seu.vo.OrderExample;
import com.odss.seu.vo.Statistics;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StatisticsQueryServiceImpl implements StatisticsQueryService{

    @Autowired
    private OrderMapper orderMapper;

    int tableNumber=100;

    public int getDayNumber (Date startTime,Date endTime)
    {
        int days = (int)((startTime.getTime() - endTime.getTime())/86400000);
        return days;
    }

    //    获取开始时间与结束时间
    public Statistics getData(Date startnow,Date endnow)
    {
        OrderExample example =new  OrderExample();
        OrderExample.Criteria criteria=example.createCriteria();

        Statistics statistics=new Statistics();
        int lengthOfGotList=0;
        List<Order> OrderList=null;
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
//        开始时间比要求的开始时间晚或相当
        criteria.andTimeGreaterThanOrEqualTo(startnow);
//        结束时间比要求的结束时间早或相当
        criteria.andTimeLessThanOrEqualTo(endnow);

        OrderList= orderMapper.selectByExample(example);
        lengthOfGotList=OrderList.size();//所有信息的条目数
//        statistics.setstartTime(startnow);
//        statistics.setendTime(endnow);
        statistics.setTime(df.format(startnow));

        int  allIncome=0;
        int  allSellNum=0;
        int allFantai=0;

//            处理信息
        for(int i=0;i<lengthOfGotList;i++)
        {
//                this table income
            List<Dish> DishList=OrderList.get(i).getDishes();
            int forTimesForDishes=DishList.size();
            for(int l=0;l<forTimesForDishes;l++)
            {
                allIncome+=DishList.get(l).getPrice();
            }
//                this table dish number
            allSellNum+=forTimesForDishes;
            allFantai+=1;
        }
//            总订单数/天数/桌数
        allFantai/=getDayNumber(startnow,endnow);
        allFantai/=tableNumber;
        statistics.setIncome(allIncome);
        statistics.setSellNum(allFantai);
        statistics.setFantai(allFantai);
        return statistics;
    }

    @Override
    public List<Statistics> queryAllStatisticsByYear(Integer istartTime, Integer iendTime)
    {
        System.out.println("startToCalculateTheDataaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Date startTime=new Date(new Long(istartTime));
        Date endTime=new Date(new Long(iendTime));
        //         还需要处理一下得到的数据，将他们按照年份计算求和或求均值
        // ——startTime不变，endTime不变，income求和，sellNum求和，fantai求均值=求和+除去个数？=总共有的餐次数除去餐桌数除去天数
        Calendar StartTime=Calendar.getInstance();//先将data转为calendar
        StartTime.setTime((Date) startTime);
        Calendar EndTime=Calendar.getInstance();//先将data转为calendar
        StartTime.setTime((Date) endTime);

        int startYear=StartTime.get(Calendar.YEAR);
        int endYear=EndTime.get(Calendar.YEAR);
        List<Statistics> StatisticsList= new ArrayList<Statistics>();
        int forTimes=endYear-startYear+1;
        Date startnow = null;
        Date endnow = null;
        int year=0;
        Calendar StartTimeForChange =Calendar.getInstance();

        //        如果开始时间与结束时间在同一年，就只算这一年内的从开始时间到结束时间的经营情况
        if(endYear==startYear)
        {
            StatisticsList.add(getData(startTime,endTime));
        }
        else
        {

//        根据日期，算出循环的年数，每一年算出各自一年的经营情况，


            for(int i=0;i<forTimes;i++)
            {
                year=StartTimeForChange.get(Calendar.YEAR);
//        第一年按照开始时间到年末算，结尾一年按照年初到结束时间算
                if(i==0)//第一年
                {
                    //第一年按照开始时间到年末算
                    startnow=startTime;

                    //计算年末
                    StartTimeForChange.setTime((Date) startnow);//转换为calender
                    StartTimeForChange.set(year,12,31);
                    endnow=StartTimeForChange.getTime();

                }
                else if(i==(forTimes-1) )//末尾一年，如果是末尾一轮，就用endTime作为结束
                {
                    StartTimeForChange.setTime((Date) endnow);
                    year+=1;
                    StartTimeForChange.set(year,1,1);
                    startnow=StartTimeForChange.getTime();

                    endnow=endTime;
                }
                else//其余年
                {

                    StartTimeForChange.setTime((Date) endnow);
                    year+=1;
                    StartTimeForChange.set(year,1,1);
                    startnow=StartTimeForChange.getTime();

                    StartTimeForChange.set(year,12,31);
                    endnow=StartTimeForChange.getTime();
                }

                StatisticsList.add(getData(startnow,endnow));
            }
        }

        return StatisticsList;
    }
//        if(statisticsList==null||statisticsList.size()==0)
//        {
//            throws new StatisticsNotFoundException();
//        }else if
//        {
//        }

//___________________________________________________________________________________________________________________________

    @Override
    public List<Statistics> queryAllStatisticsByMonth(Integer istartTime, Integer iendTime)
    {
        Date startTime=new Date(new Long(istartTime));
        Date endTime=new Date(new Long(iendTime));
        //最后的结果存放处
        List<Statistics> StatisticsList= new ArrayList<Statistics>();

        //         还需要处理一下得到的数据，将他们按照年份计算求和或求均值
        // ——startTime不变，endTime不变，income求和，sellNum求和，fantai求均值=求和+除去个数？=总共有的餐次数除去餐桌数除去天数
        Calendar StartTime=Calendar.getInstance();//先将data转为calendar
        StartTime.setTime((Date) startTime);
        Calendar EndTime=Calendar.getInstance();//先将data转为calendar
        StartTime.setTime((Date) endTime);

        int startYear=StartTime.get(Calendar.YEAR);
        int endYear=EndTime.get(Calendar.YEAR);
        int startMonth=StartTime.get(Calendar.MONTH);
        int endMonth=EndTime.get(Calendar.MONTH);

        Date startnow = null;
        Date endnow = null;
        int year=0;
        int month=0;
        Calendar StartTimeForChange = Calendar.getInstance();
        int[] monthDay={0,31,28,31,30,31,30,31,31,30,31,30,31};

//        如果开始时间与结束时间在同一年，就只算这一年内的从开始时间到结束时间的经营情况
        if(endYear==startYear)
        {

            //        如果开始时间与结束时间在同一月，就只算这一月内的从开始时间到结束时间的经营情况
            if(endMonth==startMonth)
            {

                StatisticsList.add(getData(startTime,endTime));
            }
            else//同一年，不同月
            {
                for(int i=0;i<(endMonth-startMonth+1);i++)
                {
                    year=StartTimeForChange.get(Calendar.YEAR);
                    month=StartTimeForChange.get(Calendar.MONTH);
                    if(i==0)
                    {
                        //第一月按照开始时间到月末算
                        startnow=startTime;

                        //计算月末
                        StartTimeForChange.setTime((Date) startnow);//转换为calender
                        StartTimeForChange.set(year,month,monthDay[month]);
                        endnow=StartTimeForChange.getTime();
                    }
                    else if(i==(endMonth-startMonth+1))
                    {
                        //最后一个月按照月初到结束时间
                        StartTimeForChange.setTime(endnow);
                        month+=1;
                        StartTimeForChange.set(year,month,1);
                        startnow=StartTimeForChange.getTime();

                        endnow=endTime;
                    }
                    else
                    {
                        //其它月
                        //月初
                        StartTimeForChange.setTime(endnow);
                        month+=1;
                        StartTimeForChange.set(year,month,1);
                        startnow=StartTimeForChange.getTime();

                        //月末
                        StartTimeForChange.set(year,month,monthDay[month]);
                        endnow=StartTimeForChange.getTime();
                    }
                    StatisticsList.add(getData(startnow,endnow));
                }
            }

        }
        else
        {

//        开始时间与结束时间不在同一年，先根据日期，算出循环的年数，之后每一年算出各自一年的每月经营情况，
            int forTimes=endYear-startYear+1;
            int forTimesForMonth=0;
            for(int i=0;i<forTimes;i++)
            {
//        每一年
                if(i==0)//第一年
                {
                    forTimesForMonth=12-startMonth+1;
                    for(int j=0;j<forTimesForMonth;j++)
                    {

                        year=StartTimeForChange.get(Calendar.YEAR);
                        month=StartTimeForChange.get(Calendar.MONTH);
                        if(j==0)
                        {
                            //第一月按照开始时间到月末算
                            startnow=startTime;

                            //计算月末
                            StartTimeForChange.setTime((Date) startnow);//转换为calender
                            StartTimeForChange.set(year,month,monthDay[month]);
                            endnow=StartTimeForChange.getTime();
                        }
                        else//普通月，以及年末也算在内
                        {
                            //月初
                            StartTimeForChange.setTime(endnow);
                            month+=1;
                            StartTimeForChange.set(year,month,1);
                            startnow=StartTimeForChange.getTime();

                            //月末
                            StartTimeForChange.set(year,month,monthDay[month]);
                            endnow=StartTimeForChange.getTime();
                        }

                        StatisticsList.add(getData(startnow,endnow));
                    }
                }
                else if(i==(forTimes-1) )//末尾一年，如果是末尾一轮，就用endTime作为结束
                {
                    forTimesForMonth=endMonth;
                    year = StartTimeForChange.get(Calendar.YEAR);
                    year += 1;
                    month=1;
                    //末尾一年的每个月
                    for(int j=0;j<forTimesForMonth;j++)
                    {
                        //第一月
                        if(j==0)
                        {
//                            StartTimeForChange.setTime((Date) endnow);
                            StartTimeForChange.set(year, month, 1);
                            startnow = StartTimeForChange.getTime();

                            StartTimeForChange.set(year, month, monthDay[month]);
                            endnow = StartTimeForChange.getTime();
                        }
                        else  if (j==forTimesForMonth-1)
                        {
                            //末尾年末尾月
                            StartTimeForChange.setTime((Date) endnow);
                            year = StartTimeForChange.get(Calendar.YEAR);
                            month=StartTimeForChange.get(Calendar.MONTH);
                            month+=1;
                            StartTimeForChange.set(year, month, 1);
                            startnow = StartTimeForChange.getTime();

                            endnow = endTime;
                        }
                        else
                        {
                            //末尾年其它月
                            StartTimeForChange.setTime((Date) endnow);
                            year = StartTimeForChange.get(Calendar.YEAR);
                            month=StartTimeForChange.get(Calendar.MONTH);
                            month+=1;
                            StartTimeForChange.set(year, month, 1);
                            startnow = StartTimeForChange.getTime();

                            StartTimeForChange.set(year,month,monthDay[month]);
                            endnow=StartTimeForChange.getTime();
                        }
                    }
                    StatisticsList.add(getData(startnow,endnow));

                }
                else//其余年
                {

                    forTimesForMonth=12;
                    month=1;
                    StartTimeForChange.setTime(endnow);
                    year=StartTimeForChange.get(Calendar.YEAR);
                    year+=1;
                    StartTimeForChange.set(year,month,1);
                    startnow=StartTimeForChange.getTime();
                    StartTimeForChange.set(year,month,monthDay[month]);
                    endnow=StartTimeForChange.getTime();

                    for(int j=0;j<forTimesForMonth;j++) {

                        StatisticsList.add(getData(startnow,endnow));

                        //月初
                        StartTimeForChange.setTime(endnow);
                        year=StartTimeForChange.get(Calendar.YEAR);
                        month=StartTimeForChange.get(Calendar.MONTH);
                        month+=1;
                        StartTimeForChange.set(year,month,1);
                        startnow=StartTimeForChange.getTime();

                        //月末
                        StartTimeForChange.set(year,month,monthDay[month]);
                        endnow=StartTimeForChange.getTime();

                    }
                }

            }
        }

        return StatisticsList;
    }
    //______________________________________________________________________________________________________________________
//______________________________________________________________________________________________________________________
    @Override
    public List<Statistics> queryAllStatisticsByDay(Integer istartTime, Integer iendTime) {

        Date startTime=new Date(new Long(istartTime));
        Date endTime=new Date(new Long(iendTime));

        List<Statistics> StatisticsList= new ArrayList<Statistics>();

        //         还需要处理一下得到的数据，将他们按照年份计算求和或求均值
        // ——startTime不变，endTime不变，income求和，sellNum求和，fantai求均值=求和+除去个数？=总共有的餐次数除去餐桌数除去天数
        Calendar StartTime=Calendar.getInstance();//先将data转为calendar
        StartTime.setTime((Date) startTime);
        Calendar EndTime=Calendar.getInstance();//先将data转为calendar
        StartTime.setTime((Date) endTime);

        int startYear=StartTime.get(Calendar.YEAR);
        int endYear=EndTime.get(Calendar.YEAR);
        int startMonth=StartTime.get(Calendar.MONTH);
        int endMonth=EndTime.get(Calendar.MONTH);
        int startDay=StartTime.get(Calendar.DAY_OF_MONTH);
        int endDay=EndTime.get(Calendar.DAY_OF_MONTH);

        Date startnow = null;
        Date endnow = null;
        int year=0;
        int month=0;
        int day=0;
        Calendar StartTimeForChange=Calendar.getInstance();
        int[] monthDay={0,31,28,31,30,31,30,31,31,30,31,30,31};

//        如果开始时间与结束时间在同一年，就只算这一年内的从开始时间到结束时间的经营情况
        if(endYear==startYear)
        {

            //        如果开始时间与结束时间在同一月，就只算这一月内的从开始时间到结束时间的经营情况
            if(endMonth==startMonth)
            {
                int forTimesForDay=endDay-startDay+1;
                for(int m=0;m<forTimesForDay;m++)
                {

                    year=StartTimeForChange.get(Calendar.YEAR);
                    month=StartTimeForChange.get(Calendar.MONTH);
                    if(startDay==endDay)
                    {
                        startnow=startTime;
                        endnow=endTime;
                    }
                    else
                    {
                        StartTimeForChange.setTime((Date) startnow);
                        day=StartTimeForChange.get(Calendar.DAY_OF_MONTH);
                        StartTimeForChange.set(year,month,day+1);
                        startnow=StartTimeForChange.getTime();

//                      同一天
                        endnow=startnow;
                    }
                    StatisticsList.add(getData(startnow,endnow));
                }

            }
            else//同一年，不同月
            {
                for(int i=0;i<(endMonth-startMonth+1);i++)
                {

                    year=StartTimeForChange.get(Calendar.YEAR);
                    month=StartTimeForChange.get(Calendar.MONTH);
                    if(i==0)
                    {
                        //第一月按照开始时间到月末算
                        startnow=startTime;

                        //计算月末
                        StartTimeForChange=Calendar.getInstance();
                        StartTimeForChange.setTime((Date) startnow);//转换为calender
                        StartTimeForChange.set(year,month,monthDay[month]);
                        endnow=StartTimeForChange.getTime();
                    }
                    else if(i==(endMonth-startMonth+1))
                    {
                        //最后一个月按照月初到结束时间
                        StartTimeForChange.setTime(endnow);
                        month+=1;
                        StartTimeForChange.set(year,month,1);
                        startnow=StartTimeForChange.getTime();

                        endnow=endTime;
                    }
                    else
                    {
                        //其它月
                        //月初
                        StartTimeForChange.setTime(endnow);
                        month+=1;
                        StartTimeForChange.set(year,month,1);
                        startnow=StartTimeForChange.getTime();

                        //月末
                        StartTimeForChange.set(year,month,monthDay[month]);
                        endnow=StartTimeForChange.getTime();

                    }


                    StartTimeForChange.setTime(startnow);
                    startDay=StartTimeForChange.get(Calendar.DAY_OF_MONTH);
                    StartTimeForChange.setTime(endnow);
                    endDay=StartTimeForChange.get(Calendar.DAY_OF_MONTH);
                    int forTimesForDay=endDay-startDay+1;
                    for(int m=0;m<forTimesForDay;m++)
                    {
                        if(startDay==endDay)
                        {
                            startnow=startTime;
                            endnow=endTime;
                        }
                        else
                        {
                            StartTimeForChange.setTime((Date) startnow);
                            year=StartTimeForChange.get(Calendar.YEAR);
                            month=StartTimeForChange.get(Calendar.MONTH);
                            day=StartTimeForChange.get(Calendar.DAY_OF_MONTH);
                            StartTimeForChange.set(year,month,day+1);
                            startnow=StartTimeForChange.getTime();

//                      同一天
                            endnow=startnow;
                        }
                        StatisticsList.add(getData(startnow,endnow));
                    }

                }
            }

        }
        else
        {

//        开始时间与结束时间不在同一年，先根据日期，算出循环的年数，之后每一年算出各自一年的每月经营情况，
            int forTimes=endYear-startYear+1;
            int forTimesForMonth=0;
            for(int i=0;i<forTimes;i++)
            {
//        每一年
                if(i==0)//第一年
                {
                    forTimesForMonth=12-startMonth+1;
                    for(int j=0;j<forTimesForMonth;j++)
                    {

                        year=StartTimeForChange.get(Calendar.YEAR);
                        month=StartTimeForChange.get(Calendar.MONTH);
                        if(j==0)
                        {
                            //第一月按照开始时间到月末算
                            startnow=startTime;

                            //计算月末
                            StartTimeForChange=Calendar.getInstance();
                            StartTimeForChange.setTime((Date) startnow);//转换为calender
                            StartTimeForChange.set(year,month,monthDay[month]);
                            endnow=StartTimeForChange.getTime();
                        }
                        else//普通月，以及年末也算在内
                        {
                            //月初
                            StartTimeForChange.setTime(endnow);
                            month+=1;
                            StartTimeForChange.set(year,month,1);
                            startnow=StartTimeForChange.getTime();

                            //月末
                            StartTimeForChange.set(year,month,monthDay[month]);
                            endnow=StartTimeForChange.getTime();
                        }

                        StartTimeForChange.setTime(startnow);
                        startDay=StartTimeForChange.get(Calendar.DAY_OF_MONTH);
                        StartTimeForChange.setTime(endnow);
                        endDay=StartTimeForChange.get(Calendar.DAY_OF_MONTH);
                        int forTimesForDay=endDay-startDay+1;
                        for(int m=0;m<forTimesForDay;m++) {
                            if (startDay == endDay) {
                                startnow = startTime;
                                endnow = endTime;
                            } else {
                                StartTimeForChange.setTime((Date) startnow);
                                year = StartTimeForChange.get(Calendar.YEAR);
                                month = StartTimeForChange.get(Calendar.MONTH);
                                day = StartTimeForChange.get(Calendar.DAY_OF_MONTH);
                                StartTimeForChange.set(year, month, day + 1);
                                startnow = StartTimeForChange.getTime();

//                      同一天
                                endnow = startnow;
                            }
                            StatisticsList.add(getData(startnow,endnow));
                        }
                    }
                }
                else if(i==(forTimes-1) )//末尾一年，如果是末尾一轮，就用endTime作为结束
                {
                    forTimesForMonth=endMonth;
                    year = StartTimeForChange.get(Calendar.YEAR);
                    year += 1;
                    month=1;
                    //末尾一年的每个月
                    for(int j=0;j<forTimesForMonth;j++)
                    {
                        //第一月
                        if(j==0)
                        {
//                            StartTimeForChange.setTime((Date) endnow);
                            StartTimeForChange.set(year, month, 1);
                            startnow = StartTimeForChange.getTime();

                            StartTimeForChange.set(year, month, monthDay[month]);
                            endnow = StartTimeForChange.getTime();
                        }
                        else  if (j==forTimesForMonth-1)
                        {
                            //末尾年末尾月
                            StartTimeForChange.setTime((Date) endnow);
                            year = StartTimeForChange.get(Calendar.YEAR);
                            month=StartTimeForChange.get(Calendar.MONTH);
                            month+=1;
                            StartTimeForChange.set(year, month, 1);
                            startnow = StartTimeForChange.getTime();

                            endnow = endTime;
                        }
                        else
                        {
                            //末尾年其它月
                            StartTimeForChange.setTime((Date) endnow);
                            year = StartTimeForChange.get(Calendar.YEAR);
                            month=StartTimeForChange.get(Calendar.MONTH);
                            month+=1;
                            StartTimeForChange.set(year, month, 1);
                            startnow = StartTimeForChange.getTime();

                            StartTimeForChange.set(year,month,monthDay[month]);
                            endnow=StartTimeForChange.getTime();
                        }
                    }


                    StartTimeForChange.setTime(startnow);
                    startDay=StartTimeForChange.get(Calendar.DAY_OF_MONTH);
                    StartTimeForChange.setTime(endnow);
                    endDay=StartTimeForChange.get(Calendar.DAY_OF_MONTH);
                    int forTimesForDay=endDay-startDay+1;
                    for(int m=0;m<forTimesForDay;m++) {
                        if (startDay == endDay) {
                            startnow = startTime;
                            endnow = endTime;
                        } else {
                            StartTimeForChange.setTime((Date) startnow);
                            year = StartTimeForChange.get(Calendar.YEAR);
                            month = StartTimeForChange.get(Calendar.MONTH);
                            day = StartTimeForChange.get(Calendar.DAY_OF_MONTH);
                            StartTimeForChange.set(year, month, day + 1);
                            startnow = StartTimeForChange.getTime();

//                      同一天
                            endnow = startnow;
                        }
                        StatisticsList.add(getData(startnow,endnow));
                    }

                }
                else//其余年
                {

                    forTimesForMonth=12;
                    month=1;
                    StartTimeForChange.setTime(endnow);
                    year=StartTimeForChange.get(Calendar.YEAR);
                    year+=1;
                    StartTimeForChange.set(year,month,1);
                    startnow=StartTimeForChange.getTime();
                    StartTimeForChange.set(year,month,monthDay[month]);
                    endnow=StartTimeForChange.getTime();


                    StartTimeForChange.setTime(startnow);
                    startDay=StartTimeForChange.get(Calendar.DAY_OF_MONTH);
                    StartTimeForChange.setTime(endnow);
                    endDay=StartTimeForChange.get(Calendar.DAY_OF_MONTH);
                    int forTimesForDay=endDay-startDay+1;
                    for(int m=0;m<forTimesForDay;m++)
                    {
                        if(startDay==endDay)
                        {
                            startnow=startTime;
                            endnow=endTime;
                        }
                        else
                        {
                            StartTimeForChange.setTime((Date) startnow);
                            year=StartTimeForChange.get(Calendar.YEAR);
                            month=StartTimeForChange.get(Calendar.MONTH);
                            day=StartTimeForChange.get(Calendar.DAY_OF_MONTH);
                            StartTimeForChange.set(year,month,day+1);
                            startnow=StartTimeForChange.getTime();

//                      同一天
                            endnow=startnow;
                        }
                        StatisticsList.add(getData(startnow,endnow));

                        //月初
                        StartTimeForChange.setTime(endnow);
                        year=StartTimeForChange.get(Calendar.YEAR);
                        month=StartTimeForChange.get(Calendar.MONTH);
                        month+=1;
                        StartTimeForChange.set(year,month,1);
                        startnow=StartTimeForChange.getTime();

                        //月末
                        StartTimeForChange.set(year,month,monthDay[month]);
                        endnow=StartTimeForChange.getTime();

                    }
                }

            }
        }

        return StatisticsList;
    }


}