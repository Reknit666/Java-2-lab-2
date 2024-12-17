package figures;

import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;
import java.util.List;

public class ConvexPolygon implements Drawable, Serializable {
    private final double[] xPoints;
    private final double[] yPoints;
    private final int nPoints;
    public ConvexPolygon(List<Double> xPoints, List<Double> yPoints){
        this.nPoints = xPoints.size();
        this.xPoints = new double[nPoints];
        this.yPoints = new double[nPoints];
        for (int i = 0; i < nPoints; i++) {
            this.xPoints[i] = xPoints.get(i);
            this.yPoints[i] = yPoints.get(i);
        }
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.strokePolygon(xPoints, yPoints, nPoints);
        gc.stroke();
    }
}
