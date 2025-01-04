import javax.swing.*;
import java.awt.*;

public class AppearancesMenu extends JPanel {

    public AppearancesMenu(PathMaster3000 frame) {
        setLayout(new GridLayout(20, 1));

        JLabel setActiveFieldColorLabel = new JLabel("Set Active Field Color", JLabel.CENTER);
        setActiveFieldColorLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        String[] colors = {"Red", "Green", "Blue", "Yellow", "Orange", "Magenta", "Cyan", "White"};
        JComboBox<String> activeFieldColor = new JComboBox<>(colors);
        JButton setActiveFieldColor = new JButton("Set");
        setActiveFieldColor.addActionListener(e -> frame.setActiveFieldColor(activeFieldColor.getSelectedItem().toString()));
        add(setActiveFieldColorLabel);
        add(activeFieldColor);
        add(setActiveFieldColor);

        JLabel setUnusedFieldColorLabel = new JLabel("Set Unused Field Color", JLabel.CENTER);
        setUnusedFieldColorLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        JComboBox<String> unusedFieldColor = new JComboBox<>(colors);
        JButton setUnusedFieldColor = new JButton("Set");
        setUnusedFieldColor.addActionListener(e -> frame.setUnusedFieldColor(unusedFieldColor.getSelectedItem().toString()));
        add(setUnusedFieldColorLabel);
        add(unusedFieldColor);
        add(setUnusedFieldColor);

        JLabel setUsedFieldColorLabel = new JLabel("Set Used Field Color", JLabel.CENTER);
        setUsedFieldColorLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        JComboBox<String> usedFieldColor = new JComboBox<>(colors);
        JButton setUsedFieldColor = new JButton("Set");
        setUsedFieldColor.addActionListener(e -> frame.setUsedFieldColor(usedFieldColor.getSelectedItem().toString()));
        add(setUsedFieldColorLabel);
        add(usedFieldColor);
        add(setUsedFieldColor);

        JButton back = new JButton("Back");
        back.addActionListener(e -> frame.showOptionsMenu());
        add(back);

    }
}
