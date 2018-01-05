package com.sample.concurrent;

import com.sample.model.User;
import org.junit.Test;
import sun.misc.Unsafe;

public class UnsafeTest {
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    @Test
    public void test1() {
        try {
            final long userNameOffset = unsafe.objectFieldOffset(User.class.getDeclaredField("userName"));

            User user = new User();
            user.setUserName("aaa");

            boolean result = unsafe.compareAndSwapObject(user, userNameOffset, "aaa", "bbb");
            System.out.println(result);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
