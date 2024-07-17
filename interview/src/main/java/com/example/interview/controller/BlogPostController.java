/*
 * Copyright (c) 2021, 2024, lanfeng.cn All rights reserved.
 *
 */
package com.example.interview.controller;

import com.example.interview.model.BlogPost;
import com.example.interview.model.LocalUser;
import com.example.interview.service.BlogPostService;
import com.example.interview.threadlocal.LocalUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Project: interview - BlogPostController</p>
 * <p>Powered by lanfeng On 2024-07-17 </p>
 * <p>描述：<p>
 *
 * @author lanfeng [hlefon@qq.com]
 * @version 1.0
 * @since 17
 */
@RestController
@RequestMapping("/api/posts")
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;
    @PostMapping
    public void add(@RequestBody BlogPost blogPost) {
        blogPostService.add(blogPost);
    }
    @GetMapping("/uid")
    public List<BlogPost> list() {
        LocalUser user = LocalUserUtil.getLocalUser();
        String uid = user.getId();
        return blogPostService.list(uid);
    }
    @GetMapping("/id")
    public BlogPost select(Integer id) {
        return blogPostService.select(id);
    }
    @PutMapping
    public void update(Integer id) {
        blogPostService.update(id);
    }


    //一般是软删除，我这里直接删除了。软删除就是加一个状态，1是删除，0是显示
    @DeleteMapping
    public void delete(Integer id) {
        blogPostService.delete(id);
    }
}
