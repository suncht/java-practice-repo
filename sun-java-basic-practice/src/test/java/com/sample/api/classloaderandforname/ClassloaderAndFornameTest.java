package com.sample.api.classloaderandforname;

import org.junit.Test;


public class ClassloaderAndFornameTest {

    /**
     * Class.forName默认会执行static块和静态变量
     */
    @Test
    public void testClassForName1() {
        try {
            Class.forName(Line.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Class.forName(name, initialize, loader)带参函数也可控制是否加载static块和静态变量。
     */
    @Test
    public void testClassForName2() {
        try {
            Class.forName(Line.class.getName(), false, ClassLoader.getSystemClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * ClassLoader不会执行static块和和静态变量
     */
    @Test
    public void testClassLoader() {
        try {
            ClassloaderAndFornameTest.class.getClassLoader().loadClass(Line.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
