package com.mybatis.use.gen.mapper;


import com.mybatis.use.gen.model.NewSysRole;
import com.mybatis.use.gen.model.SysRole;

import java.util.List;

public interface SysRoleMapper {
    SysRole selectRoleById(Long id);
    List<SysRole> selectAllRoleAndPrivilege();
    List<NewSysRole> newSelectAllRoleAndPrivilege();
    List<SysRole> selectRoleByUserId(Long userId);
}