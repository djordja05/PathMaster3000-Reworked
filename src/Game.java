import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    Position activePosition;
    Position endFieldPosition = new Position(1, 3);
    Position startFieldPosition = new Position(4, 1);
    int gridSize;
    String activeFieldColor;
    String usedFieldColor;
    String unusedFieldColor;
    String endFieldColor;
    double score;
    int moves;

    public Game() {
        this.activePosition = new Position(0, 0);
        this.score = 0.0;
        this.moves = 0;
    }

    protected void initializeGame(PathMaster3000 frame) {
        frame.setSize(frame.gridSize * 100, frame.gridSize * 100);
        setLayout(new GridLayout(frame.gridSize, frame.gridSize));
        Field[][] fields = new Field[frame.gridSize][frame.gridSize];
        gridSize = frame.gridSize;
        activeFieldColor = frame.activeFieldColor;
        usedFieldColor = frame.usedFieldColor;
        unusedFieldColor = frame.unusedFieldColor;
        endFieldColor = frame.endFieldColor;

        fields[startFieldPosition.getX()][startFieldPosition.getY()] = new Field(frame.startFieldColor, "Start", true, startFieldPosition);
        fields[endFieldPosition.getX()][endFieldPosition.getY()] = new Field(frame.endFieldColor, "End", true, endFieldPosition);

        for (int i = 0; i < frame.gridSize; i++) {
            for (int j = 0; j < frame.gridSize; j++) {
                if (fields[i][j] == null) {
                    fields[i][j] = new Field(frame.unusedFieldColor, String.valueOf((int)(Math.random() * 10)), false, new Position(i, j));
                }
            }
        }

        for (int i = 0; i < frame.gridSize; i++) {
            for (int j = 0; j < frame.gridSize; j++) {
                JButton button = new JButton(fields[i][j].getValue());
                button.setBackground(getColor(fields[i][j].getColor()));
                button.setForeground((getColor(fields[i][j].getColor()).equals(Color.WHITE))
                        || (getColor(fields[i][j].getColor()).equals(Color.YELLOW))
                        || (getColor(fields[i][j].getColor()).equals(Color.GREEN)) ? Color.WHITE : Color.BLACK);
                int J = j;
                int I = i;
                button.addActionListener(e -> cursor(fields[I][J]));
                add(button);
            }
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

    private void cursor(Field field) {
        System.out.println("Press!");
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
        if (getComponent(field.getPos().y + ((field.getPos().x) * gridSize)).getBackground().equals(getColor(unusedFieldColor))) {
            if ((field.getPos().x == activePosition.x + 1 && field.getPos().y == activePosition.y + 1)
                    || (field.getPos().x == activePosition.x + 1 && field.getPos().y == activePosition.y - 1)
                    || (field.getPos().x == activePosition.x - 1 && field.getPos().y == activePosition.y + 1)
                    || (field.getPos().x == activePosition.x - 1 && field.getPos().y == activePosition.y - 1)) {
                move(field);
            }

        } else if ((getComponent(field.getPos().y + ((field.getPos().x) * gridSize)).getBackground().equals(getColor(endFieldColor)))) {
            if ((field.getPos().x == activePosition.x + 1 && field.getPos().y == activePosition.y + 1)
                    || (field.getPos().x == activePosition.x + 1 && field.getPos().y == activePosition.y - 1)
                    || (field.getPos().x == activePosition.x - 1 && field.getPos().y == activePosition.y + 1)
                    || (field.getPos().x == activePosition.x - 1 && field.getPos().y == activePosition.y - 1)) {
                move(field);
            }
        }
        System.out.println(activePosition.x + " " + activePosition.y);
        System.out.println(field.getPos().x + " " + field.getPos().y);

    }

    private void move(Field field) {
        if (field.getColor().equals(endFieldColor)) {
            moves++;
            score /= moves;
            System.out.println("score = " + score);
            System.out.println("moves = " + moves);
        } else {
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


}
