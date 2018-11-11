package github.banana.demo;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

class Ball {
    private static final int X = 15;
    private static final int Y = 15;
    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;

    public void move(Rectangle2D bounds) {
        x += dx;
        y += dy;

        if (x < bounds.getMinX()) {
            x = bounds.getMinX();
            dx = -dx;
        }

        if ( x + X >= bounds.getMaxX()) {
            x = bounds.getMaxX() - X;
            dx = -dx;
        }

        if (y < bounds.getMinY()) {
            y = bounds.getMinY();
            dy = -dy;
        }

        if (y + Y >= bounds.getMaxY()) {
            y = bounds.getMinY() - Y;
            dy = -dy;
        }
    }

    public Ellipse2D getShape() {
        return  new Ellipse2D.Double(x , y , X, Y);
    }
}
