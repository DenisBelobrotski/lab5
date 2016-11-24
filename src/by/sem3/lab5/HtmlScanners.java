package by.sem3.lab5;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

class HtmlScanners {
    public static void findTags() throws IOException {
        Scanner htmlScanner = new Scanner(new File("resources/input1.html"));
        PrintStream tagPS = new PrintStream("resources/output1.out");
        String buffer;
        int index = 0;
        List<String> tags = new ArrayList<>();
        while (htmlScanner.hasNextLine()) {
            buffer = htmlScanner.nextLine();
            while (index >= 0 && index < buffer.length()) {
                index = buffer.indexOf('<', index);
                if (index >= 0) {
                    if (buffer.charAt(index + 1) != '/' &&
                            !tags.contains((buffer.substring(index + 1, buffer.indexOf('>', index))))) {
                        tags.add(buffer.substring(index + 1, buffer.indexOf('>', index)));
                    }
                    index = buffer.indexOf('>', index) + 1;
                }
            }
            index = 0;
        }
        htmlScanner.close();
        Collections.sort(tags, (str1, str2) -> Integer.compare(str1.length(), str2.length()));
        for (String item : tags) {
            tagPS.println(item);
        }
        tagPS.flush();
        tagPS.close();
    }

    public static void findWords() throws IOException {
        PrintStream numOfLinesPS = new PrintStream("resources/output2.out");
        PrintStream notFoundWordsPS = new PrintStream("resources/output3.out");
        List<String> wordsToFind = new ArrayList<>();
        int[] indexesOfLines;
        fillWordsToFind(wordsToFind);
        if (wordsToFind.isEmpty()) {
            System.out.println("input2.in is empty!");
            throw new IOException();
        }
        indexesOfLines = find(wordsToFind);
        for (int item : indexesOfLines) {
            numOfLinesPS.println(item);
        }
        Iterator<String> iterator = wordsToFind.iterator();
        int index = 0;
        while(iterator.hasNext()) {
            if(indexesOfLines[index] == -1) {
                notFoundWordsPS.println(iterator.next());
            } else {
                iterator.next();
            }
            index++;
        }
        numOfLinesPS.flush();
        notFoundWordsPS.flush();
        numOfLinesPS.close();
        notFoundWordsPS.close();
    }

    private static void fillWordsToFind(List<String> words) throws IOException {
        Scanner findWordsScanner = new Scanner(new File("resources/input2.in"));
        String temp;
        String[] wordsToFind;
        while (findWordsScanner.hasNextLine()) {
            temp = findWordsScanner.nextLine();
            temp = temp.toLowerCase();
            wordsToFind = temp.split("[;]+");
            for (int i = 0; i < wordsToFind.length; i++) {
                if (!words.contains(wordsToFind[i])) {
                    words.add(wordsToFind[i]);
                }
            }
        }
        findWordsScanner.close();
    }

    private static int[] find(List<String> wordsToFind) throws IOException {
        Scanner htmlScanner = new Scanner(new File("resources/input1.html"));
        String temp;
        int index = 0;
        int strIndex = 0;
        int[] indexesOfLines = new int[wordsToFind.size()];
        for (int i = 0; i < indexesOfLines.length; i++) {
            indexesOfLines[i] = -1;
        }
        while (htmlScanner.hasNextLine()) {
            temp = htmlScanner.nextLine();
            temp = temp.toLowerCase();
            index = 0;
            while (index >= 0 && index < temp.length()) {
                temp = temp.replace(temp.subSequence(temp.indexOf('<'), temp.indexOf('>') + 1), "");
                index = temp.indexOf('<', index);
            }
            index = 0;
            for (String item : wordsToFind) {
                if (temp.contains(item) && indexesOfLines[index] == -1) {
                    indexesOfLines[index] = strIndex;
                }
                index++;
            }
            strIndex++;
        }
        htmlScanner.close();
        return indexesOfLines;
    }
}
