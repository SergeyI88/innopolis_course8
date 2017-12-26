package labs.lab_1_1.Thread;

import labs.lab_1_1.Parser;
import labs.lab_1_1.Resourse;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;

public class ThreadOnResourse extends Thread {
    Resourse resourse;
    Parser parser;
    ExecutorService service;
    String name;

    public ThreadOnResourse(Parser parser, Resourse poll, ExecutorService service) {
        resourse = poll;
        this.parser = parser;
        this.service = service;
        this.name = poll.getName();
    }

    @Override
    public void run() {
        search(resourse);
    }

    private void search(Resourse resourse) {
        while (Parser.isStop) {
            String[] words = resourse.getText().split("[^A-Яа-я]");
            Arrays.stream(words)
                    .filter(s -> !s.equals(""))
                    .forEach(s -> {
                        if (Parser.isStop) {
                            synchronized (parser.map) {
                                if (parser.map.containsKey(s)) {
                                    int count = parser.map.get(s);
                                    System.out.println("В потоке с именем " + name + " был обнаружен дубликат слова " + "\"" + s + "\"" + " всего количетсво " + count);
                                    if (count == 3) {
                                        System.out.println("В потоке с именем " + name + " был обнаружен дубликат слова " + "\"" + s + "\"" + " которое ПРЕВЫШАЕТ ДОПУСТИМЫЙ ЛИМИТ");
                                        parser.isStop = false;
                                        this.service.shutdown();
                                    } else {
                                        parser.map.put(s, ++count);
                                    }
                                } else {
                                    parser.map.put(s, 1);
                                }
                            }
                        }
                    });
        }
    }
}
