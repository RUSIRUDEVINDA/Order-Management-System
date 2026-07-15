package com.order.service.common;

public class ErrorOrderResponse implements OrderResponse {
    public final String errorMessage;

    public ErrorOrderResponse(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
