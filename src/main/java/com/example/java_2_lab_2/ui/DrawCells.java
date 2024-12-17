package com.example.java_2_lab_2.ui;

import com.example.java_2_lab_2.Constants;
import com.example.java_2_lab_2.HelloApplication;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class DrawCells implements Drawable {
    private final double row;
    private final double column;
    private final double width;
    private final double height;


    public DrawCells(double row, double column, double width, double height){
        this.row = row;
        this.column = column;
        this.width = width;
        this.height = height;
;
    }
    @Override
    public void draw(GraphicsContext gc) {
        for (int i = 0; i <= column; i++) {
            gc.beginPath();
            gc.moveTo(width-i* Constants.cell_size,height);
            gc.lineTo(width-i*Constants.cell_size, height-row*Constants.cell_size);
            gc.stroke();

        }
        for (int i = 0; i <= row; i++) {
            gc.beginPath();
            gc.moveTo(width,height-i*Constants.cell_size);
            gc.lineTo(width-column*Constants.cell_size, height-i*Constants.cell_size);
            gc.stroke();
        }
    }

}
