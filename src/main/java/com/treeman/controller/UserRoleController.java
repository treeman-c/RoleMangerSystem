package com.treeman.controller;

import com.treeman.pojo.UserRole;
import com.treeman.service.IUseRoleService;
import com.treeman.serviceimpl.UserRoleServiceImpl;
import com.treeman.unit.ResultCode;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRoleController {

    @Autowired
    private IUseRoleService userRoleService;

    @GetMapping("/get-user")
    public UserRole getUser(@RequestParam String username){
        return userRoleService.getOneByUsername(username);
    }

    @GetMapping("/get-list")
    @PreAuthorize("hasAnyRole('admin')")  //给角色为管理员的用户授权访问该页面
    public ResultCode<List<UserRole>> getList(){
        return ResultCode.ok(userRoleService.getAll());
    }

    @PostMapping("/get-list")
    @PreAuthorize("hasAnyRole('user')")
    public  ResultCode<List<UserRole>> getAll(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        return ResultCode.ok(userRoleService.getPage(pageNum,pageSize));
    }

    @PutMapping("admin")
    @PreAuthorize("hasAnyRole('admin')")
    public ResultCode update(@RequestBody UserRole userRole){
        if(userRoleService.updateOne(userRole)){
            return ResultCode.ok();
        }
        return ResultCode.failed();
    }

    @DeleteMapping("admin/{id}")
    @PreAuthorize("hasAnyRole('admin')")
    public ResultCode delete(@PathVariable("id") long id){
        if(userRoleService.deleteById(id)) return ResultCode.ok();
        return ResultCode.failed();
    }

}
