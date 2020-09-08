package com.mybatis.use.mapper.rbac;

import com.mybatis.use.model.rbac.SysRole;
import com.mybatis.use.model.rbac.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserMapper {
    SysUser selectById(Long id);

    List<SysUser> selectAllUser();

    List<SysRole> selectRoleByUserId(Long id);

    int insertSysUser(SysUser sysUser);

    int insertByKey(SysUser sysUser);

    int updateById(SysUser sysUser);

    int deleteById(Long id);

    int deleteById2(SysUser sysUser);

    List<SysRole> selectRoleByUserIdAndRoleEnabled(Long userId,Integer enabled);

    List<SysUser> selectByUser(SysUser sysUser);

    int updateByIdSelective(SysUser sysUser);

    int insertSelective(SysUser sysUser);

    SysUser selectByIdOrUsername(SysUser sysUser);

    List<SysUser> selectByIdList(List<Long> idList);

    int insertList(List<SysUser> users);

    int updateByMap(Map<String,Object> map);

    SysUser selectUserAndRoleById(Long id);
}
