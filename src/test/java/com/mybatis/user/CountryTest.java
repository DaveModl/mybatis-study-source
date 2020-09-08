package com.mybatis.user;

import com.google.gson.Gson;
import com.mybatis.use.mapper.country.CountryMapper;
import com.mybatis.use.model.country.Country;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

@Slf4j
public class CountryTest extends BaseTest {
    @Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            List<Country> countries = countryMapper.selectAll();
            log.info("selectAll result:{}",new Gson().toJson(countries));
        }finally {
            sqlSession.close();
        }
    }
}
