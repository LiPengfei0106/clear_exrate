package com.cleartv.exrate;

import java.util.List;

/**
 * Created by Lee on 2017/7/10.
 */

public class JuHeData {


    /**
     * reason : 查询成功!
     * result : [{"currencyF":"CNY","currencyF_Name":"人民币","currencyT":"USD","currencyT_Name":"美元","currencyFD":"1","exchange":"0.1470","result":"0.1470","updateTime":"2017-07-10 09:21:42"},{"currencyF":"USD","currencyF_Name":"美元","currencyT":"CNY","currencyT_Name":"人民币","currencyFD":"1","exchange":"6.8039","result":"6.8039","updateTime":"2017-07-10 09:21:42"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * currencyF : CNY
         * currencyF_Name : 人民币
         * currencyT : USD
         * currencyT_Name : 美元
         * currencyFD : 1
         * exchange : 0.1470
         * result : 0.1470
         * updateTime : 2017-07-10 09:21:42
         */

        private String currencyF;
        private String currencyF_Name;
        private String currencyT;
        private String currencyT_Name;
        private String currencyFD;
        private String exchange;
        private String result;
        private String updateTime;

        public String getCurrencyF() {
            return currencyF;
        }

        public void setCurrencyF(String currencyF) {
            this.currencyF = currencyF;
        }

        public String getCurrencyF_Name() {
            return currencyF_Name;
        }

        public void setCurrencyF_Name(String currencyF_Name) {
            this.currencyF_Name = currencyF_Name;
        }

        public String getCurrencyT() {
            return currencyT;
        }

        public void setCurrencyT(String currencyT) {
            this.currencyT = currencyT;
        }

        public String getCurrencyT_Name() {
            return currencyT_Name;
        }

        public void setCurrencyT_Name(String currencyT_Name) {
            this.currencyT_Name = currencyT_Name;
        }

        public String getCurrencyFD() {
            return currencyFD;
        }

        public void setCurrencyFD(String currencyFD) {
            this.currencyFD = currencyFD;
        }

        public String getExchange() {
            return exchange;
        }

        public void setExchange(String exchange) {
            this.exchange = exchange;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
