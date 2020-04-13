package com.weichuang.vo;

import com.weichuang.pojo.User;

public class QueryVo {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "QueryVo{" +
                "user=" + user +
                '}';
    }
}
