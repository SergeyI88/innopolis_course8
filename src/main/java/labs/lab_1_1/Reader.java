package labs.lab_1_1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.Arrays;

public class Reader implements ReadableResourse {

    public String nameResourse;

    public Reader(String nameResourse) {
        this.nameResourse = nameResourse;
    }

    public  String readFile(String string) {
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

    public  String readUrl(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
           return "";
        }
        Element body = doc.body();
        String bodyString = body.toString();
        String result = returnOnlyKirilliza(bodyString);
        return result;
    }

    private static String returnOnlyKirilliza(String bodyString) {
        String[] strings = bodyString.split("[^А-Яа-я]");
        StringBuilder result = new StringBuilder();
        Arrays.stream(strings).forEach(s -> {
            if (!s.equals(""))
            result.append(s + " ");});
        return result.toString();
    }
}
