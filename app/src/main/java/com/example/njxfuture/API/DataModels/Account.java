package com.example.njxfuture.API.DataModels;

public class Account {
    String msg, err;
    boolean res;

    public String getMsg() {
        return msg;
    }

    public String getErr() {
        return err;
    }

    public boolean getRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
