package Client;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class EmptyField implements Field{



    public EmptyField() {

    }

    @Override
    public int getOwnerId() {
        return -1;
    }

    @Override
    public Ellipse2D getField() {
        return null;
    }

    @Override
    public boolean isActive() { return false; }

    @Override
    public void setActive(boolean state) {}

}
