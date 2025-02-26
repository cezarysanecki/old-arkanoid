import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ArkanoidFrame extends JFrame {
    private GamePanel gamePanel;
    private EndDialog endDialog;
    private GameOverDialog gameOverDialog;
    private Timer timer;
    private boolean started = false;
    private boolean paused = false;

    public ArkanoidFrame() {
        int widthScreen = Toolkit.getDefaultToolkit().getScreenSize().width;
        int heightScreen = Toolkit.getDefaultToolkit().getScreenSize().height;
        gamePanel = new GamePanel();

        timer = new Timer(25, new ActionHandler());

        this.setBounds((widthScreen - 800) / 2, (heightScreen - 800) / 2,
                800, 800);
        this.addKeyListener(new KeyClick());
        this.add(gamePanel);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ArkanoidFrame();
    }

    private class KeyClick extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(!paused) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (gamePanel.getPlatform().futureLeftMove() > 0) {
                        gamePanel.getPlatform().move(Platform.MOVEMENT.LEFT);
                        if (!timer.isRunning()) gamePanel.getBall().startingMove(-gamePanel.getPlatform().getStepX());
                    }
                    else gamePanel.getPlatform().updatePosition(0);
                }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if ((gamePanel.getPlatform().futureRightMove() < gamePanel.getWidthPanel())) {
                        gamePanel.getPlatform().move(Platform.MOVEMENT.RIGHT);
                        if (!timer.isRunning()) gamePanel.getBall().startingMove(gamePanel.getPlatform().getStepX());
                    }
                    else gamePanel.getPlatform().updatePosition(gamePanel.getWidthPanel() - Platform.WIDTH_PLATFORM);
                }
                else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    timer.start();
                    started = true;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_P) {
                if(started) {
                    if(!paused) {
                        timer.stop();
                        paused = true;
                    }
                    else {
                        timer.start();
                        paused = false;
                    }
                }
            }
        }
    }

    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (gamePanel.getBall().beforeCollisionRight() > gamePanel.getWidthPanel() ||
                    gamePanel.getBall().beforeCollisionLeft() < 0) {
                gamePanel.getBall().hitX();
            }
            else if (gamePanel.getBall().beforeCollisionDown() < 0)
                gamePanel.getBall().hitY();
            else if (gamePanel.getBall().beforeCollisionUp() > gamePanel.getHeight()) {
                if(gameOverDialog == null) gameOverDialog = new GameOverDialog(ArkanoidFrame.this,
                        gamePanel.getScore());
                gameOverDialog.setScore(gamePanel.getScore());
                gameOverDialog.setVisible(true);
            }
            else if (gamePanel.getBall().getBounds().intersectsLine(gamePanel.getPlatform().getUpLine()))
                gamePanel.hitPointX();
            else if (gamePanel.getBall().getBounds().intersectsLine(gamePanel.getPlatform().getLeftLine()))
                gamePanel.getBall().hitX();
            else if (gamePanel.getBall().getBounds().intersectsLine(gamePanel.getPlatform().getRightLine()))
                gamePanel.getBall().hitX();

            for (int i = 0; i < gamePanel.getDesks().getRows(); i++) {
                for (int j = 0; j < gamePanel.getDesks().getColumns(); j++) {
                    if(gamePanel.getDesks().getDesk(i,j).isVisible()) {
                        if(gamePanel.getDesks().getDesk(i,j).getBounds().contains(gamePanel.getBall().getN())) {
                            gamePanel.getBall().hitY();
                            gamePanel.getDesks().getDesk(i,j).setVisible(false);
                            gamePanel.incrementCountAndScore();
                        }
                        else if(gamePanel.getDesks().getDesk(i,j).getBounds().contains(gamePanel.getBall().getE())) {
                            gamePanel.getBall().hitX();
                            gamePanel.getDesks().getDesk(i,j).setVisible(false);
                            gamePanel.incrementCountAndScore();
                        }
                        else if(gamePanel.getDesks().getDesk(i,j).getBounds().contains(gamePanel.getBall().getS())) {
                            gamePanel.getBall().hitY();
                            gamePanel.getDesks().getDesk(i,j).setVisible(false);
                            gamePanel.incrementCountAndScore();
                        }
                        else if(gamePanel.getDesks().getDesk(i,j).getBounds().contains(gamePanel.getBall().getW())) {
                            gamePanel.getBall().hitX();
                            gamePanel.getDesks().getDesk(i,j).setVisible(false);
                            gamePanel.incrementCountAndScore();
                        }
                        else if (gamePanel.getBall().getBounds().contains(gamePanel.getDesks().getDesk(i,j).getNE())) {
                            gamePanel.getBall().setAngle(135);
                            gamePanel.getBall().setStepBallXY();
                            gamePanel.getDesks().getDesk(i,j).setVisible(false);
                            gamePanel.incrementCountAndScore();
                            break;
                        }
                        else if (gamePanel.getBall().getBounds().contains(gamePanel.getDesks().getDesk(i,j).getSE())) {
                            gamePanel.getBall().setAngle(45);
                            gamePanel.getBall().setStepBallXY();
                            gamePanel.getDesks().getDesk(i,j).setVisible(false);
                            gamePanel.incrementCountAndScore();
                            break;
                        }
                        else if (gamePanel.getBall().getBounds().contains(gamePanel.getDesks().getDesk(i,j).getSW())) {
                            gamePanel.getBall().setAngle(-45);
                            gamePanel.getBall().setStepBallXY();
                            gamePanel.getDesks().getDesk(i,j).setVisible(false);
                            gamePanel.incrementCountAndScore();
                            break;
                        }
                        else if (gamePanel.getBall().getBounds().contains(gamePanel.getDesks().getDesk(i,j).getNW())) {
                            gamePanel.getBall().setAngle(-135);
                            gamePanel.getBall().setStepBallXY();
                            gamePanel.getDesks().getDesk(i,j).setVisible(false);
                            gamePanel.incrementCountAndScore();
                            break;
                        }
                    }
                }
            }
            gamePanel.getBall().updatePosition();

            if(gamePanel.getCount() == gamePanel.getCountTill()) {
                if(endDialog == null) endDialog = new EndDialog(ArkanoidFrame.this, gamePanel.getScore());
                endDialog.setScore(gamePanel.getScore());
                endDialog.setVisible(true);
            }
        }
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public Timer getTimer() {
        return timer;
    }
}