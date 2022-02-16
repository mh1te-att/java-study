package singleton;

import java.io.Serializable;

/**
 * 单例模式--懒汉式
 * 线程不安全, 需要加锁, 但是会降低性能
 *
 * @author zhaoyc
 * @since 2/16/22
 */
public class Singleton3 implements Serializable {

    private Singleton3() {
        System.out.println("private Singleton3()");
    }

    private static Singleton3 INSTANCE = null;

    // Singleton3.class加锁
    public static synchronized Singleton3 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton3();
        }
        return INSTANCE;
    }

    public static void otherMethod() {
        System.out.println("otherMethod()");
    }
}
