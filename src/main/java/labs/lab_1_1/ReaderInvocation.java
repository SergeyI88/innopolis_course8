package labs.lab_1_1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ReaderInvocation implements InvocationHandler {
    ReadableResourse readableResourse;

    public ReaderInvocation(ReadableResourse readableResourse) {
        this.readableResourse = readableResourse;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        long startTime = System.nanoTime();
        String name = ((Reader) readableResourse).nameResourse;
        System.out.println("начал выполняться метод " + method.getName() + " он переводит в строку ресурс:" + name);
        Object object = method.invoke(readableResourse, args);

        long finish = System.nanoTime();
        System.out.println("закончил работу " + method.getName() + " он переводил в строку ресурс:" + name + " и потратил на это:" +
                (finish - startTime) / 1000_000_000f + " сек.");
        System.out.println();
        return object;
    }
}
