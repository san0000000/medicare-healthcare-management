package com.medicare.dto;

public class ApiResponse {

    private String message;
    private int status;

    public ApiResponse(){

    }

    public ApiResponse(String message, int status)
    {
        this.message=message;
        this.status=status;
    }

    public String getMessage()
    {
        return message;
    }
    public int getStatus()
    {
        return status;
    }
    
}
