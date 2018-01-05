package com.sample.concurrent;

import com.sample.model.User;
import sun.misc.Unsafe;

public class UnsafeTest2 {
    private static long userNameOffset;
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    static  {
        try {
            userNameOffset = unsafe.objectFieldOffset(User.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        User user = new User();
        user.setUserName("aaa");

        boolean result = unsafe.compareAndSwapInt(user, userNameOffset, 0, 1);
        System.out.println(result);
    }
}
