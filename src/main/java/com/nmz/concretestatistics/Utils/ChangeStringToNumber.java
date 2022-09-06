package com.nmz.concretestatistics.Utils;

public class ChangeStringToNumber {
    public static double format(String str){
        if ("".equals(str) || str == null) {
            return 0.0;
        } else {
            return Double.parseDouble(str);
        }
    }
}
