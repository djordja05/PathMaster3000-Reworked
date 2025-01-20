import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;

public class PathMaster3000 extends JFrame {

    int gridSize;
    Color activeFieldColor;
    Color unusedFieldColor;
    Color usedFieldColor;
    Color startFieldColor;
    Color endFieldColor;
    ArrayList<String> config = new ArrayList<>();
    boolean isPlayed = false;

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);

    MainMenu mainMenu = new MainMenu(this);
    OptionsMenu optionsMenu = new OptionsMenu(this);
    GridSizeMenu gridSizeMenu = new GridSizeMenu(this);
    AppearancesMenu appearancesMenu = new AppearancesMenu(this);
    Game game = new Game();
    StartEndPositionSetter startEndPositionSetter = new StartEndPositionSetter();


    public PathMaster3000() {
        setTitle("PathMaster3000");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeConfig();

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        game.setFocusable(true);

        cardPanel.add(mainMenu, "mainMenu");
        cardPanel.add(optionsMenu, "optionsMenu");
        cardPanel.add(gridSizeMenu, "gridSizeMenu");
        cardPanel.add(appearancesMenu, "appearancesMenu");
        cardPanel.add(game, "game");
        cardPanel.add(startEndPositionSetter, "startEndPositionSetter");

        setContentPane(cardPanel);

        cardLayout.show(cardPanel, "mainMenu");

        setVisible(true);
    }

    private void initializeConfig() {
        try {
            if (!new File("config.txt").exists()) {
                createConfig();
            }
            BufferedReader reader = new BufferedReader(new FileReader("config.txt"));
            config = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                config.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("initializeConfig() = " + e.getMessage());
        }
        for (String s : config) {
            switch (s.split(" ")[0]) {
                case "activeFieldColor":
                    activeFieldColor = game.getColor(s.split(" ")[2]);
                    break;
                case "unusedFieldColor":
                    unusedFieldColor = game.getColor(s.split(" ")[2]);
                    break;
                case "usedFieldColor":
                    usedFieldColor = game.getColor(s.split(" ")[2]);
                    break;
                case "gridSize":
                    gridSize = Integer.parseInt(s.split(" ")[2]);
                    break;
                case "startFieldColor":
                    startFieldColor = game.getColor(s.split(" ")[2]);
                    break;
                case "endFieldColor":
                    endFieldColor = game.getColor(s.split(" ")[2]);
                    break;
            }
        }
    }

    public void showOptionsMenu() {
        cardLayout.show(cardPanel, "optionsMenu");
    }

    public void showMainMenu() {
        cardLayout.show(cardPanel, "mainMenu");
        this.setSize(500, 500);
    }

    public void showGridSizeMenu() {
        cardLayout.show(cardPanel, "gridSizeMenu");
    }

    public void showAppearancesMenu() {
        cardLayout.show(cardPanel, "appearancesMenu");
    }

    public void showGame() {
        cardLayout.show(cardPanel, "game");
    }

    public void showStartEndPositionSetter() {
        cardLayout.show(cardPanel, "startEndPositionSetter");
        startEndPositionSetter.initializeStartEndPosSetter(this);
    }

    public void play(boolean isPosSet) {
        game.initializeGame(this, isPosSet);
        showGame();
    }

    public void load() {
        game.saveLoadManager.load(this, game);
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public void setActiveFieldColor(Color activeFieldColor) {
        this.activeFieldColor = activeFieldColor;
    }

    public void setUnusedFieldColor(Color unusedFieldColor) {
        this.unusedFieldColor = unusedFieldColor;
    }

    public void setUsedFieldColor(Color usedFieldColor) {
        this.usedFieldColor = usedFieldColor;
    }

    private void createConfig() {
        try {
            new File("config.txt").createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"));
            writer.write("Default values\n"
                    + "--APPEARANCE--\n"
                    + "activeFieldColor = Yellow\n"
                    + "usedFieldColor = Red\n"
                    + "unusedFieldColor = Blue\n"
                    + "startFieldColor = Green\n"
                    + "endFieldColor = Magenta\n"
                    + "--GRID SIZE--\n"
                    + "gridSize = 5");
            writer.close();
        } catch (Exception e) {
            System.out.println("createConfig() = " + e.getMessage());
        }
    }

}