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
public class Search implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;

    private String gamename;

    public Search(String username, String gamename) {
        this.username = username;
        this.gamename = gamename;
    }

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

    @Override
    public String toString() {
        return "Search{" +
                "username='" + username + '\'' +
                ", gamename='" + gamename + '\'' +
                '}';
    }
}
