package com.sample.api.classloaderandforname;

/**
 * 测试ClassLoader和Class.forName
 */
public class Line {
    static{
        System.out.println("执行静态块");
    }

    public static String aa = getString();

    private static String getString() {
        System.out.println("执行静态方法");
        return "静态方法";
    }

    public Line() {
        System.out.println("执行构造函数");
    }
}
