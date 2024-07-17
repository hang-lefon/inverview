package com.example.interview.dao;

import com.example.interview.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>Project: interview - UserDao</p>
 * <p>Powered by lanfeng On 2024-07-17 </p>
 * <p>描述：<p>
 *
 * @author lanfeng [hlefon@qq.com]
 * @version 1.0
 * @since 17
 */
@Mapper
public interface UserDao {
    List<User> selectEmail(String email);

    User select(User user);

    Integer register(User user);

    User selectById(String id);
}
