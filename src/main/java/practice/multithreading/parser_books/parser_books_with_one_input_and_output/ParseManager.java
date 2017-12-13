package practice.multithreading.parser_books.parser_books_with_one_input_and_output;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseManager {
    private final static String total = "files/for_parser/total.txt";
    protected  int quantity = 0;
    private BufferedReader reader;
    private FileWriter fileWriter;

    public ParseManager(BufferedReader reader, FileWriter fileWriter) {
        this.reader = reader;
        this.fileWriter = fileWriter;
    }

    public ParseManager() {

    }
    public static void main(String[] args) {
       start(new File("files/for_parser/books"));
    }

    static public  void start(File folder) {
        ParseManager parseManager = null;
        try {
            parseManager = new ParseManager(new BufferedReader(new FileReader(total)), new FileWriter(total, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File[] folderEntries = folder.listFiles();
        String thread = "Поток №: ";
        int i = 1;
        for (File entry : folderEntries) {
            try {
                new MyThread(thread + i++, parseManager, new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(total, true)
                )), new BufferedReader(new InputStreamReader(new FileInputStream(entry.getPath())))).start();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(parseManager.quantity);
        try {
            parseManager.fileWriter.write(parseManager.quantity + " всего страданий");
            parseManager.fileWriter.flush();
            parseManager.fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected synchronized int getLastResult() {
        String string;
        int max = 0;
        try {
            while ((string = reader.readLine()) != null) { // читаем файл построчно
                Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
                Matcher matcher = pat.matcher(string);
                int start = 0;
                while (matcher.find(start)) {
                    String value = string.substring(matcher.start(), matcher.end());
                    int result = Integer.parseInt(value);
                    max  = max > result ? max : result;
                    start = matcher.end();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return max;
    }

    protected synchronized void addNewValueInFile(int resultAfter) {
        try {
            fileWriter.write(String.valueOf(resultAfter) + " - it added thread name " + Thread.currentThread().getName() + "\n");
            fileWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
