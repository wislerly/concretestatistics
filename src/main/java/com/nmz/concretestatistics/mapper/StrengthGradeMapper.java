package com.nmz.concretestatistics.mapper;

import com.nmz.concretestatistics.povo.AddMaterials;
import com.nmz.concretestatistics.povo.StrengthGrade;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StrengthGradeMapper {

    List<StrengthGrade> queryAllSelect();

    String getPrice(@Param("strength_name") String strength_name);
}
