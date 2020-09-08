package com.mybatis.user.gen;

import com.mybatis.use.gen.mapper.SysPrivilegeMapper;
import com.mybatis.use.gen.model.SysPrivilege;
import com.mybatis.user.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

@Slf4j
public class SysPrivilegeMapperTest extends BaseTest {
    @Test
    public void testSelectPrivilegeByRoleId(){
        SqlSession sqlSession = getSqlSession();
        try {
            SysPrivilegeMapper privilegeMapper = sqlSession.getMapper(SysPrivilegeMapper.class);
            List<SysPrivilege> sysPrivileges = privilegeMapper.selectPrivilegeByRoleId(1L);
            log.info("privilege:{}",gson.toJson(sysPrivileges));
        }finally {
            sqlSession.close();
        }
    }
}
