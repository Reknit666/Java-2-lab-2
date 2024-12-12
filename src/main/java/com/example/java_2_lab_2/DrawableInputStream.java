package com.example.java_2_lab_2;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;


// Класс для чтения Drawable
class DrawableInputStream implements AutoCloseable { // Реализуем AutoCloseable
    private ObjectInputStream inputStream;

    public DrawableInputStream(InputStream is) throws IOException {
        this.inputStream = new ObjectInputStream(is);
    }
//readDrawable() — считывает один объект Drawable из потока. Сначала считывается имя класса, затем считывается и возвращается сам объект.
    public Drawable readDrawable() throws IOException, ClassNotFoundException {
        // Чтение классов и создание объектов должно быть реализовано
        String className = (String) inputStream.readObject();
        Drawable drawable = (Drawable) inputStream.readObject();
        return drawable;
    }
//readDrawables() — считывает количество объектов, затем загружает и возвращает их в список.
    public List<Drawable> readDrawables() throws IOException, ClassNotFoundException {
        int size = inputStream.readInt();
        List<Drawable> drawables = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            drawables.add(readDrawable());
        }
        return drawables;
    }
    //Реализует AutoCloseable, что позволяет использовать его в try-with-resources, чтобы гарантировать закрытие потока.
    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}