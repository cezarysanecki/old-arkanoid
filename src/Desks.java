import java.util.ArrayList;

public class Desks {
    private ArrayList<ArrayList<Desk>> desks;
    public static final int DISTANCEX = 12;
    public static final int DISTANCEY = 8;
    private int columns;
    private int rows;

    public Desks(int widthPanel, int columns, int rows) {
        int startPositionX = (widthPanel - (columns * Desk.WIDTH_DESK) - ((columns - 1) * DISTANCEX)) / 2;
        this.columns = columns;
        this.rows = rows;
        desks = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<Desk> desksRow = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                desksRow.add(new Desk(startPositionX + j * (DISTANCEX + Desk.WIDTH_DESK),
                        DISTANCEY + i * (DISTANCEY + Desk.HEIGHT_DESK)));
            }
            desks.add(desksRow);
        }
    }

    public void addDesks(GamePanel gamePanel) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                gamePanel.add(desks.get(i).get(j));
            }
        }
    }

    public void setVisible() {
        for (int i = 0; i < rows; i++) {
            for(Desk d : desks.get(i)) {
                d.setVisible(true);
            }
        }
    }

    public Desk getDesk(int row, int column) {
        return  desks.get(row).get(column);
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
}
