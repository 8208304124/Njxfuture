package com.example.njxfuture.API.DataModels;

public class UpdateUserDataModel {
    String user, msg;
    boolean acty;

    public  void setUser(String user){
        this.user=user;
    }
    public void setMsg(String msg){
        this.msg=msg;
    }
    public void setActy(boolean acty){
        this.acty=acty;
    }
    public String getUser() {
        return user;
    }
    public String getMsg() {
        return msg;
    }
    public boolean getActy() {
        return acty;
    }
}
