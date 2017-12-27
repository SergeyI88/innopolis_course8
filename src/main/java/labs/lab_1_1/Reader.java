package labs.lab_1_1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.Arrays;

public class Reader implements ReadableResourse  {

    public String string;

    public Reader(String nameResourse) {
        this.string = nameResourse;

    }

    public String readFile() {
        BufferedReader reader = null;
        StringBuilder sB = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(string));
            String line = reader.readLine();
            while (line != null) {
                sB.append(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sB.toString();
    }

    public String readUrl() {
        Document doc = null;
        try {
            doc = Jsoup.connect(string).get();
        } catch (IOException e) {
           return "";
        }
        Element body = doc.body();
        String bodyString = body.toString();
        String[] strings = bodyString.split("[^А-Яа-я0-9]");
        StringBuilder result = new StringBuilder();
        for (String s: strings) {
            if (!s.equals("")) {
                result.append(s + " ");
            }
        }
        return result.toString();
    }
}
