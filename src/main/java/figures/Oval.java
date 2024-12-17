package figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;

import java.io.Serializable;

public class Oval implements Drawable, Serializable {
    private final double centerX;
    private final double centerY;
    private final double radiusX;
    private final double radiusY;
    private final double rotation;
    public Oval(Double centerX, Double centerY, Double radiusX, Double radiusY, Double rotation){
        this.centerX = centerX;
        this.centerY=centerY;
        this.radiusX = radiusX;
        this.radiusY=radiusY;
        this.rotation = rotation;
    }

    public void draw(GraphicsContext gc) {
        gc.save();
        Rotate r = new Rotate(rotation, centerX, centerY);
        gc.setTransform(r.getMxx(), r.getMyx(),
                r.getMxy(), r.getMyy(),
                r.getTx(), r.getTy());
        gc.strokeOval(centerX - radiusX, centerY - radiusY, radiusX * 2, radiusY * 2);
        gc.stroke();
        gc.restore();
    }


}
