import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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
    boolean isPlayed = false;

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);

    MainMenu mainMenu = new MainMenu(this);
    OptionsMenu optionsMenu = new OptionsMenu(this);
    GridSizeMenu gridSizeMenu = new GridSizeMenu(this);
    AppearancesMenu appearancesMenu = new AppearancesMenu(this);
    Game game = new Game();
    EndGame endGame = new EndGame();


    public PathMaster3000() {
        setTitle("PathMaster3000");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeConfig();

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        game.setFocusable(true);

        InputMap inputMap = game.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = game.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "saveGame");
        actionMap.put("saveGame", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSize(700, 700);
                showMainMenu();
                game.save();
            }
        });

        cardPanel.add(mainMenu, "mainMenu");
        cardPanel.add(optionsMenu, "optionsMenu");
        cardPanel.add(gridSizeMenu, "gridSizeMenu");
        cardPanel.add(appearancesMenu, "appearancesMenu");
        cardPanel.add(game, "game");
        cardPanel.add(endGame, "endGame");

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

    public void showEndGame(Game game) {
        endGame.initializeEndGame(game);
        cardLayout.show(cardPanel, "endGame");
    }

    public void play() {
        game.initializeGame(this, isPlayed);
        isPlayed = true;
        showGame();
    }

    public void load() {
        game.load(this, isPlayed);
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