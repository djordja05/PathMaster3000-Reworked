import javax.swing.*;
import java.awt.*;

public class StartEndPositionSetter extends JPanel {

    Field[][] fields;
    int gridSize;
    boolean hasStart;

    public StartEndPositionSetter() {
        hasStart = false;
    }

    public void initializeStartEndPosSetter(PathMaster3000 frame) {
        frame.setSize(frame.gridSize * 100, frame.gridSize * 100);
        removeAll();
        setLayout(new GridLayout(frame.gridSize, frame.gridSize));
        fields = new Field[frame.gridSize][frame.gridSize];
        gridSize = frame.gridSize;

        for (int i = 0; i < frame.gridSize; i++) {
            for (int j = 0; j < frame.gridSize; j++) {
                if (fields[i][j] == null) {
                    fields[i][j] = new Field("Green", "Click!", false, new Position(i, j));
                }
            }
        }

        for (int i = 0; i < frame.gridSize; i++) {
            for (int j = 0; j < frame.gridSize; j++) {
                JButton button = new JButton(fields[i][j].getValue());
                button.setBackground(Color.BLUE);
                int J = j;
                int I = i;
                button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
                button.addActionListener(e -> setStartEnd(fields[I][J], frame));
                add(button);
            }
        }


    }

    public void setStartEnd(Field field, PathMaster3000 frame) {
        if (!hasStart) {
            getComponent(field.getPos().y + ((field.getPos().x) * gridSize)).setBackground(Color.GREEN);
            frame.game.startFieldPosition = new Position(field.getPos().x, field.getPos().y);
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    if (((field.getPos().x + field.getPos().y) % 2 == 0) && ((i + j) % 2 != 0)) {
                        if (!(getComponent(j + (i * gridSize)).getBackground() == Color.GREEN)) {
                            JButton temp = (JButton) getComponent(j + (i * gridSize));
                            temp.setBackground(Color.BLACK);
                            temp.setText("");
                            temp.revalidate();
                        }
                    } else if (((field.getPos().x + field.getPos().y) % 2 != 0) && ((i + j) % 2 == 0)) {
                        JButton temp = (JButton) getComponent(j + (i * gridSize));
                        temp.setBackground(Color.BLACK);
                        temp.setText("");
                        temp.revalidate();
                    }
                }
            }
            hasStart = true;
        } else {
            if ((frame.game.startFieldPosition.getX() + frame.game.startFieldPosition.getY() + field.getPos().x + field.getPos().y) % 2 == 0) {
                    frame.game.endFieldPosition = new Position(field.getPos().x, field.getPos().y);
                    hasStart = false;
                    removeAll();
                    frame.showOptionsMenu();
            }
        }
    }
}
