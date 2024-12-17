package com.example.java_2_lab_2;

import java.io.Serializable;

public class TxtReader implements Serializable {
    private static final long serialVersionUID = 1L; // Для сериализации
    private int rows;
    private int columns;
    private String[][] data;

    public TxtReader(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new String[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(int rowIndex, int columnIndex, String value) {
        data[rowIndex][columnIndex] = value;
    }
}