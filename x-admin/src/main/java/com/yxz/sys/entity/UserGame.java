package com.yxz.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yxz
 * @since 2023-12-13
 */
@TableName("x_user_game")
public class UserGame implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer Id;

    private Integer gameId;

    private Integer gameHour;

    private String username;

    private String gamename;

    private Integer userId;

    private String b_username;

    @Override
    public String toString() {
        return "UserGame{" +
                "Id=" + Id +
                ", gameId=" + gameId +
                ", gameHour=" + gameHour +
                ", username='" + username + '\'' +
                ", gamename='" + gamename + '\'' +
                ", userId=" + userId +
                ", b_username='" + b_username + '\'' +
                ", b_gamename='" + b_gamename + '\'' +
                '}';
    }

    public String getB_username() {
        return b_username;
    }

    public void setB_username(String b_username) {
        this.b_username = b_username;
    }

    public String getB_gamename() {
        return b_gamename;
    }

    public void setB_gamename(String b_gamename) {
        this.b_gamename = b_gamename;
    }

    private String b_gamename;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }


    public Integer getGameHour() {
        return gameHour;
    }

    public void setGameHour(Integer gameHour) {
        this.gameHour = gameHour;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

}
