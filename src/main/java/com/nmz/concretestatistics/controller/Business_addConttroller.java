package com.nmz.concretestatistics.controller;

import com.nmz.concretestatistics.Utils.ChangeStringToNumber;
import com.nmz.concretestatistics.mapper.BusinessDetialsMapper;
import com.nmz.concretestatistics.povo.BusinessDetials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Business_addConttroller {

    @Autowired
    BusinessDetialsMapper bdm;

    @RequestMapping("businessadd")
    public String busineesAdd() {
        return "business_add";
    }

    @RequestMapping("/business/add")
    public String busineesAdd2(HttpServletRequest request) {
        BusinessDetials bd = new BusinessDetials();
        String comp_name = request.getParameter("comp_name");
        String pouring_position = request.getParameter("pouring_position");
        String pouring_method = request.getParameter("pouring_method");
        String quantities = request.getParameter("quantities");
        String number_of_vehicles = request.getParameter("number_of_vehicles");
        String addmaterialsvalue = request.getParameter("addmaterialsvalue");
        String[] addmaterialsvalues = addmaterialsvalue.split("~");
        String strength_grade = request.getParameter("strength_grade");
        StringBuilder sb = new StringBuilder();
        for (String s : addmaterialsvalues) {
            sb.append(s);
        }
        sb.append(strength_grade);
        String finstrength_grade = sb.toString();
        String unit_price_of_convrete = "";
        String freight = request.getParameter("freight");
        String remarks = request.getParameter("remarks");
        bd.setBusiness_name(comp_name);
        bd.setPouring_position(pouring_position);
        bd.setPouring_method(pouring_method);
        bd.setQuantities(ChangeStringToNumber.format(quantities));
        bd.setNumber_of_vehicles((int) ChangeStringToNumber.format(number_of_vehicles));
        bd.setStrength_grade(finstrength_grade);
        bd.setunit_price_of_convrete(ChangeStringToNumber.format(unit_price_of_convrete));
        bd.setFreight(ChangeStringToNumber.format(freight));
        bd.setTotal_amount(ChangeStringToNumber.format("123"));
        bd.setRemarks(remarks);
        if (bdm.addBusiness(bd) == 1) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
        return "business_add";
    }
}
