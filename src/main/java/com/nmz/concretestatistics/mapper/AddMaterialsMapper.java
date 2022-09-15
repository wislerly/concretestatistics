package com.nmz.concretestatistics.mapper;

import com.nmz.concretestatistics.povo.AddMaterials;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddMaterialsMapper {
    List<AddMaterials> queryAllSelect();

    Double getPrice(@Param("add_name") String add_name);
}
