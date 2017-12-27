package labs.lab_1_1;

import labs.lab_1_1.Thread.Parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    private static final String PATH = "src/main/java/labs/lab_1_1/files";

    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> resourseArrayList = new ConcurrentLinkedQueue<>();
        resourseArrayList.add("https://www.habrahabr.ru/post/260773/");
        resourseArrayList.add("https://ria.ru/");
        resourseArrayList.add("http://www.j4web.ru/java-regex/poluchenie-ssylok-iz-html-dokumenta-v-java.html");
        resourseArrayList.add("https://www.google.ru");
        resourseArrayList.add("http://www.proglang.su/java/strings-matches");
        resourseArrayList.add("тест");
        resourseArrayList.add("http://www.test2");
        resourseArrayList.addAll(getFiles());
        Parser parser = new Parser(resourseArrayList);
        parser.start();
    }

    public static Collection<? extends String> getFiles() {
        ArrayList<String> list = new ArrayList<>();
        getFileList().stream().forEach(f -> list.add(f.getPath()));
        return list;
    }
    public static List<File> getFileList() {
        ArrayList<File> list = new ArrayList<>();
        File file = new File(PATH);
        getFilesInDirectory(file, list);
        return list;
    }

    public static void getFilesInDirectory(File file, ArrayList<File> list) {
        for (File f : file.listFiles()) {
            if (f.isFile()) {
                list.add(f);
            } else if (f.isDirectory()) {
                getFilesInDirectory(f, list);
            }
        }
    }
}
