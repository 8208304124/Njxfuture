package com.example.njxfuture.API.DataModels;

public class User {
    String user, email, mob, gstin, pas, msg;
    boolean acty;

    public String getUser() {
        return user;
    }
    public String getEmail() {
        return email;
    }
    public String getMob() {
        return mob;
    }
    public String getGstin() {
        return gstin;
    }
    public String getPas() {
        return pas;
    }
    public String getMsg() {
        return msg;
    }
    public boolean getActy() {
        return acty;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public void setEmail(String user) {
        this.email = user;
    }

    public void setMob(String user) {
        this.mob = user;
    }

    public void setGstin(String user) {
        this.gstin = user;
    }

    public void setPas(String user) {
        this.pas = user;
    }

    public void setMsg(String user) {
        this.msg = user;
    }

    public void setActy(boolean user) {
        this.acty = user;
    }

}
