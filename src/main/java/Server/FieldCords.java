package Server;

public class FieldCords {
    private transient int x;
    private transient int y;

    public FieldCords(final int x, final int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setX(final int x) {
       this.x = x;
    }

    public void setY(final int y) {
        this.y = y;
    }
}
