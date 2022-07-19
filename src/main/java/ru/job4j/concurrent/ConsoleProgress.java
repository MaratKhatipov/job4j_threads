package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        char[] process = {'|', '/', '―', '\\'};
        int index = 0;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\r LOAD: " + process[index++]);
                if (index == process.length) {
                    index = 0;
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        try {
            progress.start();
            Thread.sleep(3000);
            progress.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
