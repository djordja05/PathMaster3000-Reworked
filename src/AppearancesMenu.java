import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppearancesMenu extends JPanel {

    public AppearancesMenu(PathMaster3000 frame) {
        setLayout(new GridLayout(4, 1));

        JButton setActiveFieldcolor = new JButton("Set Active Field Color");
        setActiveFieldcolor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setActiveFieldColor(JColorChooser.showDialog(frame, "Choose Color", frame.activeFieldColor));
            }
        });

        JButton setUnusedFieldColor = new JButton("Set Unused Field Color");
        setUnusedFieldColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setUnusedFieldColor(JColorChooser.showDialog(frame, "Choose Color", frame.unusedFieldColor));
            }
        });

        JButton setUsedFieldColor = new JButton("Set Used Field Color");
        setUsedFieldColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setUsedFieldColor(JColorChooser.showDialog(frame, "Choose Color", frame.usedFieldColor));
            }
        });
        add(setActiveFieldcolor);
        add(setUnusedFieldColor);
        add(setUsedFieldColor);

        JButton back = new JButton("Back");
        back.addActionListener(e -> frame.showOptionsMenu());
        add(back);

    }
}
