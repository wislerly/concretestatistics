package com.nmz.concretestatistics.mapper;

import com.nmz.concretestatistics.povo.AddMaterials;
import com.nmz.concretestatistics.povo.BusinessDetials;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessDetialsMapper {

    int addBusiness(BusinessDetials bd);

    List<BusinessDetials> queryAll(BusinessDetials businessDetials);

    String hasMin(@Param("busName") String busName, @Param("busDate") String busDate, @Param("pouring_method") String pouring_method);

    String getIfFloor(@Param("busName") String busName, @Param("busDate") String busDate, @Param("pouring_method") String pouring_method);

    void updateList(List<BusinessDetials> list);

    void update(BusinessDetials cbd);


    List<BusinessDetials> queryForUpdate(BusinessDetials businessDetials);

    String getMaxId();

}
