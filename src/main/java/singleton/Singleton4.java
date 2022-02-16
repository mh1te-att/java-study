package singleton;

import java.io.Serializable;

/**
 * 单例模式--双检锁DSL
 * 优化懒汉式, 避免创建实例后每次都需要同步
 * INSTANCE = new Singleton4()不是原子的, 分为三步: 创建对象、调用构造和给静态变量赋值,
 * 其中后两步可能被指令重排优化, 变成先赋值在调用构造
 *
 * 如果线程1先执行了赋值, 线程2执行到第一个INSTANCE == null时发现INSTANCE已经不为null, 此时会返回一个未完全构造的对象
 *
 * @author zhaoyc
 * @since 2/16/22
 */
public class Singleton4 implements Serializable {

    private Singleton4() {
        System.out.println("private Singleton4()");
    }

    // 可见性，有序性，不加volatile可能会导致指令重排
    private static volatile Singleton4 INSTANCE = null;

    public static Singleton4 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton4.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton4();
                }
            }
        }
        return INSTANCE;
    }

    public static void otherMethod() {
        System.out.println("otherMethod()");
    }
}
