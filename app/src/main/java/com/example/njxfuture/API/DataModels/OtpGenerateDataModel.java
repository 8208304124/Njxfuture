package com.example.njxfuture.API.DataModels;

public class OtpGenerateDataModel {
    String user,msg,otp;
    boolean acty;
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String user) {
        this.msg = user;
    }
    public String getOtp() {
        return otp;
    }
    public void setOtp(String otp) {
        this.otp=otp;
    }
    public void setActy(boolean acty) {
        this.acty=acty;
    }
    public boolean getActy() {
        return acty;
    }
}
