package com.odss.seu.vo;

import java.util.Date;

public class Checkout {

    private Integer id;
    private Integer table;
    private Date time;
    private Integer state;
    private Float total;

    public Checkout(Integer id, Integer table, Date time, Integer state, Float total) {
        this.id = id;
        this.table = table;
        this.time = time;
        this.state = state;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTable() {
        return table;
    }

    public void setTable(Integer table) {
        this.table = table;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
