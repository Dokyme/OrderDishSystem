package com.odss.seu.service;


import com.odss.seu.mapper.OrderMapper;
import com.odss.seu.vo.Dish;
import com.odss.seu.vo.Order;
import com.odss.seu.vo.OrderExample;
import com.odss.seu.vo.Statistics;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class StatisticsQueryServiceImpl implements StatisticsQueryService {

    private OrderMapper orderMapper;

    @Autowired
    public StatisticsQueryServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    Integer tableNumber = 30;

    private List<Order> getOrdersWithinRange(Date startTime, Date endTime) {
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andTimeBetween(startTime, endTime);
        return orderMapper.selectByExample(orderExample);
    }

    private Float calculateTotal(Order order) {
        Float total = 0f;
        for (Dish dish : order.getDishes()) {
            total += dish.getPrice();
        }
        return total;
    }

    private Statistics calculateStatistics(List<Order> orders) {
        Statistics statistics = new Statistics();
        int numOfTables = 0;
        statistics.setSellNum(0);
        statistics.setIncome(0f);
        statistics.setFantai(0f);
        for (Order order : orders) {
            statistics.setSellNum(statistics.getSellNum() + order.getDishes().size());
            statistics.setIncome(statistics.getIncome() + calculateTotal(order));
            numOfTables++;
        }
        return statistics;
    }

    @Override
    public List<Statistics> queryAllStatistics(Long istartTime, Long iendTime, Integer step) {
        SimpleDateFormat statisticsTimeFmt;//statistics的time格式
        Long numberOfIterations = 0l;//statistics的数量——迭代的次数
        Calendar itrCalendar = Calendar.getInstance();//calendar迭代器，代表某个时间范围内的起始位置
        Calendar itrEnd = Calendar.getInstance();//calendar迭代器，代表某个时间范围内的结束位置，比itrCalenar始终大一个分度

        Date startTime = new Date(istartTime);
        Date endTime = new Date(iendTime);

        Calendar cStartTime = Calendar.getInstance();
        cStartTime.setTime(startTime);
        Calendar cEndTime = Calendar.getInstance();
        cEndTime.setTime(endTime);

        //起始时间和结束时间调整，如：按月查，就把起点和重点的日期改为1和31日
        //计算迭代次数
        //初始化statisticsTimeFmt的格式
        if (step == Calendar.YEAR) {
            cStartTime.set(cStartTime.get(Calendar.YEAR), Calendar.JANUARY, 1);
            cEndTime.set(cEndTime.get(Calendar.YEAR), Calendar.DECEMBER, 31);
            numberOfIterations = new Long(cEndTime.get(Calendar.YEAR) - cStartTime.get(Calendar.YEAR));
            statisticsTimeFmt = new SimpleDateFormat("yyyy");
            itrEnd.set(cStartTime.get(Calendar.YEAR) + 1, cStartTime.get(Calendar.MONTH), cStartTime.get(Calendar.DATE));
        } else if (step == Calendar.MONTH) {
            cStartTime.set(cStartTime.get(Calendar.YEAR), cStartTime.get(Calendar.MONTH), 1);
            cEndTime.set(cEndTime.get(Calendar.YEAR), cEndTime.get(Calendar.MONTH), 31);
            numberOfIterations = new Long((cEndTime.get(Calendar.YEAR) - cStartTime.get(Calendar.YEAR)) * 12 + cEndTime.get(Calendar.MONTH) - cStartTime.get(Calendar.MONTH));
            statisticsTimeFmt = new SimpleDateFormat("yyyy-MM");
            itrEnd.set(cStartTime.get(Calendar.YEAR), cStartTime.get(Calendar.MONTH) + 1, cStartTime.get(Calendar.DATE));
        } else {
            numberOfIterations = (cEndTime.getTimeInMillis() - cStartTime.getTimeInMillis()) / (1000 * 3600 * 24);
            statisticsTimeFmt = new SimpleDateFormat("yyyy-MM-dd");
            itrEnd.set(cStartTime.get(Calendar.YEAR), cStartTime.get(Calendar.MONTH), cStartTime.get(Calendar.DATE) + 1);
        }

        List<Statistics> statList = new ArrayList<>();
        itrCalendar.setTime(cStartTime.getTime());

        for (int i = -1; i < numberOfIterations; i++, itrCalendar.add(step, 1), itrEnd.add(step, 1)) {
            //itrCalendar每次递增一个step（一天或一个月或一年）
            Statistics stat = calculateStatistics(getOrdersWithinRange(itrCalendar.getTime(), itrEnd.getTime()));
            stat.setTime(statisticsTimeFmt.format(itrCalendar.getTime()));
            statList.add(stat);
        }

        return statList;
    }
}