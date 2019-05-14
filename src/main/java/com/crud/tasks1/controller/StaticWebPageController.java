package com.crud.tasks1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class StaticWebPageController {

    @RequestMapping("/")
    public String index(Map<String,Object> model){
        model.put("variable","My Thymeleaf");
        model.put("two",2);
        model.put("multiply","*");
        model.put("equal","=");
        model.put("plus","+");
        model.put("minus","-");
        return "index";
    }
}
