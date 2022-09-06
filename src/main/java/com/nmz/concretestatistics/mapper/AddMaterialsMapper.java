package com.nmz.concretestatistics.mapper;

import com.nmz.concretestatistics.povo.AddMaterials;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddMaterialsMapper {
    List<AddMaterials> queryAllSelect();
}
