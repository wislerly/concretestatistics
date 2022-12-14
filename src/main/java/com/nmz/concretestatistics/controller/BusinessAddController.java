package com.nmz.concretestatistics.controller;

import com.nmz.concretestatistics.Utils.Arith;
import com.nmz.concretestatistics.Utils.ThreadLocalUtil;
import com.nmz.concretestatistics.mapper.AddMaterialsMapper;
import com.nmz.concretestatistics.mapper.BusinessDetialsMapper;
import com.nmz.concretestatistics.mapper.StrengthGradeMapper;
import com.nmz.concretestatistics.mapper.TypeOfShippingMapper;
import com.nmz.concretestatistics.povo.BusinessDetials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.nmz.concretestatistics.Utils.ChangeStringToNumber.format;

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
        /*????????????????????????????????????????????????????????????????????????*/
        ThreadLocalUtil.set("pouring_price", request.getParameter("pouring_price"));
        double pourPrice = getPourPrice(pouring_method, quantities, ThreadLocalUtil.get("pouring_price"));
        /*????????????????????????????????????????????????????????????????????????????????????????????????????????????*/
        String greprice = request.getParameter("tgreprice");
        double floatprice = format(request.getParameter("floatprice"));
        String freight = request.getParameter("freight");
        double finStrengthPrice = Arith.add(format(greprice), floatprice);
        double finMPrice = Arith.add(addMaterialPrice, finStrengthPrice);
        String finStrengthGrade = getFinstrengthGrade(addmaterialsvalues, strength_grade);
        double unit_price_of_convrete = Arith.add(pourPrice, finMPrice);
        String remarks = request.getParameter("remarks");
        bd.setBusiness_name(comp_name);
        bd.setPouring_position(pouring_position);
        bd.setPouring_method(pouring_method);
        bd.setQuantities(format(quantities));
        bd.setNumber_of_vehicles((int) format(number_of_vehicles));
        bd.setStrength_grade(finStrengthGrade);
        bd.setUnit_price_of_concrete(unit_price_of_convrete);
        bd.setFreight(format(freight));
        bd.setTotal_amount(Arith.add(Arith.mul(unit_price_of_convrete, format(quantities)), format(freight)));
        bd.setBusiness_date(business_date);
        bd.setRemarks(remarks);
        bd.setStrength_price(finMPrice);
        bd.setPour_price(pourPrice);
        bd.setIsminflag(Boolean.TRUE.equals(ThreadLocalUtil.get("isMin")) ? "1" : "0");
        bd.setBusid(String.valueOf(format(bdm.getMaxId()) + 1));
        changeOldData(bd);
        return "business_add";
    }




    /**
    * @Description: ????????????????????????????????????
    * @Param: addmaterialsvalues ?????????????????????
    * @return: ??????????????????????????????
    * @Author: ?????????
    * @Date: 2022/9/20-9:50
    */
    public double getAddmaterialsPrice(String[] addmaterialsvalues) {
        double addmaterialsPrice = 0.0;
        for (String s : addmaterialsvalues) {
            addmaterialsPrice += format(amm.getPrice(s));
        }
        return addmaterialsPrice;
    }


    /**
    * @Description: ???????????????????????????????????????????????????
    * @Param: [addMaterials ??????????????????, strengthGrade ????????????]
    * @return: ?????????????????????
    * @Author: ?????????
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
    * @Description: ????????????????????????????????????????????????????????????????????????????????????????????????????????????
    * @Param: [pourMethod ????????????, quantities ?????????, busName ?????????, busDate ????????????]
    * @return: double ??????????????????
    * @Author: ?????????
    * @Date: 2022/9/20-18:01
    */
    public double getPourPrice(String pourMethod, String quantities, String pourPrice) {
        double finPourPrice = 0;
        boolean hasMinFlag = "??????".equals(pourMethod) || "?????????".equals(pourMethod) || "??????".equals(pourMethod);
        if (Double.parseDouble(quantities) < 80 && !hasMinFlag) {
            finPourPrice = Arith.div(tosm.getMinPrice(pourMethod), quantities);
            ThreadLocalUtil.set("isMin", true);
        } else {
            ThreadLocalUtil.set("isMin", false);
            finPourPrice = Double.parseDouble(pourPrice);
        }
        return finPourPrice;
    }


    /*??????????????????????????????????????????????????????????????????????????????*/
    @Transactional
    public void changeOldData(BusinessDetials bd) {

        if (bdm.addBusiness(bd) == 1) {
            System.out.println("????????????");
        } else {
            System.out.println("????????????");
        }
        if(ifChange(bd.getPouring_method(), String.valueOf(bd.getQuantities()), bd.getBusiness_name(), bd.getBusiness_date())){
            List<BusinessDetials> list = bdm.queryForUpdate(bd);
            for (BusinessDetials cbd : list) {
                double newPourPrice = Double.parseDouble(ThreadLocalUtil.get("pouring_price"));
                cbd.setPour_price(newPourPrice);
                double unit_price_of_concrete = newPourPrice + cbd.getStrength_price();
                cbd.setUnit_price_of_concrete(unit_price_of_concrete);
                cbd.setTotal_amount(Arith.add(Arith.mul(unit_price_of_concrete, cbd.getQuantities()),cbd.getFreight()));
                cbd.setIsminflag("0");
                bdm.update(cbd);
            }

        }
        ThreadLocalUtil.removeValue("isMin");
        ThreadLocalUtil.removeValue("pouring_price");

    }


    /*?????????????????????????????????????????????80???????????????????????????*/
    public boolean ifChange(String pourMethod, String quantities, String busName, String busDate) {
        double todayQuantities = Double.parseDouble(bdm.getIfFloor(busName, busDate, pourMethod));
        return todayQuantities > 80 && Double.parseDouble(bdm.hasMin(busName, busDate, pourMethod)) > 0;
    }


}
