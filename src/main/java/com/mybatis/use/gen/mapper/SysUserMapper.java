package com.mybatis.use.gen.mapper;

import com.mybatis.use.gen.model.NewSysUser;
import com.mybatis.use.gen.model.SysUser;
import java.util.List;

public interface SysUserMapper {
    SysUser selectUserAndRoleById(Long id);
    List<SysUser> selectAllUserAndRole();
    List<SysUser> selectAllUserAndRoleWithPrivilege();
    NewSysUser userRoleList(Long id);
}