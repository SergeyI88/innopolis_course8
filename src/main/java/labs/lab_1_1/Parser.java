package labs.lab_1_1;

import labs.lab_1_1.Thread.ThreadOnResourse;
import labs.lab_1_1.Thread.ThreadValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Parser {
    private ConcurrentLinkedQueue<String> resourseArrayList;
    private ConcurrentLinkedQueue<Resourse> queueWasCheckingresourse;
    public static volatile boolean isStop = true;
    public static HashMap<String, Integer> map = new HashMap<>();


    public Parser(ConcurrentLinkedQueue<String> resourseArrayList) {
        this.resourseArrayList = resourseArrayList;
        queueWasCheckingresourse = new ConcurrentLinkedQueue<>();
    }

    public void start() {
        ExecutorService serviceValid = Executors.newFixedThreadPool(3);
        while (!resourseArrayList.isEmpty()) {
            serviceValid.submit(new ThreadValidator(resourseArrayList, queueWasCheckingresourse));
        }
        serviceValid.shutdown();
        ExecutorService service = Executors.newFixedThreadPool(3);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!queueWasCheckingresourse.isEmpty() && isStop) {
            service.submit(new ThreadOnResourse(this, queueWasCheckingresourse.poll(), service));
        }
    }
}
