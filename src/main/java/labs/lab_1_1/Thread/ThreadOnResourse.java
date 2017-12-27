package labs.lab_1_1.Thread;



import labs.lab_1_1.Counterable;
import labs.lab_1_1.Resourse;
import labs.lab_1_1.invocations.CounterInvocation;

import java.lang.reflect.Proxy;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class ThreadOnResourse extends Thread {
    Resourse resourse;
    ExecutorService service;
    String name;

    public ThreadOnResourse(Resourse poll, ExecutorService service) {
        resourse = poll;
        this.service = service;
        this.name = poll.getName();
    }

    @Override
    public void run() {

        search(resourse);

    }

    private void search(Resourse resourse) {
        Counterable counterProxy = (Counterable) Proxy.newProxyInstance(CounterInvocation.class.getClassLoader()
                , new Class[]{Counterable.class}
                , new CounterInvocation());
        Scanner scanner = new Scanner(System.in);
        String[] words = resourse.getText().split("[^А-Яа-я0-9]");
        int count = 0;

        while (Parser.isStop && words.length > ++count) {
            counterProxy.count(words[count], resourse, service);
        }
    }
}
