import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class SnakeTest {

    private Snake snake;

    @BeforeEach // TODO why is this not working?
    public void setUp() {

        // Create a new instance of snake that starts at point 90, 90:
        snake = new Snake(90, 90);

    }

    @Test
    void testConstructor() {
        // Snake snake = new Snake(90, 90);
        List<Position> expected = new LinkedList<Position>();
        expected.add(new Position(60, 90));
        expected.add(new Position(75, 90));
        expected.add(new Position(90, 90));
        List<Position> expected1 = new LinkedList<Position>();
        expected1.add(new Position(60, 90));
        expected1.add(new Position(75, 90));
        expected1.add(new Position(90, 90));

        List<Position> actual = snake.getElements();
        assertEquals(expected, actual, "Elements are the same");

    }
}
