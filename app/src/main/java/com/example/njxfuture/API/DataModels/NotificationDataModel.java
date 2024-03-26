package com.example.njxfuture.API.DataModels;

public class NotificationDataModel {
    private String pid,pckimg,tip,pckid,pdt,art;

    // Constructor
    public NotificationDataModel(String tip, String pid, String pckimg, String pdt, String pckid, String art) {
        this.tip = tip;
        this.pid = pid;
        this.pckimg = pckimg;
        this.pdt = pdt;
        this.pckid = pckid;
        this.art = art;
    }

    // Getters and setters
    public String gettip() {
        return tip;
    }

    public void settip(String tip) {
        this.tip = tip;
    }

    public String getpid() {
        return pid;
    }

    public void setpid(String pid) {
        this.pid = pid;
    }

    public String getpckimg() {
        return pckimg;
    }

    public void setpckimg(String pckimg) {
        this.pckimg = pckimg;
    }

    public String getPdt() {
        return pdt;
    }

    public void setPdt(String pdt) {
        this.pdt = pdt;
    }

    public String getpckid() {
        return pckid;
    }

    public void setpckid(String pckid) {
        this.pckid = pckid;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }
}
