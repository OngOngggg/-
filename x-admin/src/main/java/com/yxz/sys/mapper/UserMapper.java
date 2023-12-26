package com.yxz.sys.mapper;

import com.yxz.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yxz
 * @since 2023-11-07
 */
public interface UserMapper extends BaseMapper<User> {
    public List<String> getRoleNameByUserId(Integer id);

    void deleteByMyId(Integer id);

    void updateByMyId(User user);

    void saveMyId(User user);
}
