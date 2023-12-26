package com.yxz.sys.mapper;

import com.yxz.sys.entity.Search;
import com.yxz.sys.entity.UserGame;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yxz
 * @since 2023-12-13
 */
public interface UserGameMapper extends BaseMapper<UserGame> {

    List<UserGame> mylist(Search search);

    void deleteByMyUg(String username, String gamename);

    void updateUserGame(String username, String gamename, Integer gameHour, String b_username, String b_gamename);

    void saveUserGame(String username, String gamename, Integer gameHour, String b_username, String b_gamename);
}
