import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AppearancesMenu extends JPanel {

    public AppearancesMenu(PathMaster3000 frame) {
        setLayout(new GridLayout(20, 1));

        String[] colors = {"Red", "Green", "Blue", "Yellow", "Orange", "Magenta", "Cyan", "White"};
        JComboBox<String> activeFieldColor = new JComboBox(colors);
        JButton setActiveFieldColor = new JButton("Set");
        setActiveFieldColor.addActionListener(e -> frame.setActiveFieldColor(activeFieldColor.getSelectedItem().toString()));
        add(activeFieldColor);
        add(setActiveFieldColor);

        JComboBox<String> unusedFieldColor = new JComboBox(colors);
        JButton setUnusedFieldColor = new JButton("Set");
        setUnusedFieldColor.addActionListener(e -> frame.setUnusedFieldColor(unusedFieldColor.getSelectedItem().toString()));
        add(unusedFieldColor);
        add(setUnusedFieldColor);

        JComboBox<String> usedFieldColor = new JComboBox(colors);
        JButton setUsedFieldColor = new JButton("Set");
        setUsedFieldColor.addActionListener(e -> frame.setUsedFieldColor(usedFieldColor.getSelectedItem().toString()));
        add(usedFieldColor);
        add(setUsedFieldColor);

        JButton back = new JButton("Back");
        back.addActionListener(e -> frame.showOptionsMenu());
        add(back);

    }
}
