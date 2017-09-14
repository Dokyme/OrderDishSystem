package com.odss.seu.vo;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;

public class Evaluation implements Serializable {
    private static final long serialVersionUID =1L;
    @JsonView(ViewLevel.Summary.class)
    private Integer cookSpeed;
    @JsonView(ViewLevel.Summary.class)
    private Integer serveAttitude;
    @JsonView(ViewLevel.Summary.class)
    private Integer dishQuality;



    public Integer getCookSpeed() {
        return cookSpeed;
    }

    public void setCookSpeed(Integer cookSpeed) {
        this.cookSpeed = cookSpeed;
    }

    public Integer getServeAttitude() {
        return serveAttitude;
    }

    public void setServeAttitude(Integer serveAttitude) {
        this.serveAttitude = serveAttitude;
    }

    public Integer getDishQuality() {
        return dishQuality;
    }

    public void setDishQuality(Integer dishQuality) {
        this.dishQuality = dishQuality;
    }
}
