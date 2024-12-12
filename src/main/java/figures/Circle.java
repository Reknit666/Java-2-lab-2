package figures;

import com.example.java_2_lab_2.Drawable;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

public class Circle implements Drawable, Serializable {
    private final double x;
    private final double y;
    private final double r;
    public Circle(Double x, Double y,Double r){
        this.x = x;
        this.y=y;
        this.r = r;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.beginPath();
        gc.strokeOval(x,y, r, r);
        gc.stroke();
    }
}
