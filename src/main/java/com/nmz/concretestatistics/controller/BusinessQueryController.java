package com.nmz.concretestatistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.nmz.concretestatistics.mapper.BusinessDetialsMapper;
import com.nmz.concretestatistics.povo.BusinessDetials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BusinessQueryController {

    @Autowired
    BusinessDetialsMapper businessDetialsMapper;

    @RequestMapping(value = "/business/query")
    public String businessQuery() {
        List<BusinessDetials> businessDetialslist = businessDetialsMapper.queryAll();
        return JSONObject.toJSONString(businessDetialslist);
    }
}
