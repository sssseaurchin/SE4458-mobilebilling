package com.seaurchin.mobilebilling.exceptions;

public class SubscriberNotFoundException extends RuntimeException{
    public SubscriberNotFoundException(String message){
        super(message);
    }
}
