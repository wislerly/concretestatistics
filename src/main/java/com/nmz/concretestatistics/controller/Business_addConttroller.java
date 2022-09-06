package com.nmz.concretestatistics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Business_addConttroller {

    @RequestMapping("businessadd")
    public String busineesAdd() {
        return "business_add";
    }

    @RequestMapping("/business/add")
    public String busineesAdd2(HttpServletRequest request) {
        String name = request.getParameter("comp_name");
        String addmaterialsvalue = request.getParameter("addmaterialsvalue");
        return "business_add";
    }
}
