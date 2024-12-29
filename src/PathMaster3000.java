import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PathMaster3000 extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);

    MainMenu mainMenu = new MainMenu(this);
    OptionsMenu optionsMenu = new OptionsMenu(this);

    public PathMaster3000() {
        setTitle("PathMaster3000");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 1000);

        cardPanel.add(mainMenu, "mainMenu");
        cardPanel.add(optionsMenu, "optionsMenu");

        setContentPane(cardPanel);

        cardLayout.show(cardPanel, "mainMenu");

        initializePathMaster3000();
        setVisible(true);
    }

    private void initializePathMaster3000() {
        mainMenu.setVisible(true);
    }

    public void showOptionsMenu() {
        cardLayout.show(cardPanel, "optionsMenu");
    }

    public void showMainMenu() {
        cardLayout.show(cardPanel, "mainMenu");
    }

}