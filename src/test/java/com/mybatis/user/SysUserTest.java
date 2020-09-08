package com.mybatis.user;

import com.mybatis.use.mapper.rbac.SysUserMapper;
import com.mybatis.use.model.rbac.SysRole;
import com.mybatis.use.model.rbac.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

@Slf4j
public class SysUserTest extends BaseTest {
    @Test
    public void testSelectById() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser sysUser = sysUserMapper.selectById(1L);
            log.info("result:{}", gson.toJson(sysUser));
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUser() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysUser> sysUsers = sysUserMapper.selectAllUser();
            log.info("result:{}", gson.toJson(sysUsers));
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserId() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysRole> sysRoles = sysUserMapper.selectRoleByUserId(1L);
            log.info("result:{}", gson.toJson(sysRoles));
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertSysUser() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser sysUser = SysUser.builder()
                    .userName("test02")
                    .userPassword("123131")
                    .userEmail("testO2@test.com")
                    .userInfo("测试用户2")
                    .headImg(new byte[]{1, 2, 3})
                    .createTime(new Date())
                    .build();
            int rows = userMapper.insertSysUser(sysUser);
            log.info("rows:{}", rows);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsetByKey() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser sysUser = SysUser.builder()
                    .userName("test03")
                    .userPassword("123321")
                    .userEmail("test3@test.com")
                    .userInfo("测试用户3")
                    .headImg(new byte[]{1, 1, 1})
                    .createTime(new Date())
                    .build();
            int rows = userMapper.insertByKey(sysUser);
            log.info("rows:{}", rows);
            sqlSession.commit();
            //回写自增id
            log.info("id:{}", sysUser.getId());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = userMapper.selectById(1003L);
            user.setUserName("test02-new");
            int rows = userMapper.updateById(user);
            log.info("update rows:{}", rows);
            sqlSession.commit();
            user = userMapper.selectById(1003L);
            log.info("user:{}", gson.toJson(user));
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user_1 = sysUserMapper.selectById(1004L);
            log.info("user_1:{}", gson.toJson(user_1));
            int rows = sysUserMapper.deleteById(1004L);
            log.info("delete rows:{}", rows);

            //对象
            SysUser user_2 = sysUserMapper.selectById(1003L);
            log.info("user_2:{}", user_2);
            rows = sysUserMapper.deleteById2(user_2);
            log.info("delete rows:{}", rows);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserIdAndRoleEnabled() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysRole> sysRoles = userMapper.selectRoleByUserIdAndRoleEnabled(1L, 1);
            log.info("sysRoles:{}", gson.toJson(sysRoles));
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByUser() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser queryName = new SysUser();
            queryName.setUserName("ad");
            List<SysUser> userList = userMapper.selectByUser(queryName);
            log.info("userList:{}", gson.toJson(userList));

            SysUser queryEmail = new SysUser();
            queryEmail.setUserEmail("test@user.com");
            List<SysUser> userList_2 = userMapper.selectByUser(queryEmail);
            log.info("result:{}", gson.toJson(userList_2));

            SysUser queryAll = new SysUser();
            queryAll.setUserName("ad");
            queryAll.setUserEmail("test@user.com");
            List<SysUser> userList_3 = userMapper.selectByUser(queryAll);
            log.info("result:{}", gson.toJson(userList_3));
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByIdSelective() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser sysUser = new SysUser();
            sysUser.setId(1001L);
            sysUser.setUserName("test-01");
            int rows = userMapper.updateByIdSelective(sysUser);
            log.info("update rows:{}",rows);
            sqlSession.commit();
            SysUser user = userMapper.selectById(1001L);
            log.info("user:{}",gson.toJson(user));
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertSelective(){
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = SysUser.builder()
                    .userName("test-02")
                    .userPassword("123321")
                    .userInfo("测试用户2")
                    .createTime(new Date())
                    .build();
            userMapper.insertSelective(user);
            sqlSession.commit();
            SysUser newUser = userMapper.selectById(user.getId());
            log.info("insert:{}",gson.toJson(newUser));
        }finally {
            sqlSession.close();
        }
    }
    
    @Test
    public void testSelectByIdOrUsername(){
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser queryId = new SysUser();
            queryId.setId(1L);
            queryId.setUserName("admin");
            SysUser user = userMapper.selectByIdOrUsername(queryId);
            log.info("user:{}",gson.toJson(user));

            queryId.setId(null);
            SysUser user_2 = userMapper.selectByIdOrUsername(queryId);
            log.info("user_2:{}",gson.toJson(user_2));

            queryId.setUserName(null);
            SysUser user_3 = userMapper.selectByIdOrUsername(queryId);
            log.info("user_3:{}",gson.toJson(user_3));
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdList(){
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            List<Long> ids = new ArrayList<>();
            ids.add(1L);
            ids.add(1001L);
            ids.add(1005L);
            List<SysUser> userList = userMapper.selectByIdList(ids);
            log.info("result:{}",gson.toJson(userList));
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertList(){
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysUser> users = new ArrayList<>();
            for (int i = 0; i <2 ; i++) {
                SysUser user = SysUser.builder()
                        .userName("test-0"+(i+3))
                        .userPassword("123456")
                        .userEmail("test0"+ (i+2) +"@user.com")
                        .build();
                users.add(user);
            }
            int rows = userMapper.insertList(users);
            log.info("insert rows:{}",rows);
            for (SysUser u: users) {
                log.info("primary key:{}",u.getId());
            }
            sqlSession.commit();
            List<SysUser> usersList = userMapper.selectAllUser();
            log.info("userList:{}",gson.toJson(usersList));
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByMap(){
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            Map<String,Object> map = new HashMap<>();
            map.put("id",1L);
            map.put("user_password","12345678");
            userMapper.updateByMap(map);
            sqlSession.commit();
            SysUser sysUser = userMapper.selectById(1L);
            log.info("sysUser:{}",gson.toJson(sysUser));
        }finally {
            sqlSession.close();
        }
    }

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
}
