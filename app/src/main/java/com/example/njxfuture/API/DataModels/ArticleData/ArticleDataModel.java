package com.example.njxfuture.API.DataModels.ArticleData;

public class ArticleDataModel {
    private String aid,img,title,link,pdt,art;

    // Constructor
    public ArticleDataModel(String title, String aid, String img, String pdt, String link, String art) {
        this.title = title;
        this.aid = aid;
        this.img = img;
        this.pdt = pdt;
        this.link = link;
        this.art = art;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPdt() {
        return pdt;
    }

    public void setPdt(String pdt) {
        this.pdt = pdt;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }
}