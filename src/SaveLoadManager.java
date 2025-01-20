import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalTime;

public class SaveLoadManager {

    public SaveLoadManager() {
        try {
            new File("GameProgression.txt").createNewFile();
        } catch (Exception e) {
            System.out.println("SaveLoadManager constructor error = " + e.getMessage());
        }
    }

    public void save(Game game) {
        game.gameEndTime = LocalTime.now();
        try {

            new File("GameProgression.txt").createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter("GameProgression.txt"));
            writer.write("activeFieldColor = " + colorToString(game.activeFieldColor));
            writer.newLine();
            writer.write("unusedFieldColor = " + colorToString(game.unusedFieldColor));
            writer.newLine();
            writer.write("usedFieldColor = " + colorToString(game.usedFieldColor));
            writer.newLine();
            writer.write("startFieldColor = " + colorToString(game.startFieldColor));
            writer.newLine();
            writer.write("endFieldColor = " + colorToString(game.endFieldColor));
            writer.newLine();
            writer.write("activePosition.x = " + game.activePosition.x);
            writer.newLine();
            writer.write("activePosition.y = " + game.activePosition.y);
            writer.newLine();
            writer.write("gridSize = " + game.gridSize);
            writer.newLine();
            writer.write("score = " + (int) (game.score));
            writer.newLine();
            writer.write("moves = " + game.moves);
            writer.newLine();
            writer.write("elapsedTime = " + ((game.gameEndTime.getHour() * 360 + game.gameEndTime.getMinute() * 60 + game.gameEndTime.getSecond()) -
                    (game.gameStartTime.getHour() * 360 + game.gameStartTime.getMinute() * 60 + game.gameStartTime.getSecond())));
            writer.newLine();
            writer.write("Fields=");
            for (Field[] field : game.fields) {
                for (int j = 0; j < game.fields.length; j++) {
                    writer.write(field[j].toString() + " ");
                }
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("save() error = " + e);
        }
    }

    public void load(PathMaster3000 frame, Game game) {
        if (new File("GameProgression.txt").exists()) {
            game.gameContent.removeAll();
            gameConfig(game);
            if (game.saveExists) {
                game.gameContent.setLayout(new GridLayout(game.gridSize, game.gridSize));
                for (int i = 0; i < game.gridSize; i++) {
                    for (int j = 0; j < game.gridSize; j++) {
                        JButton button = new JButton(game.fields[i][j].getValue());
                        if (game.fields[i][j].getPos().getX() == game.activePosition.getX() && game.fields[i][j].getPos().getY() == game.activePosition.getY()) {
                            if (button.getText().equals("Start")) {
                                button.setBackground(Color.GREEN);
                            } else {
                                button.setBackground(game.activeFieldColor);
                            }
                        } else if (game.fields[i][j].isUsed() && !(game.fields[i][j].getValue().equals("End"))) {
                            button.setBackground(game.usedFieldColor);
                        } else {
                            button.setBackground(game.fields[i][j].getColor());
                        }
                        if (game.fields[i][j].getColor().equals(Color.WHITE) || game.fields[i][j].getColor().equals(Color.YELLOW) || game.fields[i][j].getColor().equals(Color.BLUE)) {
                            button.setForeground(Color.WHITE);
                        } else {
                            button.setForeground(Color.BLACK);
                        }
                        if (game.fields[i][j].getValue().equals("End")) {
                            button.setBackground(game.endFieldColor);
                        }
                        int J = j;
                        int I = i;
                        button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
                        button.addActionListener(e -> game.cursor(game.fields[I][J], frame));
                        game.gameContent.add(button);
                    }
                }
                game.gameStartTime = LocalTime.now();
                if (game.gridSize != 0) {
                    frame.setSize(game.gridSize * 100, game.gridSize * 100);
                    frame.showGame();
                }
                game.add(game.gameContent, BorderLayout.CENTER);
                game.initializeMenuBar(frame);
            }

        } else {
            JOptionPane.showOptionDialog(frame,
                    "You don't have a saved game progress!",
                    "Error", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null, new String[]{"Ok"},
                    0);
        }
    }

    protected String colorToString(Color color) {
        return color.getRed() + ";" + color.getGreen() + ";" + color.getBlue();
    }

    private void gameConfig(Game game) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("GameProgression.txt"));
            String read = reader.readLine();
            int count = 0;
            while (read != null) {
                if (read.split(" ")[0].equals("activeFieldColor")) {
                    String[] temp = read.split(" ")[2].split(";");
                    game.activeFieldColor = new Color(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
                    count++;
                } else if (read.split(" ")[0].equals("usedFieldColor")) {
                    String[] temp = read.split(" ")[2].split(";");
                    game.usedFieldColor = new Color(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
                    count++;
                } else if (read.split(" ")[0].equals("unusedFieldColor")) {
                    String[] temp = read.split(" ")[2].split(";");
                    game.unusedFieldColor = new Color(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
                    count++;
                } else if (read.split(" ")[0].equals("endFieldColor")) {
                    String[] temp = read.split(" ")[2].split(";");
                    game.endFieldColor = new Color(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
                    count++;
                } else if (read.split(" ")[0].equals("startFieldColor")) {
                    String[] temp = read.split(" ")[2].split(";");
                    game.startFieldColor = new Color(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
                    count++;
                } else if (read.split(" ")[0].equals("activePosition.x")) {
                    game.activePosition.setX(Integer.parseInt(read.split(" ")[2]));
                    count++;
                } else if (read.split(" ")[0].equals("activePosition.y")) {
                    game.activePosition.setY(Integer.parseInt(read.split(" ")[2]));
                    count++;
                } else if (read.split(" ")[0].equals("gridSize")) {
                    game.gridSize = Integer.parseInt(read.split(" ")[2]);
                    count++;
                } else if (read.split(" ")[0].equals("elapsedTime")) {
                    game.elapsedTime = Integer.parseInt(read.split(" ")[2]);
                    count++;
                } else if (read.split("=")[0].equals("Fields")) {
                    count++;
                    String[] readArr = read.split("=")[1].split(" ");
                    Field[][] flds = new Field[game.gridSize][game.gridSize];
                    for (int i = 0; i < game.gridSize; i++) {
                        for (int j = 0; j < game.gridSize; j++) {
                            String[] temp = readArr[j + i * game.gridSize].split(",");
                            String[] colorTemp = temp[0].split(";");
                            flds[i][j] = new Field(game.unusedFieldColor, temp[1], Boolean.parseBoolean(temp[2]), new Position(Integer.parseInt(temp[3]), Integer.parseInt(temp[4])));
                        }
                    }
                    game.fields = flds;
                } else if (read.split(" ")[0].equals("moves")) {
                    game.moves = Integer.parseInt(read.split(" ")[2]);
                    count++;
                } else if (read.split(" ")[0].equals("score")) {
                    game.score = Integer.parseInt(read.split(" ")[2]);
                    count++;
                }
                read = reader.readLine();
            }
            if (count != 12) {
                game.saveExists = false;
                JOptionPane.showOptionDialog(game,
                        "You don't have a saved game progress!",
                        "Error", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null, new String[]{"Ok"},
                        0);
            } else {
                game.saveExists = true;
            }
        } catch(Exception e){
            System.out.println("e = " + e);
        }
    }
}
