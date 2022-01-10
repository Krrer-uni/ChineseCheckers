package Client;

import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class FieldTest {

    @Test
    public void EmptyFieldTest(){
        EmptyField field = new EmptyField();
        assertNull(field.getField());
        assertEquals(field.getOwnerId() ,-1);
        assertFalse(field.isActive());
        field.setActive(true);
        assertFalse(field.isActive());
    }

    @Test
    public void PlayerFieldTest(){
        PlayerField field = new PlayerField(2,new Point(1,2), new Dimension(10,10));
        assertNotNull(field.getField());
        assertEquals(field.getOwnerId() ,2);
        assertFalse(field.isActive());
        field.setActive(true);
        assertTrue(field.isActive());
    }
}
