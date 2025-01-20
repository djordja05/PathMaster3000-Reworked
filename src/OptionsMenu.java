import javax.swing.*;
import java.awt.*;

public class OptionsMenu extends JPanel {

    JToggleButton randomizeStartEnd;

    public OptionsMenu(PathMaster3000 frame) {
        setLayout(new GridLayout(4, 1));

        JButton appearance = new JButton("Appearance");
        appearance.addActionListener(e -> frame.showAppearancesMenu());
        add(appearance);

        JButton gridSize =  new JButton("Grid Size");
        gridSize.addActionListener(e -> frame.showGridSizeMenu());
        add(gridSize);

        JButton startEndPosSetter = new JButton("Start End Position Setter");
        startEndPosSetter.addActionListener(e -> frame.showStartEndPositionSetter());
        add(startEndPosSetter);


        JButton back = new JButton("Back");
        back.addActionListener(e -> frame.showMainMenu());
        add(back);

        setVisible(false);
    }

}
