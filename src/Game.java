import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalTime;

public class Game extends JPanel {

    Position activePosition = new Position();
    Position endFieldPosition;
    Position startFieldPosition;
    String activeFieldColor;
    String usedFieldColor;
    String unusedFieldColor;
    String endFieldColor;
    String startFieldColor;
    double score;
    int moves;
    int gridSize;
    Field[][] fields;
    boolean isActive = false;
    LocalTime gameStartTime;
    LocalTime gameEndTime;

    public Game() {
        this.score = 0.0;
        this.moves = 0;
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    protected void initializeGame(PathMaster3000 frame) {
        frame.setSize(frame.gridSize * 100, frame.gridSize * 100);
            removeAll();
            setLayout(new GridLayout(frame.gridSize, frame.gridSize));
            fields = new Field[frame.gridSize][frame.gridSize];
            gridSize = frame.gridSize;
            if (!frame.optionsMenu.randomizeStartEnd.isSelected()) {
                generateStartEndPos();
            }
            activeFieldColor = frame.activeFieldColor;
            usedFieldColor = frame.usedFieldColor;
            unusedFieldColor = frame.unusedFieldColor;
            endFieldColor = frame.endFieldColor;
            startFieldColor = frame.startFieldColor;
            activePosition = new Position(startFieldPosition.getX(), startFieldPosition.getY());

            if (startFieldPosition.getX() >= gridSize
                    || startFieldPosition.getY() >= gridSize
                    || endFieldPosition.getX() >= gridSize
                    || endFieldPosition.getY() >= gridSize) {generateStartEndPos();}

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
                    button.setBackground(getColor(fields[i][j].getColor()));
                    button.setForeground((getColor(fields[i][j].getColor()).equals(Color.WHITE))
                            || (getColor(fields[i][j].getColor()).equals(Color.YELLOW))
                            || (getColor(fields[i][j].getColor()).equals(Color.GREEN))
                            || (getColor(fields[i][j].getColor()).equals(Color.BLUE)) ? Color.WHITE : Color.BLACK);
                    int J = j;
                    int I = i;
                    button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
                    button.addActionListener(e -> cursor(fields[I][J], frame));
                    add(button);
                }
            }
        gameStartTime = LocalTime.now();
    }

    protected void load(PathMaster3000 frame) {
        removeAll();
        gameConfig();
        if (isActive) {
            setLayout(new GridLayout(gridSize, gridSize));

            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    JButton button = new JButton(fields[i][j].getValue());
                    if (fields[i][j].getPos().getX() == activePosition.getX() && fields[i][j].getPos().getY() == activePosition.getY()) {
                        if (button.getText().equals("Start")) {
                            button.setBackground(getColor("Green"));
                        } else {
                            button.setBackground(getColor(activeFieldColor));
                        }
                    } else if (fields[i][j].isUsed() && !(fields[i][j].getValue().equals("End"))) {
                        button.setBackground(getColor(usedFieldColor));
                    } else {
                        button.setBackground(getColor(fields[i][j].getColor()));
                    }
                    button.setForeground((getColor(fields[i][j].getColor()).equals(Color.WHITE))
                            || (getColor(fields[i][j].getColor()).equals(Color.YELLOW))
                            || (getColor(fields[i][j].getColor()).equals(Color.GREEN))
                            || (getColor(fields[i][j].getColor()).equals(Color.BLUE)) ? Color.WHITE : Color.BLACK);
                    int J = j;
                    int I = i;
                    button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
                    button.addActionListener(e -> cursor(fields[I][J], frame));
                    add(button);
                }
            }
            frame.isPlayed = true;
            frame.showGame();
        }
        if (gridSize != 0) {
            frame.setSize(gridSize * 100, gridSize * 100);
            gameStartTime = LocalTime.now();
            frame.showGame();
        }

    }

    private Color getColor(String color) {
        return switch (color) {
            case "Red" -> Color.RED;
            case "Green" -> Color.GREEN;
            case "Blue" -> Color.BLUE;
            case "Yellow" -> Color.YELLOW;
            case "Orange" -> Color.ORANGE;
            case "Magenta" -> Color.MAGENTA;
            case "Cyan" -> Color.CYAN;
            case "White" -> Color.WHITE;
            default -> Color.BLUE;
        };
    }
    /**
        colors = {"Red", "Green", "Blue", "Yellow", "Orange", "Magenta", "Cyan", "White"};
     **/

    private void cursor(Field field, PathMaster3000 frame) {
//        if ((field.getPos().x == activePosition.x + 1 && field.getPos().y == activePosition.y + 1)) {
//            if (getComponent(field.getPos().y + ((field.getPos().x) * gridSize)).getBackground().equals(getColor(unusedFieldColor))) {
//                move(field);
//            } else if ((getComponent(field.getPos().y + ((field.getPos().x) * gridSize)).getBackground().equals(getColor(endFieldColor)))) {
//                move(field);
//            }
//        } else if ((field.getPos().x == activePosition.x - 1 && field.getPos().y == activePosition.y + 1)) {
//            if (getComponent(field.getPos().y + ((field.getPos().x) * gridSize)).getBackground().equals(getColor(unusedFieldColor))) {
//                move(field);
//            } else if ((getComponent(field.getPos().y + ((field.getPos().x) * gridSize)).getBackground().equals(getColor(endFieldColor)))) {
//                move(field);
//            }
//        } else if ((field.getPos().x == activePosition.x + 1 && field.getPos().y == activePosition.y - 1)) {
//            if (getComponent(field.getPos().y + ((field.getPos().x) * gridSize)).getBackground().equals(getColor(unusedFieldColor))) {
//                move(field);
//            } else if ((getComponent(field.getPos().y + ((field.getPos().x) * gridSize)).getBackground().equals(getColor(endFieldColor)))) {
//                move(field);
//            }
//        } else if ((field.getPos().x == activePosition.x - 1 && field.getPos().y == activePosition.y - 1)) {
//            if (getComponent(field.getPos().y + ((field.getPos().x) * gridSize)).getBackground().equals(getColor(unusedFieldColor))) {
//                move(field);
//            } else if ((getComponent(field.getPos().y + ((field.getPos().x) * gridSize)).getBackground().equals(getColor(endFieldColor)))) {
//                move(field);
//            }
//        }
        if ((field.getPos().x == activePosition.x + 1 && field.getPos().y == activePosition.y + 1)
                || (field.getPos().x == activePosition.x + 1 && field.getPos().y == activePosition.y - 1)
                || (field.getPos().x == activePosition.x - 1 && field.getPos().y == activePosition.y + 1)
                || (field.getPos().x == activePosition.x - 1 && field.getPos().y == activePosition.y - 1)) {
            if (getComponent(field.getPos().y + ((field.getPos().x) * gridSize)).getBackground().equals(getColor(unusedFieldColor))) {
                move(field, frame);
            }   else if ((getComponent(field.getPos().y + ((field.getPos().x) * gridSize)).getBackground().equals(getColor(endFieldColor)))) {
                move(field, frame);
            }
        }
        System.out.println(activePosition.x + " " + activePosition.y);
        System.out.println(field.getPos().x + " " + field.getPos().y);

    }

    private void move(Field field, PathMaster3000 frame) {
        if (field.getColor().equals(endFieldColor)) {
            moves++;
            System.out.println("score = " + score / moves);
            System.out.println("moves = " + moves);
            gameEndTime = LocalTime.now();
            frame.showEndGame(this);
        } else {
            fields[activePosition.getX()][activePosition.getY()].setUsed(true);
            getComponent(activePosition.y + activePosition.x * gridSize).setBackground(getColor(usedFieldColor));
            getComponent(activePosition.y + activePosition.x * gridSize).setForeground(usedFieldColor.equals("Yellow") || usedFieldColor.equals("White") ? Color.BLACK : Color.WHITE);
            activePosition.x = field.getPos().x;
            activePosition.y = field.getPos().y;
            getComponent(activePosition.y + activePosition.x * gridSize).setBackground(getColor(activeFieldColor));
            getComponent(activePosition.y + activePosition.x * gridSize).setForeground(activeFieldColor.equals("Yellow") || activeFieldColor.equals("White") ? Color.BLACK : Color.WHITE);
            score += Integer.parseInt(field.getValue());
            moves++;
        }
    }

    protected void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("GameProgression.txt"));
            writer.write("isActive = true");
            writer.newLine();
            writer.write("activeFieldColor = " + activeFieldColor);
            writer.newLine();
            writer.write("unusedFieldColor = " + unusedFieldColor);
            writer.newLine();
            writer.write("usedFieldColor = " + usedFieldColor);
            writer.newLine();
            writer.write("startFieldColor = " + startFieldColor);
            writer.newLine();
            writer.write("endFieldColor = " + endFieldColor);
            writer.newLine();
            writer.write("activePosition.x = " + activePosition.x);
            writer.newLine();
            writer.write("activePosition.y = " + activePosition.y);
            writer.newLine();
            writer.write("gridSize = " + gridSize);
            writer.newLine();
            writer.write("score = " + (int)(score));
            writer.newLine();
            writer.write("moves = " + moves);
            writer.newLine();
            writer.write("gameStartTime = " + gameStartTime.getHour() + "," + gameStartTime.getMinute() + "," + gameStartTime.getSecond());
            writer.newLine();
            writer.write("Fields=");
            for (Field[] field : fields) {
                for (int j = 0; j < fields.length; j++) {
                    writer.write(field[j].toString() + " ");
                }
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    void gameConfig() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("GameProgression.txt"));
            String read = reader.readLine();
            while (read != null) {
                if (read.split(" ")[0].equals("isActive")) {
                    this.isActive = Boolean.parseBoolean(read.split(" ")[2]);
                } else if (read.split(" ")[0].equals("activeFieldColor")) {
                    activeFieldColor = read.split(" ")[2];
                } else if (read.split(" ")[0].equals("usedFieldColor")) {
                    usedFieldColor = read.split(" ")[2];
                } else if (read.split(" ")[0].equals("unusedFieldColor")) {
                    unusedFieldColor = read.split(" ")[2];
                } else if (read.split(" ")[0].equals("endFieldColor")) {
                    endFieldColor = read.split(" ")[2];
                } else if (read.split(" ")[0].equals("startFieldColor")) {
                    startFieldColor = read.split(" ")[2];
                } else if (read.split(" ")[0].equals("activePosition.x")) {
                    activePosition.setX(Integer.parseInt(read.split(" ")[2]));
                } else if (read.split(" ")[0].equals("activePosition.y")) {
                    activePosition.setY(Integer.parseInt(read.split(" ")[2]));
                } else if (read.split(" ")[0].equals("gridSize")) {
                    gridSize = Integer.parseInt(read.split(" ")[2]);
                } else if (read.split(" ")[0].equals("gameStartTime")) {
                    gameStartTime = LocalTime.of(Integer.parseInt(read.split(" ")[2].split(",")[0])
                            ,Integer.parseInt(read.split(" ")[2].split(",")[1])
                            ,Integer.parseInt(read.split(" ")[2].split(",")[2]));
                } else if (read.split("=")[0].equals("Fields")) {
                    String[] readArr = read.split("=")[1].split(" ");
                    Field[][] flds = new Field[gridSize][gridSize];
                    for (int i = 0; i < gridSize; i++) {
                        for (int j = 0 ; j < gridSize; j++) {
                            String[] temp = readArr[j + i * gridSize].split(",");
                            flds[i][j] = new Field(temp[0], temp[1], Boolean.parseBoolean(temp[2]), new Position(Integer.parseInt(temp[3]), Integer.parseInt(temp[4])));
                        }
                    }
                    this.fields = flds;
                } else if (read.split(" ")[0].equals("moves")) {
                    moves = Integer.parseInt(read.split(" ")[2]);
                } else if (read.split(" ")[0].equals("score")) {
                    score = Integer.parseInt(read.split(" ")[2]);
                }
                read = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    void generateStartEndPos() {
            this.startFieldPosition = new Position((int) (Math.random() * gridSize), (int) (Math.random() * gridSize));
            this.endFieldPosition = new Position((int) (Math.random() * gridSize), (int) (Math.random() * gridSize));
        if (((startFieldPosition.getX() + startFieldPosition.getY()) % 2 == 0) && ((endFieldPosition.getX() + endFieldPosition.getY()) % 2 != 0)) {
            if (endFieldPosition.getX() + 1 + endFieldPosition.getY() != startFieldPosition.getX() + startFieldPosition.getY() && endFieldPosition.getX() + 1 != gridSize) {
                endFieldPosition.setX(endFieldPosition.getX() + 1);
            } else if (endFieldPosition.getX() - 1 + endFieldPosition.getY() != startFieldPosition.getX() + startFieldPosition.getY() && endFieldPosition.getX() - 1 != -1) {
                endFieldPosition.setX(endFieldPosition.getX() - 1);
            }
        } else if (((startFieldPosition.getX() + startFieldPosition.getY()) % 2 != 0) && ((endFieldPosition.getX() + endFieldPosition.getY()) % 2 == 0)) {
            if (endFieldPosition.getX() + 1 + endFieldPosition.getY() != startFieldPosition.getX() + startFieldPosition.getY() && endFieldPosition.getX() + 1 != gridSize) {
                endFieldPosition.setX(endFieldPosition.getX() + 1);
            } else if (endFieldPosition.getX() - 1 + endFieldPosition.getY() != startFieldPosition.getX() + startFieldPosition.getY() && endFieldPosition.getX() - 1 != -1) {
                endFieldPosition.setX(endFieldPosition.getX() - 1);
            } else {
                if (endFieldPosition.getY() + 1 != gridSize) {
                    endFieldPosition.setY(endFieldPosition.getY() + 1);
                } else if (endFieldPosition.getY() - 1 != -1) {
                    endFieldPosition.setY(endFieldPosition.getY() - 1);
                }
            }
        }
    }
}
