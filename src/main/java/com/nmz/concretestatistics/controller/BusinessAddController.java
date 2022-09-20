package com.nmz.concretestatistics.controller;

import com.nmz.concretestatistics.Utils.Arith;
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
public class BusinessAddController {

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
        double addMaterialPrice = getAddmaterialsPrice(addmaterialsvalues);
        String strength_grade = request.getParameter("strength_grade");
        /*浇筑方式对应的价格由前端传入，不与数据库进行交互*/
        String pouring_price = request.getParameter("pouring_price");
        double pourPrice = getPourPrice(pouring_method, quantities, comp_name, business_date, pouring_price);
        /*水泥强度价格，从数据库取值填充到前端直接获取前端数据，避免二次查询数据库*/
        String greprice = request.getParameter("greprice");
        double floatprice = ChangeStringToNumber.format(request.getParameter("floatprice"));
        String freight = request.getParameter("freight");
        double finStrengthPrice = Arith.add(Double.parseDouble(greprice), floatprice);
        double finMPrice = Arith.add(addMaterialPrice, finStrengthPrice);
        String finStrengthGrade = getFinstrengthGrade(addmaterialsvalues, strength_grade);
        double unit_price_of_convrete = Arith.add(pourPrice, finMPrice);
        String remarks = request.getParameter("remarks");
        bd.setBusiness_name(comp_name);
        bd.setPouring_position(pouring_position);
        bd.setPouring_method(pouring_method);
        bd.setQuantities(ChangeStringToNumber.format(quantities));
        bd.setNumber_of_vehicles((int) ChangeStringToNumber.format(number_of_vehicles));
        bd.setStrength_grade(finStrengthGrade);
        bd.setUnit_price_of_concrete(unit_price_of_convrete);
        bd.setFreight(ChangeStringToNumber.format(freight));
        bd.setTotal_amount(unit_price_of_convrete);
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
    * @Description: 取得附加材料的总参考单价
    * @Param: addmaterialsvalues 附加材料的数组
    * @return: 附加材料最后的总单价
    * @Author: 聂明智
    * @Date: 2022/9/20-9:50
    */
    public double getAddmaterialsPrice(String[] addmaterialsvalues) {
        double addmaterialsPrice = 0.0;
        for (String s : addmaterialsvalues) {
            addmaterialsPrice += ChangeStringToNumber.format(amm.getPrice(s));
        }
        return addmaterialsPrice;
    }


    /**
    * @Description: 返回水泥强度和附加材料最后的拼接值
    * @Param: [addMaterials 附加材料数组, strengthGrade 水泥强度]
    * @return: 最后的拼接材料
    * @Author: 聂明智
    * @Date: 2022/9/20-16:46
    */
    public String getFinstrengthGrade(String[] addMaterials, String strengthGrade) {
        StringBuilder sb = new StringBuilder();
        sb.append(strengthGrade);
        for (String s : addMaterials) {
            sb.append(s);
        }
        return sb.toString();
    }


    /**
    * @Description: 根据工程量公司名，交易日期，浇筑方式判断是否要收取保底返回最终的浇筑单价
    * @Param: [pourMethod 浇筑方式, quantities 工程量, busName 公司名, busDate 交易日期]
    * @return: double 最终浇筑单价
    * @Author: 聂明智
    * @Date: 2022/9/20-18:01
    */
    public double getPourPrice(String pourMethod, String quantities, String busName, String busDate, String pourPrice) {
        double finPourPrice = 0;
        double todayQuantities = Arith.add(quantities, bdm.getIfFloor(busName, busDate, pourMethod));
        boolean hasMinFlag = "塔吊".equals(pourMethod) || "汽车吊".equals(pourMethod) || "自卸".equals(pourMethod);
        if (todayQuantities < 80 || !hasMinFlag) {
            finPourPrice = Arith.mul(tosm.getMinPrice(pourMethod), quantities);
        } else {
            finPourPrice = Double.parseDouble(pourPrice);
        }
        return finPourPrice;
    }
}
