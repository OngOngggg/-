package com.yxz.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxz.common.vo.Result;
import com.yxz.sys.entity.User;
import com.yxz.sys.entity.UserGame;
import com.yxz.sys.service.IUserGameService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2023-12-13
 */
@RestController
@RequestMapping("/userGame")
@CrossOrigin
public class UserGameController {
    @Autowired
    private IUserGameService userGameService;

    @GetMapping("/list")
    public  Result<Map<String,Object>> getUserGameList(@RequestParam(value = "username", required = false) String username,
                                                   @RequestParam(value = "gamename", required = false) String gamename,
                                                   @RequestParam(value = "pageNo", required = false) int pageNo,
                                                   @RequestParam(value = "pageSize", required = false) int pageSize){
//        LambdaQueryWrapper<UserGame> wrapper =  new LambdaQueryWrapper<> ();
//        wrapper.eq(StringUtils.hasLength(username),UserGame::getUsername,username);
//        wrapper.eq(StringUtils.hasLength(gamename),UserGame::getGamename,gamename);
//        wrapper.orderByAsc(UserGame::getId);
//
//        Page<UserGame> page = new Page<>(pageNo,pageSize);
//        userGameService.page(page,wrapper);
        if(username==null) username = "";
        if(gamename==null) gamename = "";

        System.out.println(username);
        System.out.println(gamename);

        List<UserGame> userGames = userGameService.mysearchlist(username,gamename);
        int total = userGames.size();
        int from = (pageNo-1)*pageSize;
        userGames = userGames.subList(from,Math.min(from+pageSize,total));

        Map<String,Object> data = new HashMap<>();
        data.put("total",total);
        data.put("rows",userGames);

        return Result.success(data);
    }

    @PostMapping("/delete")
    public Result<?> deleteUserGameById(@RequestParam("username") String username,
                                           @RequestParam("gamename") String gamename){
        userGameService.removeByUg(username,gamename);
        //iUserService.removeById(id);
        return Result.success("删除用户成功");
    }

    @PostMapping("/save")
    public  Result<?> saveUserGame(@RequestParam(value = "b_username", required = false) String b_username,
                                                       @RequestParam(value = "username", required = false) String username,
                                                       @RequestParam(value = "b_gamename", required = false) String b_gamename,
                                                       @RequestParam(value = "gamename", required = false) String gamename,
                                                       @RequestParam(value = "gameHour", required = false) Integer gameHour,
                                                       @RequestParam(value = "judge", required = false) boolean judge){
        if(judge==false){
            userGameService.updateUserGame(username,gamename,gameHour,b_username,b_gamename);
            return Result.success("修改用户成功");
        }
        else{
            userGameService.saveUserGame(username,gamename,gameHour,b_username,b_gamename);
            return Result.success("保存用户成功");
        }
    }
}
