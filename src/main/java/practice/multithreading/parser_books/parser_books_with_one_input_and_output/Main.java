package practice.multithreading.parser_books.parser_books_with_one_input_and_output;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        ParseManager.start(new File("files/for_parser/books"));;
    }
}
