// Класс представляет начинающего разработчика (Junior) с уникальным именем.
// Этот объект будет использоваться в качестве элемента очереди в многопоточном приложении.
package com.efr.multithreading.model;

import java.util.Random;

public class Junior {
    private final String name;

    // Конструктор создает уникальное имя для каждого Junior, используя случайный ID.
    public Junior() {
        int id = new Random().nextInt(100);
        name = "Junior № " + id;
    }

    public String getName() {
        return name;
    }

    // Симулирует процесс обучения Junior.
    public void education(){
        System.out.println(name + " is learned");
    }

    // Симулирует процесс нахождения работы Junior.
    public void foundJob(){
        System.out.println(name + " found a job");
    }
}
