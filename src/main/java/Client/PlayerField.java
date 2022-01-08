package Client;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class PlayerField implements Field {

    Ellipse2D.Float field;
    int ownerId;
    boolean isActive;

    public PlayerField(int ownerId, Point2D location, Dimension2D size) {
        field = new Ellipse2D.Float();
        this.ownerId = ownerId;
        field.setFrame(location, size);
        isActive = false;
    }

    @Override
    public int getOwnerId() {
        return ownerId;
    }

    @Override
    public Ellipse2D getField() {
        return field;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean state) {
        isActive = state;
    }
}
