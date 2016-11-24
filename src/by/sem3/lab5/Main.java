package by.sem3.lab5;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            HtmlScanners.findTags();
            HtmlScanners.findWords();
            System.out.println("Job complete!");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}
