package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Predicate;

public final class GetContent {

    private final File file;

    public GetContent(File file) {
        this.file = file;
    }


    public String predicateContent(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        synchronized (this) {
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
                int data;
                char charData;
                while ((data = bis.read()) != -1) {
                    charData = (char) data;
                    if (filter.test(charData)) {
                        output.append(charData);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return output.toString();
        }
    }

    public String getContent() {
        return predicateContent(data -> true);
    }

    public String getContentWithoutUnicode() {
        return predicateContent(data -> data < 0x80);
    }
}


