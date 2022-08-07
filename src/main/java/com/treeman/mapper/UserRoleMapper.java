package com.treeman.mapper;

import com.treeman.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    public List<UserRole> getAll();
    public List<UserRole> getPage(int pageNum,int pageSize);
    public int insert(UserRole userRole);
    public UserRole getOneById(long id);
    public UserRole getOneByUsername(String name);
    public int updateOne(UserRole userRole);
    public int deleteById(long id);

}
