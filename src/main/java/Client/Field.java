package Client;

import java.awt.geom.Ellipse2D;

public interface Field {
    int getOwnerId();
    Ellipse2D getField();
    boolean isActive();
    void setActive(boolean state);
}
