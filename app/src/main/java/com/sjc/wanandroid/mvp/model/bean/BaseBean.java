package com.sjc.wanandroid.mvp.model.bean;

/**
 * Created by sjc on 2018/5/22 11:20
 */

public class BaseBean<T> {

    /**
     * data : {"collectIds":[],"email":"","icon":"","id":5667,"password":"123456","type":0,"username":"15539630635"}
     * errorCode : 0
     * errorMsg :
     */

    private T data;
    private int errorCode;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
