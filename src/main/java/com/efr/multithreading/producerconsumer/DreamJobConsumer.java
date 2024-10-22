// Класс DreamJobConsumer реализует Runnable и представляет собой потребителя, который извлекает Junior из очереди.
package com.efr.multithreading.producerconsumer;

import com.efr.multithreading.model.Junior;

public class DreamJobConsumer implements Runnable{
    private BlockingQueue blockingQueue;

    // Конструктор принимает блокирующую очередь, из которой будет извлекаться Junior.
    public DreamJobConsumer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            // Извлекаем Junior из очереди для устройства на работу
            Junior junior = blockingQueue.dequeue();
            junior.foundJob(); // Симуляция нахождения работы Junior
        } catch (Exception e) {
            // В случае прерывания устанавливаем флаг прерывания для потока
            Thread.currentThread().interrupt();
        }
    }
}
