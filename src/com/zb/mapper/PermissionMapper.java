package com.zb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.zb.pojo.Permission;

public interface PermissionMapper {
	
	//查询与role关联的所有的权限
    @Select("select * from permission where id in (select peid from role_permission where rid=#{id} )")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;

}
