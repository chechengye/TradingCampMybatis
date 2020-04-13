package com.weichuang.mapper;

import com.weichuang.pojo.User;
import com.weichuang.vo.QueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> getAllUser();

    User getUserById(int id);

    int addUser(User user);

    List<User> getUsersByQueryVo(QueryVo vo);

    int getUserCount();

    List<User> getUsersByNameAndSex(@Param("name") String username, @Param("sex") String sex);

    List<User> getUsersByIds(List<Integer> ids);
}
