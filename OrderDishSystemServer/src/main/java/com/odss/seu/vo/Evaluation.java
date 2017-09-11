package com.odss.seu.vo;

public class Evaluation {
    private Integer cookSpeed;
    private Integer serveAttitude;
    private Integer dishQuality;

    public Evaluation(Integer cookSpeed, Integer serveAttitude, Integer dishQuality) {
        this.cookSpeed = cookSpeed;
        this.serveAttitude = serveAttitude;
        this.dishQuality = dishQuality;
    }

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
