package com.example.java_2_lab_2.Deserializer;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class BinaryToTxtDeserializer {
    public List<String> deserialize(String inputFilePath) throws IOException, ClassNotFoundException {
        List<String> lines = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(inputFilePath))) {
            int numberOfLines = inputStream.readInt(); // Чтение количества строк

            for (int i = 0; i < numberOfLines; i++) {
                String line = (String) inputStream.readObject(); // Чтение каждой строки
                lines.add(line);
            }
        }

        return lines;
    }
}