import java.util.Objects;

public class Position implements Comparable<Position> {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return ("x = " + x + " y = " + y);
    }

    @Override
    public boolean equals(Object o) {

        // Check referential equality + edge cases:
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        }

        // Type case object to position and return whether their elements are equal:
        Position p = (Position) o;
        return Objects.equals(x, p.x) && Objects.equals(y, p.y);
    }

    @Override
    public int compareTo(Position p) {
        // Check if null:
        if (p == null) {
            throw new NullPointerException();
        }

        // Check for referential equality:
        if (this == p) {
            return 0;
        }

        // Check for structural equality (prioritizing x value):
        if (x > p.x) {
            return 1;
        } else if (x < p.x) {
            return -1;
        } else if (y > p.y) {
            return 1;
        } else if (y < p.y) {
            return -1;
        }
        return 0;
    }
}