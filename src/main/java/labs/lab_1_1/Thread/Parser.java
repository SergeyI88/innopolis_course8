package labs.lab_1_1.Thread;


import labs.lab_1_1.Resourse;

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
        System.out.println(queueWasCheckingresourse);
        while (!queueWasCheckingresourse.isEmpty() && isStop) {
            service.execute(new ThreadOnResourse(queueWasCheckingresourse.poll(), service));

        }
      //  new ThreadOnResourse(queueWasCheckingresourse.poll(), service).run();
    }
}
