package com.dhf.utils;

import java.util.HashMap;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/07/13 8:52
 * 返回结果的统一API
 */
public class ResultAPI {
    private int code;
    private String message;
    private HashMap<String,Object> data;

    public int getCode() {
        return code;
    }
    public static ResultAPI ok(){
        ResultAPI resultAPI = new ResultAPI();
        return resultAPI.setCode(200).setMessage("ok");
    }
    public ResultAPI setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultAPI setMessage(String message) {
        this.message = message;
        return this;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public ResultAPI setData(HashMap<String, Object> data) {
        this.data = data;
        return this;
    }
    public ResultAPI setDate(String key,Object value){
        if (this.data==null){
            this.data=new HashMap<>();
        }
        this.data.put(key,value);
        return this;
    }
}
