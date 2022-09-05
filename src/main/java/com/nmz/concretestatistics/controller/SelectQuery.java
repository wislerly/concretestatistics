package com.nmz.concretestatistics.controller;

import com.nmz.concretestatistics.mapper.TypeOfShippingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class SelectQuery {

    @Autowired
    private TypeOfShippingMapper typeOfShippingMapper;

    @RequestMapping("SelectQuery")
    public String querySelectQuery() {
        Map map = typeOfShippingMapper.querySelectQuery();
        System.out.println(map);
        return "business_add";
    }
}
