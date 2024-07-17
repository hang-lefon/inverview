/*
 * Copyright (c) 2021, 2024, lanfeng.cn All rights reserved.
 *
 */
package com.example.interview.threadlocal;


import com.example.interview.model.LocalUser;

public class LocalUserUtil {
    private static ThreadLocal<LocalUser> localUser = new ThreadLocal<>();
    //在当前线程的脑门上贴一个标签，就是当前用户
    public static void setLocalUser(LocalUser localUser) {
        LocalUserUtil.localUser.set(localUser);
    }
    //从当前线程的脑门上摘下标签，就是当前用户
    public static LocalUser getLocalUser() {
        return localUser.get();
    }
    public static void remove() {
        localUser.remove();
    }

}
