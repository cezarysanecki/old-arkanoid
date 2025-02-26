import javax.swing.*;
import java.awt.*;

public class GameOverDialog extends JDialog {
    private JLabel text;

    public GameOverDialog(ArkanoidFrame owner, int score) {
        super(owner, "Koniec", true);
        setSize(400, 200);
        setLayout(new GridBagLayout());

        JPanel panelName = new JPanel();
        Font font = new Font("Serif", Font.BOLD, 15);
        text = new JLabel("Przegrałeś! Wynik: " + score);
        text.setFont(font);
        panelName.add(text, BorderLayout.SOUTH);
        add(panelName, new GBC(0, 0,2,1));

        JButton buttonReset = new JButton("Reset");
        buttonReset.addActionListener(e -> {
            owner.getGamePanel().setStart();
            owner.getTimer().stop();
            setVisible(false);
        });

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        add(buttonReset, new GBC(0,1,1,1));

        JButton buttonExit = new JButton("Wyjście");
        buttonExit.addActionListener(e -> System.exit(0));
        add(buttonExit, new GBC(1,1,1,1));

        setLocation(owner.getLocation().x + (owner.getWidth() - getWidth()) / 2, owner.getLocation().y +
                (owner.getHeight() - getHeight()) / 2);
        setResizable(false);
    }

    public void setScore(int score) {
        text.setText("Przegrałeś! Wynik: " + score);
    }
}