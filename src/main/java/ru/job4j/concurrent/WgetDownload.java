package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class WgetDownload implements Runnable {
    private final String url;
    private final int speed;
    private final String targetFile;
    private final static int SECOND = 1000;

    public WgetDownload(String url, int speed, String targetFile) {
        this.url = url;
        this.speed = speed;
        this.targetFile = targetFile;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream out = new FileOutputStream(targetFile)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long start = System.currentTimeMillis();
            long timePassed;
            int downloadData = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                out.write(dataBuffer, 0, bytesRead);
                downloadData += bytesRead;
                if (downloadData > speed) {
                    timePassed = System.currentTimeMillis() - start;
                    if (timePassed < SECOND) {
                        try {
                            Thread.sleep(SECOND - timePassed);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    downloadData = 0;
                    start = System.currentTimeMillis();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*
        url = http://speedtest.ftp.otenet.gr/files/test10Mb.db
        speed = 1048576
        targetFile = D:\testTMP.db
         */
        String url = args[0];
        String targetFile = args[2];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new WgetDownload(url, speed, targetFile));
        long start = System.nanoTime();
        wget.start();
        System.out.println("wget START");
        wget.join();
        long end = System.nanoTime();
        System.out.println("Time PASSED is - " + (end - start) / 1000_000_000);
    }
}
