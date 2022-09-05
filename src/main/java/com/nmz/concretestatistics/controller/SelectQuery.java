package com.nmz.concretestatistics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SelectQuery {

    @RequestMapping("SelectQuery")
    public String querySelectQuery() {
        System.out.println("进来了");
        return "business_add";
    }
}
