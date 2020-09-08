package com.mybatis.use.mapper.rbac;

import com.mybatis.use.model.rbac.SysRole;

public interface SysRoleMapper {
    SysRole selectRoleById(Long id);

    int updateById(SysRole sysRole);
}
