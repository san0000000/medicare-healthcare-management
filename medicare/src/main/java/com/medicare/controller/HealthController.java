package com.medicare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@restcontroller :- tells Spring: "This class will handle REST API requests."
@RestController //sets a common URL prefix /api/health
@RequestMapping ("/api")
public class HealthController {
    //When somebody sends a GET request to /api/health, execute this method
    @GetMapping("/health") 
    public String health()
    {
        return "MediCare application is running";
    }
}
