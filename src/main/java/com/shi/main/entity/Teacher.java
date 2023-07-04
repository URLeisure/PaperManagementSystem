package com.shi.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import javafx.scene.control.TextField;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author shianyi
 * @since 2023-06-07
 */
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String name;

    public Integer getTid() {
        return id;
    }

    public void setTid(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", name=" + name +
        "}";
    }
}
