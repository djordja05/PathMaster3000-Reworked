import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class PathMaster3000 extends JFrame {

    int gridSize;
    String activeFieldColor;
    String unusedFieldColor;
    String usedFieldColor;
    String startFieldColor;
    String endFieldColor;
    ArrayList<String> config = new ArrayList<>();

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);

    MainMenu mainMenu = new MainMenu(this);
    OptionsMenu optionsMenu = new OptionsMenu(this);
    GridSizeMenu gridSizeMenu = new GridSizeMenu(this);
    AppearancesMenu appearancesMenu = new AppearancesMenu(this);
    Game game = new Game();


    public PathMaster3000() {
        setTitle("PathMaster3000");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 1000);
        initializeConfig();

        cardPanel.add(mainMenu, "mainMenu");
        cardPanel.add(optionsMenu, "optionsMenu");
        cardPanel.add(gridSizeMenu, "gridSizeMenu");
        cardPanel.add(appearancesMenu, "appearancesMenu");
        cardPanel.add(game, "game");

        setContentPane(cardPanel);

        cardLayout.show(cardPanel, "mainMenu");

        setVisible(true);
    }

    private void initializeConfig() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("config.txt"));
            config = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                config.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception exc) {
            System.out.println("exc = " + exc);
        }
        for (String s : config) {
            switch (s.split(" ")[0]) {
                case "activeFieldColor":
                    activeFieldColor = s.split(" ")[2];
                    break;
                case "unusedFieldColor":
                    unusedFieldColor = s.split(" ")[2];
                    break;
                case "usedFieldColor":
                    usedFieldColor = s.split(" ")[2];
                    break;
                case "gridSize":
                    gridSize = Integer.parseInt(s.split(" ")[2]);
                    break;
                case "startFieldColor":
                    startFieldColor = s.split(" ")[2];
                    break;
                case "endFieldColor":
                    endFieldColor = s.split(" ")[2];
                    break;
            }
        }
    }

    public void showOptionsMenu() {
        cardLayout.show(cardPanel, "optionsMenu");
    }

    public void showMainMenu() {
        cardLayout.show(cardPanel, "mainMenu");
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

    public void play() {
        System.out.println(activeFieldColor);
        System.out.println(unusedFieldColor);
        System.out.println(usedFieldColor);
        System.out.println(gridSize);
        game.initializeGame(this);
        showGame();
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public void setActiveFieldColor(String activeFieldColor) {
        this.activeFieldColor = activeFieldColor;
    }

    public void setUnusedFieldColor(String unusedFieldColor) {
        this.unusedFieldColor = unusedFieldColor;
    }

    public void setUsedFieldColor(String usedFieldColor) {
        this.usedFieldColor = usedFieldColor;
    }
}