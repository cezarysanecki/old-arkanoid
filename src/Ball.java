import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Ball extends JComponent {
    public static final int DIAMETER = 15;                          //Promień piłki
    public static double RADIUS_TO_ANGLE = 180 / Math.PI;           //Zamiana radianów na stopnie
    private double angleInRadian;         //Kąt wektora piłki
    private int stepBall = 5;
    private double stepBallX;
    private double stepBallY;
    private double startingBallPositionX;
    private double startingBallPositionY;
    private double ballPositionX;
    private double ballPositionY;

    public Ball(int widthPanel, int startingBallPositionY) { //ustawia początkowe wartości pozycjonowania
        this.startingBallPositionX = (widthPanel - DIAMETER) / 2;
        this.startingBallPositionY = startingBallPositionY;
        this.ballPositionX = startingBallPositionX;
        this.ballPositionY = startingBallPositionY;
        this.setAngle(135);
        setStepBallXY();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D ball = new Ellipse2D.Double(0, 0, DIAMETER, DIAMETER);
        g2.setColor(Color.RED);
        g2.fill(ball);
        g2.draw(ball);
    }

    public void setStartingPosition() {                                             // ustawia platformę w początkowej pozycji
        setBounds((int) startingBallPositionX, (int) startingBallPositionY, DIAMETER, DIAMETER);
        ballPositionX = startingBallPositionX;
        ballPositionY = startingBallPositionY;
        this.setAngle(135);
        setStepBallXY();
    }

    public void setAngle(double angle) {
        this.angleInRadian = angle / RADIUS_TO_ANGLE;
    }

    public void startingMove(int stepX) {
        ballPositionX += stepX;
        setBounds((int) ballPositionX, (int) startingBallPositionY, DIAMETER, DIAMETER);
    }

    public void updatePosition() {                                                  // ustawia platformę po aktualizacji
        ballPositionX += stepBallX;
        ballPositionY += stepBallY;
        setBounds((int) ballPositionX, (int) ballPositionY, DIAMETER, DIAMETER);
    }

    public void hitX(){
        stepBallX = -stepBallX;
    }

    public void hitY(){
        stepBallY = -stepBallY;
    }

    public int beforeCollisionLeft() {
        return (int) (ballPositionX + stepBallX);
    }

    public int beforeCollisionRight() {
        return (int) (ballPositionX + DIAMETER + stepBallX);
    }

    public int beforeCollisionDown() {
        return (int) (ballPositionY + stepBallY);
    }

    public int beforeCollisionUp() {
        return (int) (ballPositionY + DIAMETER + stepBallY);
    }

    public int middleXBall() {
        return (int) (ballPositionX + DIAMETER / 2);
    }

    public void setStepBallXY() {
        this.stepBallX = stepBall * Math.sin(angleInRadian);
        this.stepBallY = stepBall * Math.cos(angleInRadian);
    }

    public Point2D getN() {
        return new Point2D.Double(ballPositionX + DIAMETER / 2, ballPositionY);
    }

    public Point2D getE() {
        return new Point2D.Double(ballPositionX + DIAMETER, ballPositionY  + DIAMETER / 2);
    }

    public Point2D getS() {
        return new Point2D.Double(ballPositionX + DIAMETER / 2, ballPositionY + DIAMETER);
    }

    public Point2D getW() {
        return new Point2D.Double(ballPositionX, ballPositionY + DIAMETER / 2);
    }
}