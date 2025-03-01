package com.luv2code.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {
    //new a controller method for show initial HTML form

    @GetMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    //need a controller method to process the HTML form

    @RequestMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    //need a controller method to read form data and
    // add data to the model

    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model){
        //read the request parameter from the html form

        String theName = request.getParameter("studentName");

        //convert data to all caps
        theName = theName.toUpperCase();

        //create a message
        String result = "Yo! " + theName;

        //add the message to the model
        model.addAttribute("message", result);
        return "helloworld";
    }

    @PostMapping("/processFormVersionThree")
    public String processFormVersionThree(@RequestParam("studentName") String theName, Model model){

        //convert data to all caps
        theName = theName.toUpperCase();

        //create a message
        String result = "Hey my friend from v3! " + theName;

        //add the message to the model
        model.addAttribute("message", result);
        return "helloworld";
    }
}








