package figures;

import com.example.java_2_lab_2.Drawable;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

public class Segment implements Drawable, Serializable {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    public Segment(Double x1, Double y1, Double x2, Double y2){
        this.x1 = x1;
        this.y1=y1;
        this.x2 = x2;
        this.y2=y2;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.beginPath(); // Начало пути
        gc.moveTo(x1, y1);
        gc.lineTo(x2, y2);
        gc.stroke();
    }
}
