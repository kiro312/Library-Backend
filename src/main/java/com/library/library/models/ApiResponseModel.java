package com.library.library.models;

import org.springframework.stereotype.Service;

@Service
public class ApiResponseModel {
    private String message;
    private boolean status;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ApiResponseModel(String message, boolean status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public ApiResponseModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}