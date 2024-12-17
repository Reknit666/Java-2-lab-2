package com.example.java_2_lab_2.Serializer;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TxtToBinarySerializer {
    public void serialize(String inputFilePath, String outputFilePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputFilePath)); // Чтение строк из текстового файла

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(outputFilePath))) {
            outputStream.writeInt(lines.size()); // Запись количества строк
            for (String line : lines) {
                outputStream.writeObject(line); // Запись каждой строки
            }
        }
    }
}