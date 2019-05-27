package com.zb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.zb.pojo.Role;

import org.apache.ibatis.annotations.Many;

public interface RoleMapper {
	 //根据用户id查询出所有对应的角色
    @Select("select * from role where id in (select rid from user_role where uid=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.zb.mapper.PermissionMapper.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;


}
