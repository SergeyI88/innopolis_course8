package labs.lab_1_1;

import labs.lab_1_1.Thread.Parser;

import java.util.concurrent.ExecutorService;

public class Counter implements Counterable {


    public void count(String string, Resourse resourse, ExecutorService service) {

        String[] strings = string.split("[0-9]");
        for (String s : strings){
            if (!s.equals("")) {
                synchronized (Parser.map) {
                    if (Parser.map.containsKey(s)) {
                        int count = Parser.map.get(s);
                        try {
                            Thread.sleep(350);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("В потоке с именем " + resourse.getName() + " был обнаружен дубликат слова " + "\"" + s + "\"" + " всего количетсво " + count);
                        if (count == 30) {
                            System.out.println("В потоке с именем " + resourse.getName() + " был обнаружен дубликат слова " + "\"" + s + "\"" + " которое ПРЕВЫШАЕТ ДОПУСТИМЫЙ ЛИМИТ");
                            Parser.isStop = false;
                            service.shutdown();
                        } else {
                            Parser.map.put(s, ++count);
                        }
                    } else {
                        Parser.map.put(s, 1);
                    }
                }
            }
        }
    }
}


