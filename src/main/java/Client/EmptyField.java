package Client;


import java.awt.geom.Ellipse2D;

/**
 * empty field class
 */
public class EmptyField implements Field{
    /**
     * owner getter
     * @return constant -1
     */
    @Override
    public int getOwnerId() {
        return -1;
    }

    /**
     * return null as empty field has no field
     * @return null
     */
    @Override
    public Ellipse2D getField() {
        return null;
    }

    /**
     * return false as empty field cannot be activated
     * @return false
     */
    @Override
    public boolean isActive() { return false; }

    /**
     * empty function
     * @param state not used
     */
    @Override
    public void setActive(boolean state) {}

}
