package singleton;

import java.io.Serializable;

/**
 * 单例模式--饿汉式
 * 手动添加代码防止反射和反序列化破坏单例
 *
 * @author zhaoyc
 * @since 2/16/22
 */
public class Singleton1 implements Serializable {

    private Singleton1() {
        // 构造方法抛出异常是防止反射破坏单例
        if (INSTANCE != null) {
            throw new RuntimeException("单例对象不能重复创建");
        }
        System.out.println("private Singleton()");
    }

    private static final Singleton1 INSTANCE = new Singleton1();

    public static Singleton1 getInstance() {
        return INSTANCE;
    }

    public static void otherMethod() {
        System.out.println("otherMethod()");
    }

    // readResolve是防止反序列化破坏单例
    public Object readResolve() {
        return INSTANCE;
    }
}
