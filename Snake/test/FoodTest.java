import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FoodTest {

    private Food food;
    private int startWidth;
    private Position p;
    
    @BeforeEach
    void setUp() {
        p = new Position(10, 10);
        food = new Food(p);
        startWidth = food.getWidth();
    }
    
    /**
     * Tests whether intersect() will return true when intersecting
     */
    @Test
    void testIntersectTrue() {
        assertTrue(food.intersect(9, 9, 5, 5));
    }
    
    /**
     * Tests whether intersect will return false when not intersecting
     */
    @Test
    void testIntersectFalse() {
        assertFalse(food.intersect(50, 50, 20, 20));
    }
    
    /**
     * Tests whether the shrink function will shrink the food if not eaten anything.
     * It should not.
     */
    @Test
    void testShrinkNotReady() {
        
        // Call shrink function
        food.shrink();
        
        // Assert that that the width did not change
        assertEquals(startWidth, food.getWidth(), "Width should not change.");
    }
    
    /**
     * Tests whether the shrink function will shrink the food if eaten 10 times already.
     * It should.
     */
    @Test
    void testShrinkReady() {
        
        // Get eaten 10 times
        for (int i = 0; i < 10; i++) {
            food.eaten(p);
        }
        
        // Call the shrink function
        food.shrink();
        
        // Assert that the width did change
        assertNotEquals(startWidth, food.getWidth(), "Width should change.");
    }
    
    /**
     * Tests whether the shrink function will work after 11 times (should not).
     */
    @Test
    void testShrinkTooSmall() {
        
        // Shrink 10 times
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 10; j++) {
                food.eaten(p);
            }
            food.shrink();
        }
        
        // Get eaten 10 more times
        for (int i = 0; i < 10; i++) {
            food.eaten(p);
        }
        
        // Get the current width
        int w = food.getWidth();
        
        //Try to get shrink again
        food.shrink();
        
        // Assert that the width after 11 shrinks and 12 shrinks is the same
        assertEquals(w, food.getWidth(), "With should be the same.");
    }

}
