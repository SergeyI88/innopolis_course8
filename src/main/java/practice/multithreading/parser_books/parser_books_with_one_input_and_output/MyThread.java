package practice.multithreading.parser_books.parser_books_with_one_input_and_output;

import java.io.*;

public class MyThread extends Thread {
    private final BufferedWriter writer;
    private final BufferedReader reader;
    private final ParseManager parseManager;
    private String name;
    private char[] word = {'с', 'т', 'р', 'а', 'д', 'а', 'н', 'и', 'е'};

    public MyThread(String name, ParseManager parseManager, BufferedWriter writer, BufferedReader reader) {
        super();
        this.name = name;
        this.parseManager = parseManager;
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public void run() {
        count();
    }

    private int count() {
        int resultAfter = 0;
        try {

            int i;
            while ((i = reader.read()) != -1) {
                char c = (char) i;
                if (searching(c, reader)) {
                    resultAfter++;
                    parseManager.quantity++;
                    if (resultAfter % 5 == 0) {
                        System.out.println("Найдено 5 слов поток " + name);
                        synchronized (parseManager) {
                            parseManager.addNewValueInFile(5 + parseManager.getLastResult());
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultAfter;
    }


    private boolean searching(char c, BufferedReader reader) {
        for (int i = 0; i < word.length; i++) {
            char c1 = word[i];
            if (c1 != c) {
                return false;
            }
            try {
                c = (char) reader.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}
