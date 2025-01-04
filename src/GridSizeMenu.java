import javax.swing.*;
import java.awt.*;

public class GridSizeMenu extends JPanel {

    public GridSizeMenu(PathMaster3000 frame) {
        setLayout(new GridLayout(20, 1));

        String[] sizes = {"3 x 3", "4 x 4", "5 x 5", "6 x 6", "7 x 7", "8 x 8", "9 x 9", "10 x 10", "11 x 11", "12 x 12"};
        JComboBox<String> gridSizes = new JComboBox<>(sizes);
        gridSizes.setSelectedIndex(0);
        add(gridSizes);

        JButton setGridSize = new JButton("Set");
        setGridSize.addActionListener(e -> frame.setGridSize(Integer.parseInt(String.valueOf(gridSizes.getSelectedItem().toString().split(" ")[0]))));
        add(setGridSize);

        JButton back = new JButton("Back");
        back.addActionListener(e -> frame.showOptionsMenu());
        add(back);
    }

}
