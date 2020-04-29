import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PositionTest {

    private Position p1;
    private Position p2;
    private Position p3;
    
    @BeforeEach
    void setUp() {
        p1 = new Position(1, 2);
        p2 = new Position(1, 2);
        p3 = new Position(2, 3);
    }
    
    /**
     * Test whether equality works when are equal
     */
    @Test
    void testEqualityEqual() {
        assertEquals(p1, p2, "Should be equal.");
    }
    
    /**
     * Test whether equality works when are not equal
     */
    @Test
    void testEqualityNotEqual() {
        assertNotEquals(p1, p3, "Should not be equal.");
    }
    
    /**
     * Test whether compareTo() returns the correct value when less than
     */
    @Test
    void testCompareToLessThan() {
        assertEquals(-1, p1.compareTo(p3), "Should be less than.");
    }
    
    /**
     * Test whether compareTo() returns the correct value when greater than
     */
    @Test
    void testCompareToGreaterThan() {
        assertEquals(1, p3.compareTo(p2), "Should be greater than");
    }
    
    /**
     * Test whether compareTo() returns the correct value when equal to
     */
    @Test
    void testCompareToEqualTo() {
        System.out.println(p1.compareTo(p2));
        assertEquals(0, p1.compareTo(p2), "Should be equal to");
    }

}
