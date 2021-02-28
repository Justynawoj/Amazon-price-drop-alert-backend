package com.amazon_price_drop_alert.exceptions;

public class AsinNotFoundException extends Exception{
    public AsinNotFoundException (String message) {
        super(message);
    }
}
