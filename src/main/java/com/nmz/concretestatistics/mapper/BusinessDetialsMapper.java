package com.nmz.concretestatistics.mapper;

import com.nmz.concretestatistics.povo.BusinessDetials;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessDetialsMapper {

    int addBusiness(BusinessDetials bd);

    List<BusinessDetials> queryAll(BusinessDetials businessDetials);

    int getIfFloor(@Param("busName") String busName, @Param("busDate") String busDate, @Param("pouring_method") String pouring_method);

}
