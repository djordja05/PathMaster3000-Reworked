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
    ArrayList<String> config = new ArrayList<>();

    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);

    MainMenu mainMenu = new MainMenu(this);
    OptionsMenu optionsMenu = new OptionsMenu(this);
    GridSizeMenu gridSizeMenu = new GridSizeMenu(this);
    AppearancesMenu appearancesMenu = new AppearancesMenu(this);

    public PathMaster3000() {
        setTitle("PathMaster3000");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 1000);

        cardPanel.add(mainMenu, "mainMenu");
        cardPanel.add(optionsMenu, "optionsMenu");
        cardPanel.add(gridSizeMenu, "gridSizeMenu");
        cardPanel.add(appearancesMenu, "appearancesMenu");

        setContentPane(cardPanel);

        cardLayout.show(cardPanel, "mainMenu");

        initializeConfig();
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
        }
        for (int i = 0; i < config.size(); i++) {
            switch(config.get(i).split(" ")[0]) {
                case "activeFieldColor": activeFieldColor = config.get(i).split(" ")[2]; break;
                case "unusedFieldColor": unusedFieldColor = config.get(i).split(" ")[2]; break;
                case "usedFieldColor": usedFieldColor = config.get(i).split(" ")[2]; break;
                case "gridSize": gridSize = Integer.parseInt(config.get(i).split(" ")[2]); break;
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

    public void play() {
        System.out.println(activeFieldColor);
        System.out.println(unusedFieldColor);
        System.out.println(usedFieldColor);
        System.out.println(gridSize);
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