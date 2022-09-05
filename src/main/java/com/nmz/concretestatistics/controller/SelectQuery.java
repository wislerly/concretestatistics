package com.nmz.concretestatistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.nmz.concretestatistics.mapper.TypeOfShippingMapper;
import com.nmz.concretestatistics.povo.TypeOfShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SelectQuery {

    @Autowired
    private TypeOfShippingMapper typeOfShippingMapper;

    @RequestMapping("SelectQuery")
    public void querySelectQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<TypeOfShipping> list = typeOfShippingMapper.querySelectQuery();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        ArrayList<String> arr = new ArrayList<>();
        for (TypeOfShipping typeOfShipping : list) {
            arr.add(typeOfShipping.ship_name);
        }
        map.put("pouring_position",arr);
        String mapJson = JSONObject.toJSONString(map);
        /*防止中文乱码*/
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(mapJson);
    }
}
