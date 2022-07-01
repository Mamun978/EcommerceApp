package com.ecommerce.response;

import java.time.LocalDateTime;

public class ApiResponse {
    
    public final boolean success;
    public final String message;
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public boolean isSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }
    
    public String getTimestamp(){
        return LocalDateTime.now().toString();
    }
}
