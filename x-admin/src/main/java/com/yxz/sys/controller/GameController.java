package com.yxz.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxz.common.vo.Result;
import com.yxz.sys.entity.Game;
import com.yxz.sys.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yxz
 * @since 2023-12-13
 */
@RestController
@RequestMapping("/game")
@CrossOrigin
public class GameController {
    @Autowired
    private IGameService gameService;

    @GetMapping("/list")
    public Result<Map<String,Object>> getGameList(@RequestParam(value = "gameName",required = false) String gameName,
                                                  @RequestParam(value = "pageNo",required = false) Long pageNo,
                                                  @RequestParam(value = "pageSize",required = false) Long pageSize){
        LambdaQueryWrapper<Game> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(gameName), Game::getGameName,gameName);
        wrapper.orderByAsc(Game::getGameId);

        Page<Game> page = new Page<>(pageNo,pageSize);
        gameService.page(page, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);

    }

    @PostMapping
    public Result<?> addGame(@RequestBody Game game){
        gameService.mySave(game);
        //gameService.save(game);
        return Result.success("新增游戏成功");
    }

    @PutMapping
    public Result<?> updateGame(@RequestBody Game game){
        //gameService.updateByMyId(game);
        gameService.updateById(game);
        return Result.success("修改游戏成功");
    }

    @GetMapping("/{gameId}")
    public Result<Game> getGameById(@PathVariable("gameId") Integer id){
        //Game game = gameService.getByMyId(id);
        Game game = gameService.getById(id);
        return Result.success(game);
    }

    @DeleteMapping("/{gameId}")
    public Result<Game> deleteGameById(@PathVariable("gameId") Integer id){
        gameService.removeByMyId(id);
        //gameService.removeById(id);
        return Result.success("删除游戏成功");
    }

}
