package com.cleartv.exrate;

import java.util.HashMap;

/**
 * Created by Lee on 2017/7/7.
 */

public class ExRateBean {

    private HashMap<String,String> countryName;
    private String countryCode;
    private String toRMB = "209.8636";
    private String fromRMB = "0.0048";
    private int imgRes;

    public ExRateBean(String zh_name, String en_name, String countryCode,String defaultTo,String defaultFrom,int imgRes) {
        HashMap<String,String> countryName = new HashMap<>();
        countryName.put("zh-CN",zh_name);
        countryName.put("en-US",en_name);
        toRMB = defaultTo;
        fromRMB = defaultFrom;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.imgRes = imgRes;
    }

    public HashMap<String, String> getCountryName() {
        return countryName;
    }

    public void setCountryName(HashMap<String, String> countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getToRMB() {
        return toRMB;
    }

    public void setToRMB(String toRMB) {
        this.toRMB = toRMB;
    }

    public String getFromRMB() {
        return fromRMB;
    }

    public void setFromRMB(String fromRMB) {
        this.fromRMB = fromRMB;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }
}
