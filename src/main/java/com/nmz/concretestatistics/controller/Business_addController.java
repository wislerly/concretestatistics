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

    BusinessDetialsMapper bdm;

    AddMaterialsMapper amm;

    StrengthGradeMapper sgm;

    TypeOfShippingMapper tosm;

    @Autowired
    public void setBdm(BusinessDetialsMapper bdm) {
        this.bdm = bdm;
    }

    @Autowired
    public void setAmm(AddMaterialsMapper amm) {
        this.amm = amm;
    }

    @Autowired
    public void setSgm(StrengthGradeMapper sgm) {
        this.sgm = sgm;
    }

    @Autowired
    public void setTosm(TypeOfShippingMapper tosm) {
        this.tosm = tosm;
    }

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
        String floatprice = request.getParameter("floatprice");
        if (floatprice == null|| "".equals(floatprice)) {
            floatprice = "0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(strength_grade);
        for (String s : addmaterialsvalues) {
            sb.append(s);
        }
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
        double totalAmount = getprice(bd, addmaterialsvalues, strength_grade, pouring_method, comp_name, business_date, quantities, floatprice);
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
    public double getprice(BusinessDetials bd, String[] addmater, String sgrade, String pouring_method, String busName, String busDate, String quantities, String floatprice) {
        Double addmaterPrice = 0.0;
        for (String s : addmater) {
            addmaterPrice += ChangeStringToNumber.format(amm.getPrice(s));
        }

        addmaterPrice += sgm.getPrice(sgrade);

        bd.setStrength_price(addmaterPrice);

        addmaterPrice += Double.parseDouble(floatprice);


        addmaterPrice += addmaterPrice * Double.parseDouble(quantities);

        int i = bdm.getIfFloor(busName, busDate, pouring_method);

        double strength_price = 0.0;
        if (Double.parseDouble(quantities) > 80 || i > 1) {
            strength_price += tosm.getRePrice(pouring_method);
        } else {
            /*没有交过保底*/
            strength_price += tosm.getMinPrice(pouring_method);
        }
        bd.setPour_price(strength_price);
        addmaterPrice += strength_price * Double.parseDouble(quantities);;

        return addmaterPrice;
    }
}
