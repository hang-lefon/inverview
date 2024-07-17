/*
 * Copyright (c) 2021, 2024, lanfeng.cn All rights reserved.
 *
 */
package com.example.interview.service;

import com.example.interview.dao.BlogPostDao;
import com.example.interview.model.BlogPost;
import com.example.interview.threadlocal.LocalUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Project: interview - BlogPostService</p>
 * <p>Powered by lanfeng On 2024-07-17 </p>
 * <p>描述：<p>
 *
 * @author lanfeng [hlefon@qq.com]
 * @version 1.0
 * @since 17
 */
@Service
public class BlogPostService {
    @Autowired
    private BlogPostDao blogPostDao;

    public void add(BlogPost blogPost) {
        blogPost.setUserid(Integer.parseInt(LocalUserUtil.getLocalUser().getId()));
        blogPostDao.add(blogPost);
    }

    public List<BlogPost> list(String uid) {
        return blogPostDao.list(uid);
    }

    public BlogPost select(Integer id) {
        return blogPostDao.select(id);
    }

    public void update(Integer id) {
        blogPostDao.update(id);
    }

    public void delete(Integer id) {
        blogPostDao.delete(id);
    }
}
