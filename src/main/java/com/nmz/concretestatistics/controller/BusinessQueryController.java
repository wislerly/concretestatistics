package com.nmz.concretestatistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.nmz.concretestatistics.mapper.BusinessDetialsMapper;
import com.nmz.concretestatistics.povo.BusinessDetials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class BusinessQueryController {

    @Autowired
    BusinessDetialsMapper businessDetialsMapper;

    @RequestMapping(value = "businessquery")
    public String businessQuery() {
        return "business_query";
    }

    @RequestMapping(value = "GetAllData")
    public void getAllDataQuery(HttpServletResponse response,HttpServletRequest request) throws Exception {
        String comp_name = request.getParameter("comp_name");
        String add_date = request.getParameter("add_date");
        BusinessDetials businessDetials = new BusinessDetials();
        businessDetials.setBusiness_name(comp_name);
        businessDetials.setBusiness_date(add_date);
        List<BusinessDetials> businessDetialslist = businessDetialsMapper.queryAll(businessDetials);
        /*防止中文乱码*/
        response.setCharacterEncoding("utf-8");
        String json = JSONObject.toJSONString(businessDetialslist);
        response.getWriter().println(json);
    }
}
