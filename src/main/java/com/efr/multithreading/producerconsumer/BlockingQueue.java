// Класс BlockingQueue представляет собой блокирующую очередь, которая используется для координации потоков производителей и потребителей.
// Очередь ограничена по размеру и использует механизмы синхронизации для управления доступом в многопоточной среде.
package com.efr.multithreading.producerconsumer;

import com.efr.multithreading.interfaces.MyQueue;
import com.efr.multithreading.model.Junior;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue implements MyQueue<Junior> {
    private int maxSize; // Максимальный размер очереди
    private Queue<Junior> juniors = new LinkedList<>(); // Используем LinkedList для очереди

    // Конструктор, принимающий максимальный размер очереди
    public BlockingQueue(int size) {
        this.maxSize = size;
    }

    // Метод добавления элемента в очередь. Если очередь заполнена, поток блокируется до освобождения места.
    @Override
    public synchronized void enqueue(Junior junior) {
        // Пока очередь заполнена, ждем
        while (juniors.size() == maxSize) {
            try {
                wait(); // Ожидание, пока не появится место в очереди
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Восстанавливаем флаг прерывания
            }
        }
        juniors.add(junior); // Добавляем Junior в очередь
        notifyAll(); // Оповещаем другие потоки, что элемент добавлен
    }

    // Метод извлечения элемента из очереди. Если очередь пуста, поток блокируется до появления элементов.
    @Override
    public synchronized Junior dequeue() {
        // Пока очередь пуста, ждем
        while (juniors.isEmpty()) {
            try {
                wait(); // Ожидание, пока не появится элемент в очереди
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Восстанавливаем флаг прерывания
            }
        }
        Junior junior = juniors.remove(); // Извлекаем элемент из очереди
        notifyAll(); // Оповещаем другие потоки, что элемент извлечен
        return junior;
    }

    // Возвращает текущий размер очереди
    @Override
    public synchronized int size() {
        return juniors.size();
    }
}