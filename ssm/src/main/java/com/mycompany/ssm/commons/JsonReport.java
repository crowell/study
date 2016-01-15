package com.mycompany.ssm.commons;

/**
 * Created by JinBingBing on 2016/1/14.
 */
public class JsonReport {
    private boolean success = true;
    private Object data;
    private String errorMsg;

    public JsonReport(Object data, String errorMsg) {
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public JsonReport(boolean success, String errorMsg) {
        this.success = success;
        this.errorMsg = errorMsg;
    }

    public JsonReport(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public JsonReport(boolean success, Object data, String errorMsg) {
        this.success = success;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public JsonReport(boolean success) {
        this.success = success;
    }

    public JsonReport() {
    }

    public JsonReport(String errorMsg) {

        this.errorMsg = errorMsg;
    }

    public JsonReport(Object data) {

        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
