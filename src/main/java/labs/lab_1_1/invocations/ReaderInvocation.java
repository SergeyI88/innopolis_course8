package labs.lab_1_1.invocations;

import labs.lab_1_1.ReadableResourse;
import labs.lab_1_1.Reader;
import labs.lab_1_1.class_loaders.MyClassLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ReaderInvocation implements InvocationHandler {

    private String name;
    ReadableResourse readableResourse;

    public ReaderInvocation(String name) {
        this.name = name;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("readUrl")) {
            MyClassLoader loader = new MyClassLoader(proxy.getClass().getClassLoader());
            Class readerClass = loader.loadClass("labs.lab_1_1.Reader");
            Method newMethod = readerClass.getMethod(method.getName());

            Constructor[] constructors = readerClass.getConstructors();

            return newMethod.invoke(constructors[0].newInstance(name), args);
        } else if (method.getName().equals("readFile")) {
            MyClassLoader loader = new MyClassLoader(proxy.getClass().getClassLoader());
            Class readerClass = loader.loadClass("labs.lab_1_1.Reader");

            Method newMethod = readerClass.getMethod(method.getName());

            Constructor[] constructors = readerClass.getConstructors();

            return newMethod.invoke(constructors[0].newInstance(name), args);
        }
        return method.invoke(readableResourse, args);
    }
}
