package com.example.demochauluc.exception;

import java.util.Map;

public class ResponseObject {

    private Object data;
    
    private Map<String, String> errorMessage = null;
    
    private String status;
    
    public ResponseObject(Object data,String status) {
        this.data = data;
        this.status = status;
    }
    
    public ResponseObject(Map<String, String> errorMessage, String status) {
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Map<String, String> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResponseObject [data=" + data + ", errorMessage=" + errorMessage + ", status=" + status + "]";
    }
    
    
}
