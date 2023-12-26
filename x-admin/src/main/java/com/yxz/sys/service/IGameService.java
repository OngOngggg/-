package com.yxz.sys.service;

import com.yxz.sys.entity.Game;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxz
 * @since 2023-12-13
 */
public interface IGameService extends IService<Game> {

    void mySave(Game game);

    void updateByMyId(Game game);

    void removeByMyId(Integer id);
}
