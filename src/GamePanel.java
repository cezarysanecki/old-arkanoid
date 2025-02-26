import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Platform platform;
    private Ball ball;
    private Desks desks;
    private int widthPanel = 600;
    private int heightPanel = 600;
    private int count = 0;
    private int countTill;
    private int score = 0;

    public GamePanel() {
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.setBounds(0,0, widthPanel, heightPanel);

        platform = new Platform(widthPanel, heightPanel);
        platform.setStartingPosition();
        ball = new Ball(widthPanel, platform.getStartingPlatformPositionY() - Ball.DIAMETER - 10);
        ball.setStartingPosition();
        desks = new Desks(widthPanel, 8,8);
        countTill = desks.getColumns() * desks.getRows();

        this.add(platform);
        this.add(ball);
        desks.addDesks(this);
    }

    public Platform getPlatform() {
        return platform;
    }

    public Ball getBall() {
        return ball;
    }

    public int getWidthPanel() {
        return widthPanel;
    }

    public void setStart() {
        platform.setStartingPosition();
        ball.setStartingPosition();
        desks.setVisible();
        count = 0;
        score = 0;
    }

    public void hitPointX() {
        ball.setAngle(135 + (1 - (((double) ball.middleXBall() - (double) platform.getPlatformPositionX())
                / Platform.WIDTH_PLATFORM)) * 90);
        ball.setStepBallXY();
    }

    public Desks getDesks() {
        return desks;
    }

    public void incrementCountAndScore() {
        count++;
        score = count * 50;
    }

    public int getCount() {
        return count;
    }

    public int getCountTill() {
        return countTill;
    }

    public int getScore() {
        return score;
    }
}
