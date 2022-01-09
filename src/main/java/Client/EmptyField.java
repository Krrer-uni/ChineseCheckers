package Client;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class EmptyField implements Field{

    Ellipse2D.Float field;
    boolean isActive;

    public EmptyField( Point2D location, Dimension2D size) {
        field = new Ellipse2D.Float();
        field.setFrame(location, size);
        isActive = false;
    }

    @Override
    public int getOwnerId() {
        return -1;
    }

    @Override
    public Ellipse2D getField() {
        return field;
    }

    @Override
    public boolean isActive() { return false; }

    @Override
    public void setActive(boolean state) {
        isActive = state;
    }

}
