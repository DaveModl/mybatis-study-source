package com.mybatis.use.model.rbac;

import com.mybatis.use.model.constant.Enabled;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
@Getter
@Setter
public class SysRole {
    private Long id;
    private String roleName;
    private Enabled enabled;
    private Long createBy;
    private Date createTime;
}
