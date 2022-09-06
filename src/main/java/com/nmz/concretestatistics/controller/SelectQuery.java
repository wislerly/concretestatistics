package com.nmz.concretestatistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.nmz.concretestatistics.mapper.AddMaterialsMapper;
import com.nmz.concretestatistics.mapper.StrengthGradeMapper;
import com.nmz.concretestatistics.mapper.TypeOfShippingMapper;
import com.nmz.concretestatistics.povo.AddMaterials;
import com.nmz.concretestatistics.povo.StrengthGrade;
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

    @Autowired
    private StrengthGradeMapper strengthGradeMapper;

    @Autowired
    private AddMaterialsMapper addMaterialsMapper;


    @RequestMapping("SelectQuery")
    public void querySelectQuery(HttpServletResponse response) throws IOException {
        List<TypeOfShipping> typeOfShippinglist = typeOfShippingMapper.querySelectQuery();
        List<AddMaterials> addMaterialslist = addMaterialsMapper.queryAllSelect();
        List<StrengthGrade> strengthGradelist = strengthGradeMapper.queryAllSelect();


        Map<String, List<String>> map = new HashMap<>();
        ArrayList<String> typeOfShippingarr = new ArrayList<>();
        for (TypeOfShipping typeOfShipping : typeOfShippinglist) {
            typeOfShippingarr.add(typeOfShipping.ship_name);
        }

        ArrayList<String> addMaterialsarr = new ArrayList<>();
        for (AddMaterials addMaterials : addMaterialslist) {
            addMaterialsarr.add(addMaterials.add_name);
        }
        ArrayList<String> strengthGradearr = new ArrayList<>();
        for (StrengthGrade strengthGrade : strengthGradelist) {
            strengthGradearr.add(strengthGrade.strength_name);
        }

        map.put("pouring_position", typeOfShippingarr);
        map.put("strength_grade", strengthGradearr);
        map.put("add_materials", addMaterialsarr);
        String mapJson = JSONObject.toJSONString(map);
        /*防止中文乱码*/
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(mapJson);
    }
}
