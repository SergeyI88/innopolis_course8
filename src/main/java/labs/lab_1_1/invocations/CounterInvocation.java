package labs.lab_1_1.invocations;

import labs.lab_1_1.Counter;
import labs.lab_1_1.Counterable;
import labs.lab_1_1.Resourse;
import labs.lab_1_1.class_loaders.MyClassLoader;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.util.concurrent.ExecutorService;

public class CounterInvocation implements InvocationHandler {


    Counterable counterable;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MyClassLoader loader = new MyClassLoader(proxy.getClass().getClassLoader());
        Class counterClass = loader.loadClass("labs.lab_1_1.Counter");

        Method newMethod = counterClass.getMethod(method.getName(), String.class, Resourse.class, ExecutorService.class);

        return newMethod.invoke(counterClass.newInstance(), args);
    }
}
