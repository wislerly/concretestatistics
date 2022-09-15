package com.nmz.concretestatistics.controller;

import com.nmz.concretestatistics.Utils.ChangeStringToNumber;
import com.nmz.concretestatistics.mapper.AddMaterialsMapper;
import com.nmz.concretestatistics.mapper.BusinessDetialsMapper;
import com.nmz.concretestatistics.mapper.StrengthGradeMapper;
import com.nmz.concretestatistics.mapper.TypeOfShippingMapper;
import com.nmz.concretestatistics.povo.BusinessDetials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Business_addController {

    @Autowired
    BusinessDetialsMapper bdm;

    @Autowired
    AddMaterialsMapper amm;

    @Autowired
    StrengthGradeMapper sgm;

    @Autowired
    TypeOfShippingMapper tosm;

    @RequestMapping("businessadd")
    public String busineesAdd() {
        return "business_add";
    }

    @RequestMapping("/business/add")
    public String busineesAdd2(HttpServletRequest request) {
        BusinessDetials bd = new BusinessDetials();
        String business_date = request.getParameter("add_date");
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
        bd.setUnit_price_of_concrete(ChangeStringToNumber.format(unit_price_of_convrete));
        bd.setFreight(ChangeStringToNumber.format(freight));
        double totalAmount = getprice(addmaterialsvalues, strength_grade, pouring_method, comp_name, business_date, quantities);
        bd.setTotal_amount(totalAmount + Double.parseDouble(freight));
        bd.setBusiness_date(business_date);
        bd.setRemarks(remarks);
        if (bdm.addBusiness(bd) == 1) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
        return "business_add";
    }


    /**
    * @Description: 根据传入参数返回总额，方法可以拆
    * @Param: [addmater, sgrade, pouring_method, busName, busDate, quantities]
    * @return: double
    * @Author: 聂明智
    * @Date: 2022/9/15-15:08
    */
    public double getprice(String[] addmater, String sgrade, String pouring_method, String busName, String busDate, String quantities) {
        Double addmaterPrice = 0.0;
        for (String s : addmater) {
            addmaterPrice += amm.getPrice(s);
        }
        addmaterPrice += sgm.getPrice(sgrade);

        addmaterPrice += addmaterPrice * Double.parseDouble(quantities);

        int i = bdm.getIfFloor(busName, busDate, pouring_method);
        if (Double.parseDouble(quantities) < 80 || i > 1) {
            addmaterPrice += tosm.getRePrice(pouring_method) * Double.parseDouble(quantities);
        } else {
            /*没有交过保底*/
            addmaterPrice += tosm.getMinPrice(pouring_method);
        }
        return addmaterPrice;
    }
}
