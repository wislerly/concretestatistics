package com.nmz.concretestatistics.mapper;

import com.nmz.concretestatistics.povo.AddMaterials;
import com.nmz.concretestatistics.povo.StrengthGrade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StrengthGradeMapper {

    List<StrengthGrade> queryAllSelect();
}
