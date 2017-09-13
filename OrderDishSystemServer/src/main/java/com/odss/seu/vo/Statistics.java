package com.odss.seu.vo;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.Date;

public class Statistics implements Serializable {

    @JsonView(ViewLevel.Summary.class)
    private Date startTime;

    @JsonView(ViewLevel.Summary.class)
    private Date endTime;

    @JsonView(ViewLevel.Summary.class)
    private Integer income;

    @JsonView(ViewLevel.Summary.class)
    private Integer sellNum;

    @JsonView(ViewLevel.Summary.class)
    private Integer fantai;//翻台率

    public void setstartTime(Date startTime)
    {
        this.startTime=startTime;
    }

    public Date getstartTime()
    {
        return startTime;
    }


    public void setendTime(Date endTime)
    {
        this.endTime=endTime;
    }

    public Date getendTime()
    {
        return endTime;
    }


    public void setIncome(Integer income)
    {
        this.income=income;
    }

    public Integer getIncome()
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