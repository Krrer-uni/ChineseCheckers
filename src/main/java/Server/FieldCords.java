package Server;

/**
 * Simple class that is representing a point on a 2D plot.
 * @author Wojciech Rymer & Marek Świergoń
 *
 */
public class FieldCords {
	
	/**
	 *  x coordinate of a point
	 */
    private transient int x;
    /**
     *  y coordinate of a point 
     */
    private transient int y;

    /**
     * A standard constructor to create a representation of a specific point.
     * @param x coordinate x of a point
     * @param y coordinate y of a point
     */
    public FieldCords(final int x, final int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * @return coordinate x of a point
     */
    public int getX() {
        return x;
    }

    /**
     * @return coordinate y of a point
     */
    public int getY() {
        return y;
    }
    
    /**
     * set x coordinate of a point
     * @param x coordinate x of a point
     */
    public void setX(final int x) {
       this.x = x;
    }

    /**
     * set y coordinate of a point
     * @param y coordinate y of a point
     */
    public void setY(final int y) {
        this.y = y;
    }
}
