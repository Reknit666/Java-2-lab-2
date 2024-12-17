package com.example.java_2_lab_2.ui;

import com.example.java_2_lab_2.Constants;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class DrawNumbers {
    private final double row;
    private final double column;
    private final double width;
    private final double height;
    double offsetY = 20; // Отступ между строками
    double offsetX = 20; // Отступ между цифрами в строке

    public DrawNumbers(double row, double column, double width, double height){
        this.row = row;
        this.column = column;
        this.width = width;
        this.height = height;
    }

    public void drawNumbersLeft(GraphicsContext gc, List<List<Integer>> numbersLeft) {
        for (int i = 0; i < (numbersLeft.size()); i++) {
            List<Integer> stroka = numbersLeft.get(i);
            // Начальная позиция по X
            double startX = width-column* Constants.cell_size-Constants.cell_size*stroka.size()+7;
            double startY = height-row * Constants.cell_size+15; // Начальная позиция по Y
            for (int j = 0; j < stroka.size(); j++) {
                // Рассчитываем позиции для каждой цифры
                double x = startX + j * offsetX;
                double y = startY + i * offsetY;

                // Рисуем цифру
                gc.beginPath();
                gc.fillText(String.valueOf(stroka.get(j)), x, y);
                gc.stroke();
            }
        }
    }
    public void drawNumbersUpper(GraphicsContext gc, List<List<Integer>> numbersRight) {
        for (int i = 0; i < (numbersRight.size()); i++) {
            List<Integer> stroka = numbersRight.get(i);
            // Начальная позиция по X
            double startX = width-column* Constants.cell_size+i*offsetX+7;
            double startY = height-row * Constants.cell_size-Constants.cell_size*stroka.size()+15; // Начальная позиция по Y
            for (int j = 0; j < stroka.size(); j++) {
                // Рассчитываем позиции для каждой цифры
                double x = startX;
                double y = startY + j * offsetY;

                // Рисуем цифру
                gc.beginPath();
                gc.fillText(String.valueOf(stroka.get(j)), x, y);
                gc.stroke();
            }
        }

    }
}
