package com.example.njxfuture.API.DataModels.PackageDataModel;

import java.util.List;

public class PackageDetailsDataModel {
    private String tid;
    private String img;
    private List<Point> points;
    private List<Step> steps;

    public void setSteps(List<Step> steps){
        this.steps=steps;
    }
    public void setPoints(List<Point> points){
        this.points=points;
    }
    public void setTid(String tid)
    {
        this.tid=tid;
    }
    public void setImg(String img){
        this.img=img;
    }
    public List<Step> getSteps(){
        return steps;
    }
    public List<Point> getPoints(){
        return points;
    }
    public String getTid()
    {
        return tid;
    }
    public String getImg(){
        return img;
    }
}

