package com.mybatis.user;

import com.mybatis.use.mapper.rbac.SysRoleMapper;
import com.mybatis.use.model.constant.Enabled;
import com.mybatis.use.model.rbac.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
@Slf4j
public class SysRoleTest extends BaseTest {
    @Test
    public void testSelectRoleById(){
        SqlSession sqlSession = getSqlSession();
        try {
            SysRoleMapper mapper = sqlSession.getMapper(SysRoleMapper.class);
            SysRole sysRole = mapper.selectRoleById(2L);
            log.info("role:{}",gson.toJson(sysRole));
            sysRole.setEnabled(Enabled.disabled);
            int rows = mapper.updateById(sysRole);
            log.info("rows:{}",rows);
        }finally {
            sqlSession.close();
        }
    }
}
