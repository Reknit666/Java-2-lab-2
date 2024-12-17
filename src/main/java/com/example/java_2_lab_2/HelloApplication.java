package com.example.java_2_lab_2;

import com.example.java_2_lab_2.Deserializer.BinaryToTxtDeserializer;
import com.example.java_2_lab_2.Serializer.TxtToBinarySerializer;
import com.example.java_2_lab_2.ui.DrawCells;
import com.example.java_2_lab_2.ui.DrawNumbers;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelloApplication extends Application {
    private final int CANVAS_WIDTH = 600;
    private final int CANVAS_HEIGHT = 600;
    private GraphicsContext gc;
    private double ROW;
    private double COLUMN;
    private int[][] arrayOfValue;
    private boolean[][] arrayOfBlocked;
    private List<List<Integer>> listOfClueNumbersLeft;
    private List<List<Integer>> listOfClueNumbersUpper;
    private int[][] sampleForWinner;
    // Сколько ячеек для цифр
    private double CELL_FOR_CODE= 7.0;
    private Canvas canvas;
    private boolean gamewon = false;

    @Override
    public void start(Stage stage) throws IOException {
        chooseFile(stage);
    }
    private void isWinner(){
        if(Arrays.deepEquals(sampleForWinner, arrayOfValue)){
            System.out.println("U WON");
            gamewon = true;
        }
    }
    private void onClick(MouseEvent mouseEvent) {
        if(gamewon) return;
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        // Обработка нажатия только для Клеточек которые можем менять
        if (x>(CANVAS_WIDTH-ROW*Constants.cell_size)&& y>(CANVAS_HEIGHT-COLUMN*Constants.cell_size)){
            // Подсчет левого отступа
            double leftBorder = (CANVAS_WIDTH-COLUMN*Constants.cell_size)/Constants.cell_size;
            // Подсчет верхнего отступа
            double upperBorder = (CANVAS_HEIGHT-COLUMN*Constants.cell_size)/Constants.cell_size;
            int rowIndex = (int)((y / Constants.cell_size)-upperBorder);
            int columnIndex = (int)((x / Constants.cell_size)-leftBorder);
            // обработка нажатия на ЛКМ
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                // Переключаем значение: если 1 - на 0, если 0 - на 1
                if (arrayOfValue[rowIndex][columnIndex] == 1 && !arrayOfBlocked[rowIndex][columnIndex]) {
                    arrayOfValue[rowIndex][columnIndex] = 0;
                    draw(0, x, y);
                }
                else
                    if(arrayOfValue[rowIndex][columnIndex] == 0 && !arrayOfBlocked[rowIndex][columnIndex]) {
                    arrayOfValue[rowIndex][columnIndex] = 1;
                    draw(1, x, y);
                }

            } else
                if (mouseEvent.getButton() == MouseButton.SECONDARY && !arrayOfBlocked[rowIndex][columnIndex]) {
                arrayOfBlocked[rowIndex][columnIndex] = true;
                draw(-1, x, y);
            } else if (mouseEvent.getButton() == MouseButton.SECONDARY && arrayOfBlocked[rowIndex][columnIndex]) {
                    arrayOfBlocked[rowIndex][columnIndex] = false;
                    draw(0, x, y);
                }
        }
        isWinner();
    }
    private void drawCells() {
        new DrawCells(ROW, COLUMN, CANVAS_WIDTH, CANVAS_HEIGHT).draw(gc);
        new DrawCells(ROW, CELL_FOR_CODE, CANVAS_WIDTH - COLUMN * Constants.cell_size,
                CANVAS_HEIGHT).draw(gc);
        new DrawNumbers(ROW,COLUMN,CANVAS_WIDTH,CANVAS_HEIGHT).drawNumbersLeft(gc, listOfClueNumbersLeft);
        new DrawCells(CELL_FOR_CODE, COLUMN, CANVAS_WIDTH,
                CANVAS_HEIGHT - ROW * Constants.cell_size).draw(gc);
        new DrawNumbers(ROW,COLUMN,CANVAS_WIDTH,CANVAS_HEIGHT).drawNumbersUpper(gc, listOfClueNumbersUpper);

    }
    public void draw(int value, double x, double y) {
        double drawX = ((int)(x/Constants.cell_size))*Constants.cell_size ;
        double drawY = ((int)(y/Constants.cell_size))*Constants.cell_size ;  // Рисуем ячейку в зависимости от значения
        switch (value) {
            case 0:
                // Рисуем белую ячейку
                gc.beginPath();
                gc.setFill(Color.WHITE);
                gc.fillRect(drawX+0.5, drawY+0.5, Constants.cell_size-1, Constants.cell_size-1);
                gc.stroke();
                break;
                case 1:
                // Рисуем черную ячейку
                gc.beginPath();
                gc.setFill(Color.BLACK);
                gc.fillRect(drawX+0.5, drawY+0.5, Constants.cell_size-1, Constants.cell_size-1);
                gc.stroke();
                break;
                case -1:
                // Рисуем белую ячейку
                gc.beginPath();
                gc.setFill(Color.WHITE);
                gc.fillRect(drawX+0.5, drawY+0.5, Constants.cell_size-1, Constants.cell_size-1);
                // Рисуем черный круг внутри ячейки

                gc.setFill(Color.BLACK);
                gc.fillOval(
                        drawX + (Constants.cell_size / 4),
                        drawY + (Constants.cell_size / 4),
                        Constants.cell_size / 2,
                        Constants.cell_size / 2);
                gc.stroke();
                break;

        }
    }
    private void stageShow(Stage stage){
        VBox vBox = new VBox();
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        canvas.setOnMouseClicked(this::onClick);
        Button button = new Button("Выбрать файл");
        button.setOnAction(e -> chooseFile(stage));
        vBox.getChildren().addAll(canvas, button);
        Scene scene = new Scene(vBox);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    private void chooseFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            stageShow(stage);
            // Сериализация для каждого выбранного файла(выбираем только txt или ошибка)
            TxtToBinarySerializer serializer = new TxtToBinarySerializer();
            BinaryToTxtDeserializer deserializer = new BinaryToTxtDeserializer();
            String inputFilePath = file.getAbsolutePath(); // Путь к исходному файлу
            String outputFilePath = "house.dat"; // Путь к файлу для сериализации
            try {
                // Сериализация
                serializer.serialize(inputFilePath, outputFilePath);
                // Десериализация
                List<String> lines = deserializer.deserialize(outputFilePath);
                // Устанваливаем значения для row and column
                setRawAndColumn(lines);
                // Отрисовка клеточек
                // -1 - Белая ячейка с черным кругом
                // 0 - Белая ячейка
                // 1 - Черная ячейка
                arrayOfValue = new int[(int)ROW][(int)COLUMN];
                // false-не заблокирована
                //true - blocked
                arrayOfBlocked = new boolean[(int)ROW][(int)COLUMN];
                setClueNumbers(lines);
                setUpperClueNumbers(lines);
                gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
                gamewon = false;
                drawCells();
                setSampleForWinner(lines);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
     //Установка чисел посдказок
    private void setSampleForWinner(List<String> lines){
        sampleForWinner = new int[(int)ROW][(int)COLUMN];
        for (int i = 0; i <ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                sampleForWinner[i][j] = Character.getNumericValue(lines.get(i+1).charAt(j));
            }
        }
    }
    private void setClueNumbers(List<String> lines){
        listOfClueNumbersLeft = new ArrayList<>(); // Создаем основной список для хранения строк.
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            List<Integer> currentLineClue = new ArrayList<>(); // Создаем список для текущей строки.
            boolean flag = false;
            int counter = 0;
            for (int j = 0; j < line.length(); j++) {
                char character = line.charAt(j);
                if (character == '1') {
                    counter++;
                    flag = true;
                } else if (character == '0' && flag) {
                    currentLineClue.add(counter); // Добавляем счетчик в список текущей строки.
                    counter = 0; // Сбрасываем счетчик.
                    flag = false;
                }
            }
            // Проверяем, если в конце строки остались единицы.
            if (flag) {
                currentLineClue.add(counter);
            }
            listOfClueNumbersLeft.add(currentLineClue); // Добавляем текущую строку в основной список.
        }
    }
    private void setUpperClueNumbers(List<String> lines) {
        listOfClueNumbersUpper = new ArrayList<>();
        int lineLength = lines.get(1).length();

        for (int j = 0; j < lineLength; j++) {
            List<Integer> currentColumnClue = new ArrayList<>();
            boolean flag = false;
            int counter = 0;
            for (int i = 1; i < lines.size(); i++) {
                char character = lines.get(i).charAt(j);
                if (character == '1') {
                    counter++;
                    flag = true;
                } else if (character == '0' && flag) {
                    currentColumnClue.add(counter); // Добавляем счетчик в список текущего столбца.
                    counter = 0; // Сбрасываем счетчик.
                    flag = false;
                }
            }
            // Проверяем, если в конце столбца остались единицы.
            if (flag) {
                currentColumnClue.add(counter);
            }

            listOfClueNumbersUpper.add(currentColumnClue); // Добавляем текущий столбец в основной список.
        }
    }
    private void setRawAndColumn(List<String> lines){
        String firstLine = lines.get(0); // "15 15"
        // Разделение строки на части
        String[] parts = firstLine.split(" "); // ["15", "15"]
        // Преобразование строк в числа
        ROW = Integer.parseInt(parts[0]); // 15
        COLUMN = Integer.parseInt(parts[1]); // 15
    }
    public static void main(String[] args) {
        launch();
    }
}