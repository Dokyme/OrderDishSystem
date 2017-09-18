package com.odss.seu.vo;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.Date;

public class Statistics implements Serializable {

//    @JsonView(ViewLevel.Summary.class)
//    private Date startTime;
//
//    @JsonView(ViewLevel.Summary.class)
//    private Date endTime;

    @JsonView(ViewLevel.Summary.class)
    private String time;

    @JsonView(ViewLevel.Summary.class)
    private float income;

    @JsonView(ViewLevel.Summary.class)
    private Integer sellNum;

    @JsonView(ViewLevel.Summary.class)
    private Integer fantai;//翻台率


    public void setTime(String time)
    {
        this.time=time;
    }

    public String getTime()
    {
        return time;
    }



//    public void setstartTime(Date startTime)
//    {
//        this.startTime=startTime;
//    }
//
//    public Date getstartTime()
//    {
//        return startTime;
//    }
//
//
//    public void setendTime(Date endTime)
//    {
//        this.endTime=endTime;
//    }
//
//    public Date getendTime()
//    {
//        return endTime;
//    }


    public void setIncome(float income)
    {
        this.income=income;
    }

    public float getIncome()
    {
        return income;
    }


    public void setSellNum(Integer sellNum)
    {
        this.sellNum=sellNum;
    }

    public Integer getSellNum()
    {
        return sellNum;
    }


    public void setFantai(Integer fantai)
    {
        this.fantai=fantai;
    }

    public Integer getFantai()
    {
        return fantai;
    }


}
