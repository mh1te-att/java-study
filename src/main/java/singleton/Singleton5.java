package singleton;

import java.io.Serializable;

/**
 * 单例模式--懒汉式内部类
 *
 * @author zhaoyc
 * @since 2/16/22
 */
public class Singleton5 implements Serializable {

    private Singleton5() {
        System.out.println("private Singleton5()");
    }

    private static class Holder {
        static Singleton5 INSTANCE = new Singleton5();
    }

    public static Singleton5 getInstance() {
        return Holder.INSTANCE;
    }

    public static void otherMethod() {
        System.out.println("otherMethod()");
    }
}
