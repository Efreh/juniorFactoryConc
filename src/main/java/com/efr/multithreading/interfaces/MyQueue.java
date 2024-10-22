// Интерфейс очереди, который определяет контракт для любых блокирующих очередей.
package com.efr.multithreading.interfaces;

public interface MyQueue<T> {
    // Метод для добавления элемента в очередь
    void enqueue(T item);

    // Метод для извлечения элемента из очереди
    T dequeue();

    // Метод для получения текущего размера очереди
    int size();
}
