package Client;

import java.awt.geom.Ellipse2D;

/**
 * Field interface
 */
public interface Field {
    /**
     * owner id getter
     * @return owner id
     */
    int getOwnerId();

    /**
     *  field getter
     * @return field
     */
    Ellipse2D getField();

    /**
     *  getter for active state
     * @return state of field
     */
    boolean isActive();

    /**
     * setter for active state
     * @param state state of field
     */
    void setActive(boolean state);
}
