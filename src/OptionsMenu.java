import javax.swing.*;
import java.awt.*;

public class OptionsMenu extends JPanel {

    JToggleButton randomizeStartEnd;

    public OptionsMenu(PathMaster3000 frame) {
        setLayout(new GridLayout(10, 1));

        JButton appearance = new JButton("Appereance");
        appearance.addActionListener(e -> frame.showAppearancesMenu());
        add(appearance);

        JButton gridSize =  new JButton("Grid Size");
        gridSize.addActionListener(e -> frame.showGridSizeMenu());
        add(gridSize);

        JButton startEndPosSetter = new JButton("Start End Position Setter");
        startEndPosSetter.addActionListener(e -> frame.showStartEndPositionSetter());

        randomizeStartEnd = new JToggleButton("Randomize Start End");
        randomizeStartEnd.setSelected(false);
        startEndPosSetter.setEnabled(false);
        randomizeStartEnd.addActionListener(e -> {
            startEndPosSetter.setEnabled(randomizeStartEnd.isSelected());
        });
        add(randomizeStartEnd);
        add(startEndPosSetter);


        JButton back = new JButton("Back");
        back.addActionListener(e -> frame.showMainMenu());
        add(back);

        setVisible(false);
    }

}
