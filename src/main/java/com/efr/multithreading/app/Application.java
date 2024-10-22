// Основной класс приложения, который создает потоки производителей и потребителей и запускает их.
// Используется пул потоков для управления многопоточностью.
package com.efr.multithreading.app;

import com.efr.multithreading.model.Junior;
import com.efr.multithreading.producerconsumer.BlockingQueue;
import com.efr.multithreading.producerconsumer.DreamJobConsumer;
import com.efr.multithreading.producerconsumer.JavaCodeProducer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) {

        // Используем пул потоков для производителей и потребителей
        try (ExecutorService poolProducer = Executors.newFixedThreadPool(6);
             ExecutorService poolConsumer = Executors.newFixedThreadPool(3)) {

            // Создаем блокирующую очередь с максимальным размером 5
            BlockingQueue blockingQueue = new BlockingQueue(5);

            // Запускаем 10 потоков производителей, каждый из которых обучает Junior и помещает его в очередь
            for (int i = 0; i < 10; i++) {
                poolProducer.submit(new JavaCodeProducer(new Junior(), blockingQueue));
            }

            // Запускаем 10 потоков потребителей, каждый из которых извлекает Junior из очереди и устраивает его на работу
            for (int i = 0; i < 10; i++) {
                poolConsumer.submit(new DreamJobConsumer(blockingQueue));
            }
        }
        // Пулы потоков автоматически закрываются благодаря try-with-resources
    }
}
