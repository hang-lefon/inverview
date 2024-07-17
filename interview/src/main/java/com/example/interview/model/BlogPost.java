/*
 * Copyright (c) 2021, 2024, lanfeng.cn All rights reserved.
 *
 */
package com.example.interview.model;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Project: interview - BlogPost</p>
 * <p>Powered by lanfeng On 2024-07-17 </p>
 * <p>描述：<p>
 *
 * @author lanfeng [hlefon@qq.com]
 * @version 1.0
 * @since 17
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BlogPost {
    private Integer id;
    private String title;
    private String content;
    private Integer userid;
    private DateTime createTime;
    private DateTime lastmodified;
}
