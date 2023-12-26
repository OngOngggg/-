package com.yxz.sys.service;

import com.yxz.sys.entity.UserGame;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxz
 * @since 2023-12-13
 */
public interface IUserGameService extends IService<UserGame> {

    List<UserGame> mysearchlist(String username, String gamename);

    void removeByUg(String username, String gamename);

    void updateUserGame(String username, String gamename, Integer gameHour, String b_username, String b_gamename);

    void saveUserGame(String username, String gamename, Integer gameHour, String b_username, String b_gamename);
}
