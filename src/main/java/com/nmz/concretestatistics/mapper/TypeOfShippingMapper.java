package com.nmz.concretestatistics.mapper;

import com.nmz.concretestatistics.povo.TypeOfShipping;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TypeOfShippingMapper {

    List<TypeOfShipping> querySelectQuery();
}
