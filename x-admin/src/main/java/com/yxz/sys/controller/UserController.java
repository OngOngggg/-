package com.yxz.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.javafx.collections.MappingChange;
import com.yxz.common.vo.Result;
import com.yxz.sys.entity.User;
import com.yxz.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yxz
 * @since 2023-11-07
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/all")
    public Result<List<User>> getAllUser(){
        List<User> users = iUserService.list();
        return Result.success(users);
    }

    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody User user)
    {
        Map<String,Object> data= iUserService.login(user);
        if(data!=null) return Result.success(data);
        return Result.fail(20002,"用户名或密码错误");
    }

    @GetMapping("/info")
    public Result<Map<String,Object>> getUserInfo(@RequestParam("token") String token)
    {
        Map<String,Object> data= iUserService.getUserInfo(token);
        if(!data.isEmpty()) return Result.success(data);
        return Result.fail(20003,"登录信息无效，请重新登录");
    }

    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token)
    {
        iUserService.logout(token);
        return Result.success();
    }

    @GetMapping("/list")
    public  Result<Map<String,Object>> getUserList(@RequestParam(value = "username", required = false) String username,
                                               @RequestParam(value = "phone", required = false) String phone,
                                               @RequestParam(value = "pageNo") Long pageNo,
                                               @RequestParam(value = "pageSize") Long pageSize){
        LambdaQueryWrapper<User> wrapper =  new LambdaQueryWrapper<> ();
        wrapper.eq(StringUtils.hasLength(username),User::getUsername,username);
        wrapper.eq(StringUtils.hasLength(phone),User::getPhone,phone);
        wrapper.orderByAsc(User::getId);

        Page<User> page = new Page<>(pageNo,pageSize);
        iUserService.page(page,wrapper);
        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }

    @PostMapping
    public Result<?> addUser(@RequestBody User user){
        iUserService.MySave(user);
        //iUserService.save(user);
        return Result.success("新增成功");
    }

    @PutMapping
    public Result<?> updateUser(@RequestBody User user){
        user.setPassword(null);
        iUserService.updateByMyId(user);
        //iUserService.updateById(user);
        return Result.success("修改用户成功");
    }

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable("id") Integer id){
        User user = iUserService.getById(id);
        return Result.success(user);
    }

    @DeleteMapping("/{id}")
    public Result<User> deleteUserById(@PathVariable("id") Integer id){
        iUserService.removeByMyId(id);
        //iUserService.removeById(id);
        return Result.success("删除用户成功");
    }
}
