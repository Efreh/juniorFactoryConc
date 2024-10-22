// Класс JavaCodeProducer реализует Runnable и представляет собой производителя, который добавляет Junior в очередь.
package com.efr.multithreading.producerconsumer;

import com.efr.multithreading.model.Junior;

public class JavaCodeProducer implements Runnable {
    private Junior junior;
    private BlockingQueue blockingQueue;

    // Конструктор принимает Junior и блокирующую очередь для взаимодействия.
    public JavaCodeProducer(Junior junior, BlockingQueue blockingQueue) {
        this.junior = junior;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            // Симулируем процесс обучения Junior (задержка на 500 мс)
            Thread.sleep(500);
            junior.education();

            // Добавляем обученного Junior в блокирующую очередь
            blockingQueue.enqueue(junior);
        } catch (InterruptedException e) {
            // В случае прерывания устанавливаем флаг прерывания для потока
            Thread.currentThread().interrupt();
        }
    }
}
