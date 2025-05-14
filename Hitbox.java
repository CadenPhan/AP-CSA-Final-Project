import java.awt.*;

public class Hitbox extends GameObject {
    public Hitbox(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect(x, y, width, height); // Draw the hitbox
    }
}