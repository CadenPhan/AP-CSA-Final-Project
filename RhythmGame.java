import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class RhythmGame extends JPanel implements ActionListener {
    private Timer timer;
    private int score = 0;
    private Tile tile; // The moving tile
    private Hitbox hitbox; // The hitbox area
    private int direction = 1; 
    private int speed = 2; // Initial speed of the tile
    private String feedbackMessage = ""; // Message to display for hits or misses (It's a little bugged)
    private Random random;
    private boolean gameOver = false; // State of game variable

    // Feedback messages
    private String[] missMessages = {
        "Missed!",
        "Too early!",
        "Try again!",
        "Not quite!",
        "Oops!"
    };

    private String[] hitMessages = {
        "Great job!",
        "Awesome!",
        "You're on fire!",
        "Fantastic!",
        "Well done!"
    };

    public RhythmGame() {
        tile = new Tile(0, getHeight() + 137, 100, 100); 
        hitbox = new Hitbox(getWidth() + 125, getHeight() + 125, 125, 125); // Hitbox location & w/h
        timer = new Timer(16, this); // Testing* 60 FPS?
        timer.start();
        random = new Random(); // Initialize the random number generator

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode());
            }
        });
        requestFocusInWindow(); // yt video test
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        tile.draw(g); // Draw the tile
        hitbox.draw(g); // Draw the hitbox

        g.drawString("Score: " + score, 10, 20);
        
        // Draw feedback message if it exists 
        if (!feedbackMessage.isEmpty()) {
            g.setColor(Color.RED);
            g.drawString(feedbackMessage, 150, 200);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update tile position
        tile.move(direction * speed); // Move the tile
        if (tile.getX() >= getWidth() - tile.width || tile.getX() <= 0) {
            direction *= -1; // Change direction if it hits the edge
        }
        repaint();
    }

    private void handleKeyPress(int keyCode) {
        if (keyCode == KeyEvent.VK_SPACE) {
            // Check if the tile is in the hitbox
            if (hitbox.isInBounds(tile.getX())) {
                score++; // Increase score
                feedbackMessage = hitMessages[random.nextInt(hitMessages.length)]; // Feedback for a correct hit
                speed = (int) (speed * 1.25); // Increase speed exponentially
            } else {
                feedbackMessage = missMessages[random.nextInt(missMessages.length)]; // Feedback for a miss
                gameOver = true;
                restartGame();
            }
        }
        repaint();
    }
    
    private void restartGame() {
        score = 0; // Reset score
        speed = 2; // Reset speed
        direction = 1; // Reset direction
        tile.setX(0); // Reset tile position
        feedbackMessage = missMessages[random.nextInt(missMessages.length)]; // Display miss message
        gameOver = false; // Reset game over function
        repaint(); // Repaint game
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Rhythm Game");
        RhythmGame game = new RhythmGame();
        frame.add(game);
        frame.setSize(400, 600); // Increased height for better visibility
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}