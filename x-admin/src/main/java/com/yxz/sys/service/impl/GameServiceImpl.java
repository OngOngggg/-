package com.yxz.sys.service.impl;

import com.yxz.sys.entity.Game;
import com.yxz.sys.mapper.GameMapper;
import com.yxz.sys.service.IGameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yxz
 * @since 2023-12-13
 */
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements IGameService {

    @Override
    public void mySave(Game game) {
        this.baseMapper.saveGame(game);
    }

    @Override
    public void updateByMyId(Game game) {
        this.baseMapper.updateGame(game);
    }

    @Override
    public void removeByMyId(Integer id) {
        this.baseMapper.deleteGame(id);
    }
}
