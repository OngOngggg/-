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
@TableName("x_game")
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "game_id", type = IdType.AUTO)
    private Integer gameId;

    private String gameName;

    public Integer getGameId() {
        return gameId;
    }

    public void setRoleId(Integer gameId) {
        this.gameId = gameId;
    }
    public String getGameName() {
        return gameName;
    }

    public void setRoleName(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public String toString() {
        return "Role{" +
            "gameId=" + gameId +
            ", gameName=" + gameName +
        "}";
    }
}
