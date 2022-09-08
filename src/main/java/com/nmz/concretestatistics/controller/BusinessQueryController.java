package com.nmz.concretestatistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import java.util.Map;

@Controller
public class BusinessQueryController {

    @Autowired
    BusinessDetialsMapper businessDetialsMapper;

    @RequestMapping(value = "businessquery")
    public String businessQuery() {
        return "business_query";
    }

    @RequestMapping(value = "GetAllData")
    public void getAllDataQuery(HttpServletResponse response, HttpServletRequest request) throws Exception {
        String comp_name = request.getParameter("comp_name");
        String add_date = request.getParameter("add_date");
        BusinessDetials businessDetials = new BusinessDetials();
        businessDetials.setBusiness_name(comp_name);
        businessDetials.setBusiness_date(add_date);
        PageHelper.startPage(1 , 1);
        List<BusinessDetials> businessDetialslist = businessDetialsMapper.queryAll(businessDetials);
        //根据查询的数据列表，得到分页的结果对象
        PageInfo<BusinessDetials> pageList = new PageInfo<BusinessDetials>(businessDetialslist);
        List<BusinessDetials> datas = pageList.getList();
        /*防止中文乱码*/
        response.setCharacterEncoding("utf-8");
        String json = JSONObject.toJSONString(datas);
        response.getWriter().println(json);
    }
}
