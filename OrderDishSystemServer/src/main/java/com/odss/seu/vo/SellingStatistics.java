package com.odss.seu.vo;

import java.util.Date;

public class SellingStatistics {
    private Date startDate;
    private Date endDate;
    private Float income;
    private Integer sellnum;
    private Float fantai;

    public SellingStatistics(Date startDate, Float income, Integer sellnum, Float fantai) {
        this.startDate = startDate;
        this.income = income;
        this.sellnum = sellnum;
        this.fantai = fantai;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }

    public Integer getSellnum() {
        return sellnum;
    }

    public void setSellnum(Integer sellnum) {
        this.sellnum = sellnum;
    }

    public Float getFantai() {
        return fantai;
    }

    public void setFantai(Float fantai) {
        this.fantai = fantai;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
