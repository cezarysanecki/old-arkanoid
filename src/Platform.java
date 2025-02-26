import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Platform extends JComponent {
    public static final int WIDTH_PLATFORM = 100;
    public static final int HEIGHT_PLATFORM = 20;
    public enum MOVEMENT {LEFT, RIGHT};
    private int startingPlatformPositionX;
    private int startingPlatformPositionY;
    private int stepX = 8;
    private int platformPositionX;
    private int platformPositionY;

    public Platform(int widthPanel, int heightPanel) { //ustawia początkowe wartości pozycjonowania
        this.startingPlatformPositionX = (widthPanel - WIDTH_PLATFORM) / 2;
        this.startingPlatformPositionY = heightPanel - HEIGHT_PLATFORM;
        this.platformPositionX = startingPlatformPositionX;
        this.platformPositionY = startingPlatformPositionY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D rec = new Rectangle2D.Double(0, 0, WIDTH_PLATFORM, HEIGHT_PLATFORM);
        g2.setColor(Color.YELLOW);
        g2.fill(rec);
        g2.draw(rec);
    }

    public void setStartingPosition() {                                             // ustawia platformę w początkowej pozycji
        setBounds(startingPlatformPositionX, startingPlatformPositionY, WIDTH_PLATFORM, HEIGHT_PLATFORM);
        this.platformPositionX = startingPlatformPositionX;
        this.platformPositionY = startingPlatformPositionY;
    }

    public int getStartingPlatformPositionY() {
        return startingPlatformPositionY;
    }

    public void move(MOVEMENT m) {
        if (m == MOVEMENT.LEFT) platformPositionX -= stepX;
        else if (m == MOVEMENT.RIGHT) platformPositionX += stepX;
        setBounds(platformPositionX, platformPositionY, WIDTH_PLATFORM, HEIGHT_PLATFORM);
    }

    public int futureLeftMove() {
        return platformPositionX - stepX;
    }

    public int futureRightMove() {
        return platformPositionX + stepX + WIDTH_PLATFORM;
    }

    public void updatePosition(int positionX) {
        setBounds(positionX, platformPositionY, WIDTH_PLATFORM, HEIGHT_PLATFORM);
    }

    public Line2D getUpLine() {
        return new Line2D.Double(platformPositionX, platformPositionY,
                platformPositionX + WIDTH_PLATFORM, platformPositionY);
    }

    public Line2D getLeftLine() {
        return new Line2D.Double(platformPositionX, platformPositionY,
                platformPositionX, platformPositionY + HEIGHT_PLATFORM);
    }

    public Line2D getRightLine() {
        return new Line2D.Double(platformPositionX + WIDTH_PLATFORM, platformPositionY,
                platformPositionX + WIDTH_PLATFORM, platformPositionY+ HEIGHT_PLATFORM);
    }

    public int getStepX() {
        return stepX;
    }

    public int getPlatformPositionX() {
        return platformPositionX;
    }
}