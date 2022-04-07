package com.example.mangxahoi.dto.respon;

public class ResponseMess {
    private String message;

    public ResponseMess() {
    }

    public ResponseMess(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
