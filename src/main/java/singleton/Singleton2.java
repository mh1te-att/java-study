package singleton;

/**
 * 单例模式--枚举饿汉式
 * 天然防止反射、反序列化破坏单例
 *
 * @author zhaoyc
 * @since 2/16/22
 */
public enum Singleton2 {

    INSTANCE;

    // 枚举默认构造为private
    Singleton2() {
        System.out.println("private Singleton2()");
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    public static Singleton2 getInstance() {
        return INSTANCE;
    }

    public static void otherMethod() {
        System.out.println("otherMethod()");
    }
}
