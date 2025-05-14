import java.awt.*;

public class Tile extends GameObject {
    public Tile(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height); // Draw the tile
    }

    public void move(int speed) {
        x += speed; // Move the tile
    }
}