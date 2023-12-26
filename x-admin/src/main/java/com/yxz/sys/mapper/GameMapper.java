package com.yxz.sys.mapper;

import com.yxz.sys.entity.Game;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yxz
 * @since 2023-12-13
 */
public interface GameMapper extends BaseMapper<Game> {

    void getByMyId(Integer id);

    void saveGame(Game game);

    void updateGame(Game game);

    void deleteGame(Integer id);
}
