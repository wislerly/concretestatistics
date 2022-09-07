package com.nmz.concretestatistics.mapper;

import com.nmz.concretestatistics.povo.BusinessDetials;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessDetialsMapper {

    int addBusiness(BusinessDetials bd);

    List<BusinessDetials> queryAll(BusinessDetials businessDetials);

}
