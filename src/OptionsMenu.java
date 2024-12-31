import javax.swing.*;
import java.awt.*;

public class OptionsMenu extends JPanel {

    public OptionsMenu(PathMaster3000 frame) {
        setLayout(new GridLayout(20, 1));

        JButton appearance = new JButton("Appereance");
        appearance.addActionListener(e -> frame.showAppearancesMenu());
        add(appearance);

        JButton gridSize =  new JButton("Grid Size");
        gridSize.addActionListener(e -> frame.showGridSizeMenu());
        add(gridSize);

        JButton back = new JButton("Back");
        back.addActionListener(e -> frame.showMainMenu());
        add(back);

        setVisible(false);
    }
}
