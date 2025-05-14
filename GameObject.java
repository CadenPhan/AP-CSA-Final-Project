import java.awt.*;

public abstract class GameObject {
    protected int x; // X position
    protected int y; // Y position
    protected int width; // Width of the object
    protected int height; // Height of the object

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void draw(Graphics g); // Method for drawing

    public boolean isInBounds(int targetX) {
        return targetX >= x && targetX <= x + width; // Check if a target X is within bounds
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }
}