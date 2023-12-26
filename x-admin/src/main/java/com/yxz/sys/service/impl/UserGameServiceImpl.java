package com.yxz.sys.service.impl;

import com.yxz.sys.entity.Search;
import com.yxz.sys.entity.UserGame;
import com.yxz.sys.mapper.UserGameMapper;
import com.yxz.sys.service.IUserGameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yxz
 * @since 2023-12-13
 */
@Service
public class UserGameServiceImpl extends ServiceImpl<UserGameMapper, UserGame> implements IUserGameService {

    @Override
    public List<UserGame> mysearchlist(String username, String gamename) {
        Search search = new Search(username,gamename);
        return this.baseMapper.mylist(search);
    }

    @Override
    public void removeByUg(String username, String gamename) {
        this.baseMapper.deleteByMyUg(username,gamename);
    }

    @Override
    public void updateUserGame(String username, String gamename, Integer gameHour, String b_username, String b_gamename) {
        this.baseMapper.updateUserGame(username, gamename, gameHour, b_username, b_gamename);
    }

    @Override
    public void saveUserGame(String username, String gamename, Integer gameHour, String b_username, String b_gamename) {
        this.baseMapper.saveUserGame(username, gamename, gameHour, b_username, b_gamename);
    }


}
