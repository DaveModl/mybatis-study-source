package com.mybatis.use.mapper.country;

import com.mybatis.use.model.country.Country;

import java.util.List;

public interface CountryMapper {
    List<Country> selectAll();
}
