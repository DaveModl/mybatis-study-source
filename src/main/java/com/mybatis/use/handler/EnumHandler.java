package com.mybatis.use.handler;

import com.mybatis.use.model.constant.NewEnabled;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EnumHandler implements TypeHandler<NewEnabled> {
    private final Map<Integer,NewEnabled> enabledMap = new HashMap<>();

    public EnumHandler() {
        for (NewEnabled enabled : NewEnabled.values()){
            enabledMap.put(enabled.getValue(),enabled);
        }
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, NewEnabled newEnabled, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,newEnabled.getValue());
    }

    @Override
    public NewEnabled getResult(ResultSet resultSet, String s) throws SQLException {
        Integer value = resultSet.getInt(s);
        return enabledMap.get(value);
    }

    @Override
    public NewEnabled getResult(ResultSet resultSet, int i) throws SQLException {
        Integer value = resultSet.getInt(i);
        return enabledMap.get(value);
    }

    @Override
    public NewEnabled getResult(CallableStatement callableStatement, int i) throws SQLException {
        Integer value = callableStatement.getInt(i);
        return enabledMap.get(value);
    }
}
