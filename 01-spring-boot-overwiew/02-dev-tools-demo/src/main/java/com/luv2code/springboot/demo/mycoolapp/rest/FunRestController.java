package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    //expose  "/" that return "Hello world"
    @GetMapping("/")
    public String sayHello(){
        return "Hello world!";
    }

    //expose a new end point for "workout"
    @GetMapping("/workout")
    public String getDailyWorkout(){
        return "Run hard 5k!";
    }

    // expose a new end point for "fortune"
    @GetMapping("/fortune")
    public String getDailyFortune(){
        return "Today is your lucky day.";
    }




}
