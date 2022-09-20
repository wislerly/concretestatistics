package com.nmz.concretestatistics.mapper;

import com.nmz.concretestatistics.povo.TypeOfShipping;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TypeOfShippingMapper {

    List<TypeOfShipping> querySelectQuery();

    String getRePrice(@Param("pouring_method") String pouring_method);

    String getMinPrice(@Param("pouring_method") String pouring_method);
}
