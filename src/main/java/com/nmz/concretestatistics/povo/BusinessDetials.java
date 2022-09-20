package com.nmz.concretestatistics.povo;

import com.alibaba.excel.annotation.ExcelProperty;

public class BusinessDetials {

    /*公司名称*/
    public String business_name;

    /*浇筑位置*/
    public String pouring_position;

    /*浇筑方式*/
    public String pouring_method;

    public double quantities;

    public int number_of_vehicles;

    public String strength_grade;

    public double unit_price_of_concrete;

    public double freight;

    public double total_amount;

    public String remarks;

    public String business_date;

    public double pour_price;

    public double strength_price;

    /*砼单价*/
    public double fin_price;

    public String getBusiness_date() {
        return business_date;
    }

    public void setBusiness_date(String business_date) {
        this.business_date = business_date;
    }

    public double getUnit_price_of_concrete() {
        return unit_price_of_concrete;
    }

    public void setUnit_price_of_concrete(double unit_price_of_concrete) {
        this.unit_price_of_concrete = unit_price_of_concrete;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getPouring_position() {
        return pouring_position;
    }

    public void setPouring_position(String pouring_position) {
        this.pouring_position = pouring_position;
    }

    public String getPouring_method() {
        return pouring_method;
    }

    public void setPouring_method(String pouring_method) {
        this.pouring_method = pouring_method;
    }

    public double getQuantities() {
        return quantities;
    }

    public void setQuantities(double quantities) {
        this.quantities = quantities;
    }

    public int getNumber_of_vehicles() {
        return number_of_vehicles;
    }

    public void setNumber_of_vehicles(int number_of_vehicles) {
        this.number_of_vehicles = number_of_vehicles;
    }

    public String getStrength_grade() {
        return strength_grade;
    }

    public void setStrength_grade(String strength_grade) {
        this.strength_grade = strength_grade;
    }


    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getPour_price() {
        return pour_price;
    }

    public void setPour_price(double pour_price) {
        this.pour_price = pour_price;
    }

    public double getStrength_price() {
        return strength_price;
    }

    public void setStrength_price(double strength_price) {
        this.strength_price = strength_price;
    }

    public double getFin_price() {
        return fin_price;
    }

    public void setFin_price(double fin_price) {
        this.fin_price = fin_price;
    }
}
