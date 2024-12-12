package figures;

import com.example.java_2_lab_2.Drawable;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

public class Point implements Drawable, Serializable {
    final int R_Point = 2;
    private final double x;
    private final double y;
    public Point(Double x, Double y){
        this.x = x;
        this.y=y;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.fillOval(x,y, R_Point, R_Point);
        gc.stroke();
    }
}
