package com.odss.seu.vo;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;

public class Data implements Serializable {

    @JsonView(ViewLevel.Summary.class)
    private Integer startTime;

    @JsonView(ViewLevel.Summary.class)
    private Integer endTime;

    @JsonView(ViewLevel.Summary.class)
    private Integer income;

    @JsonView(ViewLevel.Summary.class)
    private Integer sellNum;

    @JsonView(ViewLevel.Summary.class)
    private Integer fantai;//翻台率


}
