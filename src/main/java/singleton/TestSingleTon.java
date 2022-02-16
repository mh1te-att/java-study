package singleton;

import org.springframework.objenesis.instantiator.util.UnsafeUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 单例模式测试
 *
 * @author zhaoyc
 * @since 2/16/22
 */
public class TestSingleTon {

    public static void main(String[] args)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException,
                   IOException, ClassNotFoundException {
        Singleton2.otherMethod();
        System.out.println("------------------------------------");
        System.out.println(Singleton2.getInstance());
        System.out.println(Singleton2.getInstance());

        // 反射破坏单例
//        reflection(Singleton2.class);

        // 反序列化破坏单例
        serializable(Singleton2.getInstance());

        // unsafe破坏单例
//        unsafe(Singleton1.class);
    }

    private static void reflection(Class<?> clazz)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        System.out.println("反射创建实例：" + constructor.newInstance());
    }

    private static void serializable(Object instance) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(instance);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        System.out.println("反序列化创建实例：" + ois.readObject());
    }

    private static void unsafe(Class<?> clazz) throws InstantiationException {
        Object o = UnsafeUtils.getUnsafe().allocateInstance(clazz);
        System.out.println("Unsafe创建实例：" + o);
    }

}
