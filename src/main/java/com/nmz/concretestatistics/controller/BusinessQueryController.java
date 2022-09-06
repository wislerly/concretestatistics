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
    public void getAllDataQuery(HttpServletResponse response) throws Exception {
        List<BusinessDetials> businessDetialslist = businessDetialsMapper.queryAll();
        /*防止中文乱码*/
        response.setCharacterEncoding("utf-8");
        String json = JSONObject.toJSONString(businessDetialslist);
        response.getWriter().println(json);
    }
}
