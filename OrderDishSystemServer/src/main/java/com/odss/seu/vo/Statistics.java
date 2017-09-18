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
    private Float income;

    @JsonView(ViewLevel.Summary.class)
    private Integer sellNum;

    @JsonView(ViewLevel.Summary.class)
    private Float fantai;//翻台率


    public void setTime(String time)
    {
        this.time=time;
    }

    public String getTime()
    {
        return time;
    }




    public void setIncome(Float income)
    {
        this.income=income;
    }
    public Float getIncome()
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


    public void setFantai(Float fantai)
    {
        this.fantai=fantai;
    }

    public Float getFantai()
    {
        return fantai;
    }


}
