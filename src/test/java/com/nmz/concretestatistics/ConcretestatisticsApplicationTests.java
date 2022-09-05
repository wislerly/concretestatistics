package com.nmz.concretestatistics;

import com.nmz.concretestatistics.mapper.TypeOfShippingMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ConcretestatisticsApplicationTests {

    @Autowired
    JdbcTemplate jt;

    @Autowired
    TypeOfShippingMapper ty;

    @Test
    void contextLoads() {
    }


    @Test
    public void test(){
        List list = ty.querySelectQuery();

    }

}
