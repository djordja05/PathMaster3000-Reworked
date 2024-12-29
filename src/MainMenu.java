import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Path;

public class MainMenu extends JPanel {


    public MainMenu(PathMaster3000 frame) {
        setLayout(new GridLayout(20,1));
        initializeMainMenu(frame);
    }

    private void initializeMainMenu(PathMaster3000 frame) {
        JButton play = new JButton("Play");
        play.setVisible(true);
        add(play);

        JButton save = new JButton("Save");
        save.setVisible(true);
        add(save);

        JButton load = new JButton("Load");
        load.setVisible(true);
        add(load);

        JButton options = new JButton("Options");
        options.setVisible(true);
        options.addActionListener(e -> frame.showOptionsMenu());
        add(options);

        JButton exit = new JButton("Exit");
        exit.setVisible(true);
        exit.addActionListener(e -> {
            System.exit(0);
        });
        add(exit);

    }

}