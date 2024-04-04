package com.example.njxfuture.API.DataModels;

public class PushNotificationDataModel {
    private String nid,pckimg,title,pckid,dtxt,pdt;

    // Constructor
    public PushNotificationDataModel(String title, String nid, String pckimg, String dtxt, String pckid, String pdt) {
        this.title = title;
        this.nid = nid;
        this.pckimg = pckimg;
        this.dtxt = dtxt;
        this.pckid = pckid;
        this.pdt = pdt;
    }

    // Getters and setters
    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getnid() {
        return nid;
    }

    public void setnid(String nid) {
        this.nid = nid;
    }

    public String getpckimg() {
        return pckimg;
    }

    public void setpckimg(String pckimg) {
        this.pckimg = pckimg;
    }

    public String getdtxt() {
        return dtxt;
    }

    public void setdtxt(String dtxt) {
        this.dtxt = dtxt;
    }

    public String getpckid() {
        return pckid;
    }

    public void setpckid(String pckid) {
        this.pckid = pckid;
    }

    public String getpdt() {
        return pdt;
    }

    public void setpdt(String pdt) {
        this.pdt = pdt;
    }
}
