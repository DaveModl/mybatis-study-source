package com.mybatis.user.gen;

import com.mybatis.use.gen.mapper.SysUserMapper;
import com.mybatis.use.gen.model.NewSysUser;
import com.mybatis.use.gen.model.SysUser;
import com.mybatis.user.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

@Slf4j
public class SysUserMapperTest extends BaseTest {
    @Test
    public void testSelectUserAndRoleById(){
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = userMapper.selectUserAndRoleById(1001L);
            log.info("user-role:{}",gson.toJson(user));
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUserAndRole(){
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysUser> sysUsers = userMapper.selectAllUserAndRole();
            log.info("users-roles:{}",gson.toJson(sysUsers));
            //依靠Id关联合并，Id列必须在查询中
            log.info("userNum:{}",sysUsers.size());
        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void testSelectAllUserAndRoleWithPrivilege(){
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysUser> sysUsers = userMapper.selectAllUserAndRoleWithPrivilege();
            log.info("users-roles:{}",gson.toJson(sysUsers));
            //依靠Id关联合并，Id列必须在查询中
            log.info("userNum:{}",sysUsers.size());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testCollectionTop(){
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            NewSysUser newSysUser = userMapper.userRoleList(1L);
            System.out.println(newSysUser);
        }finally {
            sqlSession.close();
        }
    }
}
