package figures;

import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;


public class Triangle implements Drawable, Serializable {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;
    public Triangle(Double x1, Double y1, Double x2, Double y2, Double x3, Double y3){
        this.x1 = x1;
        this.y1=y1;
        this.x2 = x2;
        this.y2=y2;
        this.x3 = x3;
        this.y3=y3;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.moveTo(x1,y1);
        gc.lineTo(x2,y2);
        gc.lineTo(x3,y3);
        gc.closePath();
        gc.stroke();
    }
}
