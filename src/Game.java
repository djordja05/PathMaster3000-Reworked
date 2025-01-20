import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class Game extends JPanel {

    Position activePosition = new Position();
    Position endFieldPosition;
    Position startFieldPosition;
    Color activeFieldColor;
    Color usedFieldColor;
    Color unusedFieldColor;
    Color endFieldColor;
    Color startFieldColor;
    double score;
    int moves;
    int gridSize;
    Field[][] fields;
    LocalTime gameStartTime;
    LocalTime gameEndTime;
    int elapsedTime;
    JPanel gameContent = new JPanel();
    SaveLoadManager saveLoadManager = new SaveLoadManager();
    boolean saveExists = false;

    public Game() {
        this.score = 0.0;
        this.moves = 0;
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setLayout(new BorderLayout());
    }

    protected void initializeGame(PathMaster3000 frame, boolean isSetPos) {
        frame.setSize(frame.gridSize * 100, frame.gridSize * 100);
        gameContent.removeAll();
        initializeMenuBar(frame);
        gameContent.setLayout(new GridLayout(frame.gridSize, frame.gridSize));
        fields = new Field[frame.gridSize][frame.gridSize];
        gridSize = frame.gridSize;
        if (!isSetPos) {
            generateStartEndPos();
        }
        activeFieldColor = frame.activeFieldColor;
        usedFieldColor = frame.usedFieldColor;
        unusedFieldColor = frame.unusedFieldColor;
        endFieldColor = frame.endFieldColor;
        startFieldColor = frame.startFieldColor;
        activePosition = new Position(startFieldPosition.getX(), startFieldPosition.getY());
        moves = 0;
        score = 0;

        if (startFieldPosition.getX() >= gridSize
                || startFieldPosition.getY() >= gridSize
                || endFieldPosition.getX() >= gridSize
                || endFieldPosition.getY() >= gridSize) {
            generateStartEndPos();
        }

        fields[startFieldPosition.getX()][startFieldPosition.getY()] = new Field(startFieldColor, "Start", true, startFieldPosition);
        fields[endFieldPosition.getX()][endFieldPosition.getY()] = new Field(endFieldColor, "End", true, endFieldPosition);

        for (int i = 0; i < frame.gridSize; i++) {
            for (int j = 0; j < frame.gridSize; j++) {
                if (fields[i][j] == null) {
                    fields[i][j] = new Field(frame.unusedFieldColor, String.valueOf((int) (Math.random() * 10)), false, new Position(i, j));
                }
            }
        }

        for (int i = 0; i < frame.gridSize; i++) {
            for (int j = 0; j < frame.gridSize; j++) {
                JButton button = new JButton(fields[i][j].getValue());
                button.setBackground(fields[i][j].getColor());
                if (fields[i][j].getColor().equals(Color.WHITE) || fields[i][j].getColor().equals(Color.YELLOW) || fields[i][j].getColor().equals(Color.BLUE)) {
                    button.setForeground(Color.WHITE);
                } else {
                    button.setForeground(Color.BLACK);
                }
                int J = j;
                int I = i;
                button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
                button.addActionListener(e -> cursor(fields[I][J], frame));
                gameContent.add(button);
            }
        }
        add(gameContent, BorderLayout.CENTER);
        gameStartTime = LocalTime.now();
    }

    protected Color getColor(String color) {
        return switch (color) {
            case "Red" -> Color.RED;
            case "Green" -> Color.GREEN;
            case "Blue" -> Color.BLUE;
            case "Yellow" -> Color.YELLOW;
            case "Orange" -> Color.ORANGE;
            case "Magenta" -> Color.MAGENTA;
            case "Cyan" -> Color.CYAN;
            case "White" -> Color.WHITE;
            case "Gray" -> Color.GRAY;
            case "LightGray" -> Color.LIGHT_GRAY;
            default -> Color.BLUE;
        };
    }

    /**
     * colors = {"Red", "Green", "Blue", "Yellow", "Orange", "Magenta", "Cyan", "White"};
     **/

    void cursor(Field field, PathMaster3000 frame) {
        if ((field.getPos().x == activePosition.x + 1 && field.getPos().y == activePosition.y)
                || (field.getPos().x == activePosition.x && field.getPos().y == activePosition.y + 1)
                || (field.getPos().x == activePosition.x - 1 && field.getPos().y == activePosition.y)
                || (field.getPos().x == activePosition.x && field.getPos().y == activePosition.y - 1)) {
            if (gameContent.getComponent(field.getPos().y + ((field.getPos().x) * gridSize)).getBackground().equals(unusedFieldColor)) {
                move(field, frame);
            } else if ((gameContent.getComponent(field.getPos().y + ((field.getPos().x) * gridSize)).getBackground().equals(endFieldColor))) {
                move(field, frame);
            }
        }
        System.out.println(activePosition.x + " " + activePosition.y);
        System.out.println(field.getPos().x + " " + field.getPos().y);

    }

    private void move(Field field, PathMaster3000 frame) {
        if (field.getValue().equals("End")) {
            moves++;
            System.out.println("score = " + score / moves);
            System.out.println("moves = " + moves);
            gameEndTime = LocalTime.now();
            elapsedTime += ((gameEndTime.getHour() * 360 + gameEndTime.getMinute() * 60 + gameEndTime.getSecond()) - (gameStartTime.getHour() * 360 + gameStartTime.getMinute() * 60
                    + gameStartTime.getSecond()));
            Object[] options = {"Main Menu"};
            int select = JOptionPane.showOptionDialog(frame, "Moves = " + moves + "\nSum of selected field values = " +
                            score + "\nScore = " + score / moves + "\nTime = " + (elapsedTime),
                    "Results",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (select == 0) {
                frame.showMainMenu();
            }
        } else {
            fields[activePosition.getX()][activePosition.getY()].setUsed(true);
            gameContent.getComponent(activePosition.y + activePosition.x * gridSize).setBackground(usedFieldColor);
            gameContent.getComponent(activePosition.y + activePosition.x * gridSize).setForeground(usedFieldColor.equals(Color.YELLOW) || usedFieldColor.equals(Color.WHITE) ? Color.BLACK : Color.WHITE);
            activePosition.x = field.getPos().x;
            activePosition.y = field.getPos().y;
            gameContent.getComponent(activePosition.y + activePosition.x * gridSize).setBackground(activeFieldColor);
            gameContent.getComponent(activePosition.y + activePosition.x * gridSize).setForeground(activeFieldColor.equals(Color.YELLOW) || activeFieldColor.equals(Color.WHITE) ? Color.BLACK : Color.WHITE);
            score += Integer.parseInt(field.getValue());
            moves++;
        }
    }

    void generateStartEndPos() {
        this.startFieldPosition = new Position((int) (Math.random() * gridSize), (int) (Math.random() * gridSize));
        this.endFieldPosition = new Position((int) (Math.random() * gridSize), (int) (Math.random() * gridSize));
        if (endFieldPosition.getY() + 1 != gridSize) {
            endFieldPosition.setY(endFieldPosition.getY() + 1);
        } else if (endFieldPosition.getY() - 1 != -1) {
            endFieldPosition.setY(endFieldPosition.getY() - 1);
        }
        if (startFieldPosition.getX() == endFieldPosition.getX() && startFieldPosition.getY() == endFieldPosition.getY()) {
            generateStartEndPos();
        }
    }

    void initializeMenuBar(PathMaster3000 frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Game");
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(e -> saveLoadManager.save(this));
        JMenuItem mainMenu = new JMenuItem("Main Menu");
        mainMenu.addActionListener(e -> frame.showMainMenu());
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));
        fileMenu.add(save);
        fileMenu.add(mainMenu);
        fileMenu.add(exit);
        menuBar.add(fileMenu);
        add(menuBar, BorderLayout.NORTH);
    }

}
