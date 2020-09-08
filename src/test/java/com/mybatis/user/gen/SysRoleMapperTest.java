package com.mybatis.user.gen;

import com.mybatis.use.gen.mapper.SysRoleMapper;
import com.mybatis.use.gen.model.NewSysRole;
import com.mybatis.use.gen.model.SysRole;
import com.mybatis.user.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

@Slf4j
public class SysRoleMapperTest extends BaseTest {
    @Test
    public void testSelectAllUserAndRoleWithPrivilege(){
        SqlSession sqlSession = getSqlSession();
        try {
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class);
            List<SysRole> sysRoles = roleMapper.selectAllRoleAndPrivilege();
            log.info("roles-privilege:{}",gson.toJson(sysRoles));
            //依靠Id关联合并，Id列必须在查询中
            log.info("userNum:{}",sysRoles.size());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testNewSelectAllUserAndRoleWithPrivilege(){
        SqlSession sqlSession = getSqlSession();
        try {
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class);
            List<NewSysRole> sysRoles = roleMapper.newSelectAllRoleAndPrivilege();
            log.info("roles-privilege:{}",gson.toJson(sysRoles));
            //依靠Id关联合并，Id列必须在查询中
            log.info("userNum:{}",sysRoles.size());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserId(){
        SqlSession sqlSession = getSqlSession();
        try {
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class);
            List<SysRole> sysRoles = roleMapper.selectRoleByUserId(1L);
            System.out.println(sysRoles);
        }finally {
            sqlSession.close();
        }
    }
}
