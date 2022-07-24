package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    final int maxSize;

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (queue.size() >= maxSize) {
                wait();
            }
            queue.offer(value);
            notifyAll();
        }
    }


    /**
     * @return объект из внутренней коллекции
     * Если в коллекции объектов нет,
     * то нужно перевести текущую нить в состояние ожидания
     * @throws InterruptedException
     */
    public synchronized T poll() throws InterruptedException {
        while (queue.size() == 0) {
            this.wait();
        }
        T result = queue.poll();
        notifyAll();
        return result;
    }


    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
