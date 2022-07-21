package ru.job4j.concurrent;

import java.io.*;

public final class SaveContent {
    private final File file;

    public SaveContent(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) {
       try (BufferedOutputStream o = new BufferedOutputStream(new FileOutputStream(file))) {
           for (int i = 0; i < content.length(); i += 1) {
               o.write(content.charAt(i));
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
