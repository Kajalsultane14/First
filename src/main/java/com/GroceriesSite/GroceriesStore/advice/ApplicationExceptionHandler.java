package com.GroceriesSite.GroceriesStore.advice;

import com.GroceriesSite.GroceriesStore.exception.HolidayException;
import com.GroceriesSite.GroceriesStore.exception.ProductEfficiencyException;
import com.GroceriesSite.GroceriesStore.exception.ProductNotAvailableException;
import com.GroceriesSite.GroceriesStore.exception.RateExceedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    private String error;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ProductNotAvailableException.class)
    public Map<String,String> handleException(ProductNotAvailableException ex)
    {
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;

    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ProductEfficiencyException.class)
    public Map<String,String> handleException(ProductEfficiencyException ex)
    {
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RateExceedException.class)
    public Map<String,String> handleException(RateExceedException ex)
    {
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HolidayException.class)
    public Map<String,String> handleException(HolidayException ex)
    {
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;

    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleException(MethodArgumentNotValidException ex)
    {
        Map<String,String> errorMap=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{errorMap.put(error.getField(), error.getDefaultMessage());

        });
        return errorMap;

    }
}
