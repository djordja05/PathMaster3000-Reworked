import javax.swing.*;
import java.awt.*;

public class OptionsMenu extends JPanel {

    public OptionsMenu(PathMaster3000 frame) {
        setLayout(new GridLayout(20, 1));


        JButton appereance = new JButton("Appereance");
        appereance.setVisible(true);
        add(appereance);

        JButton gridSize =  new JButton("Grid Size");
        gridSize.setVisible(true);
        add(gridSize);

        JButton back = new JButton("Back");
        back.setVisible(true);
        back.addActionListener(e -> frame.showMainMenu());
        add(back);

        setVisible(false);
    }
}
