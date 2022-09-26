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
        /*浇筑方式对应的价格由前端传入，不与数据库进行交互*/
        ThreadLocalUtil.set("pouring_price", request.getParameter("pouring_price"));
        double pourPrice = getPourPrice(pouring_method, quantities, ThreadLocalUtil.get("pouring_price"));
        /*水泥强度价格，从数据库取值填充到前端直接获取前端数据，避免二次查询数据库*/
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
    * @Description: 取得附加材料的总参考单价
    * @Param: addmaterialsvalues 附加材料的数组
    * @return: 附加材料最后的总单价
    * @Author: 聂明智
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
    public double getPourPrice(String pourMethod, String quantities, String pourPrice) {
        double finPourPrice = 0;
        boolean hasMinFlag = "塔吊".equals(pourMethod) || "汽车吊".equals(pourMethod) || "自卸".equals(pourMethod);
        if (Double.parseDouble(quantities) < 80 && !hasMinFlag) {
            finPourPrice = Arith.div(tosm.getMinPrice(pourMethod), quantities);
            ThreadLocalUtil.set("isMin", true);
        } else {
            ThreadLocalUtil.set("isMin", false);
            finPourPrice = Double.parseDouble(pourPrice);
        }
        return finPourPrice;
    }


    /*插入当前数据，然后看当日总改工作量超过保底更改旧数据*/
    @Transactional
    public void changeOldData(BusinessDetials bd) {

        if (bdm.addBusiness(bd) == 1) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
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


    /*判断当日中是否存在总工作量超过80但仍收了保底的数据*/
    public boolean ifChange(String pourMethod, String quantities, String busName, String busDate) {
        double todayQuantities = Double.parseDouble(bdm.getIfFloor(busName, busDate, pourMethod));
        return todayQuantities > 80 && Double.parseDouble(bdm.hasMin(busName, busDate, pourMethod)) > 0;
    }


}
