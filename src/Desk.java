import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Desk extends JComponent {
    public static final int WIDTH_DESK = 60;
    public static final int HEIGHT_DESK = 30;
    private int deskPositionX;
    private int deskPositionY;

    public Desk(int positionX, int positionY) {
        this.deskPositionX = positionX;
        this.deskPositionY = positionY;
        setBounds(positionX, positionY, WIDTH_DESK, HEIGHT_DESK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D rec = new Rectangle2D.Double(0,0, WIDTH_DESK, HEIGHT_DESK);
        g2.setColor(Color.GREEN);
        g2.fill(rec);
        g2.draw(rec);
    }

    public Point2D getNW() {
        return new Point2D.Double(deskPositionX, deskPositionY);
    }

    public Point2D getNE() {
        return new Point2D.Double(deskPositionX + WIDTH_DESK, deskPositionY);
    }

    public Point2D getSE() {
        return new Point2D.Double(deskPositionX + WIDTH_DESK, deskPositionY + HEIGHT_DESK);
    }

    public Point2D getSW() {
        return new Point2D.Double(deskPositionX, deskPositionY + HEIGHT_DESK);
    }
}
