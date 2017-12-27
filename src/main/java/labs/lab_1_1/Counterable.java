package labs.lab_1_1;

import java.util.concurrent.ExecutorService;

public interface Counterable {
    void count(String s, Resourse resourse, ExecutorService executorService);
}
