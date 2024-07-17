package com.example.interview.dao;

import com.example.interview.annotations.PageX;
import com.example.interview.model.BlogPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>Project: interview - BlogPostDao</p>
 * <p>Powered by lanfeng On 2024-07-17 </p>
 * <p>描述：<p>
 *
 * @author lanfeng [hlefon@qq.com]
 * @version 1.0
 * @since 17
 */
@Mapper
public interface BlogPostDao {
    void add(BlogPost blogPost);

    void delete(Integer id);

    void update(Integer id);

    BlogPost select(Integer id);

    @PageX
    //PageX自定义注解，自动分页需要前端传递page和size
    List<BlogPost> list(String uid);
}
