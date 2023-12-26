package com.yxz.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yxz.sys.entity.User;
import com.yxz.sys.mapper.UserMapper;
import com.yxz.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yxz
 * @since 2023-11-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    public RedisTemplate redisTemplate;
    @Override
    public Map<String, Object> login(User user) {
        //查询到对应的用户名和密码返回对应token
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        wrapper.eq(User::getPassword,user.getPassword());
        User LoginUser = this.baseMapper.selectOne(wrapper);
        if(LoginUser!=null)
        {
            String key = "user" + UUID.randomUUID();
            LoginUser.setPassword(null);
            redisTemplate.opsForValue().set(key,LoginUser,30, TimeUnit.MINUTES);

            Map<String,Object> data = new HashMap<>();
            data.put("token",key);
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        Object ob = redisTemplate.opsForValue().get(token);
        if(ob!=null){
            User user = JSON.parseObject(JSON.toJSONString(ob),User.class);
            Map<String, Object> data = new HashMap<>();
            data.put("name",user.getUsername());
            data.put("avatar",user.getAvatar());

            List<String> role_list =  this.baseMapper.getRoleNameByUserId(user.getId());
            data.put("roles",role_list);
            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }
    @Override
    public void removeByMyId(Integer id) {
        this.baseMapper.deleteByMyId(id);
    }

    @Override
    public void updateByMyId(User user) {
        this.baseMapper.updateByMyId(user);
    }

    @Override
    public void MySave(User user) {
        this.baseMapper.saveMyId(user);
    }
}
