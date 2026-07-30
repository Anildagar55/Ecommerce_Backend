package com.example.Eco_Backend.ExceptionHandler;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String mess){
        super(mess);
    }
}
