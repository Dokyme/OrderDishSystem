package com.odss.seu.vo;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Date;

public class Checkout {

    @JsonView(ViewLevel.Summary.class)
    private Integer id;
    @JsonView(ViewLevel.Summary.class)
    private Integer table;
    @JsonView(ViewLevel.Summary.class)
    private Date time;
    @JsonView(ViewLevel.Summary.class)
    private Integer state;
    @JsonView(ViewLevel.Summary.class)
    private Float total;

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
