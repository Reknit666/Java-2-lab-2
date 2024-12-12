package com.example.java_2_lab_2;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

// Класс для записи Drawable
class DrawableOutputStream implements AutoCloseable { // Реализуем AutoCloseable
    private final ObjectOutputStream outputStream;

    public DrawableOutputStream(OutputStream os) throws IOException {
        this.outputStream = new ObjectOutputStream(os);
    }
//writeDrawable(Drawable drawable) — записывает один объект Drawable.
// Сначала записывается имя класса фигуры (что позволяет явно указать тип для десериализации), затем сам объект.
    public void writeDrawable(Drawable drawable) throws IOException {
        outputStream.writeObject(drawable.getClass().getName());
        outputStream.writeObject(drawable);
    }
//writeDrawables(List drawables) — записывает общее количество фигур и затем циклом записывает каждую фигуру по отдельности.
    public void writeDrawables(List<Drawable> drawables) throws IOException {
        outputStream.writeInt(drawables.size());
        for (Drawable drawable : drawables) {
            writeDrawable(drawable);
        }
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
    }
}
