package com.abdou.api.utils;

import java.time.LocalDate;

import com.abdou.api.models.Taxi;

import lombok.Data;


@Data
public class RideDto { 
    private Long id ;
    private double price ;
    private String from ;
    private String to ; 
    private LocalDate date ;
    
    private Taxi taxi ;
}
