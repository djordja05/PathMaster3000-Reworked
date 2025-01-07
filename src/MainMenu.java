import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {


    public MainMenu(PathMaster3000 frame) {
        setLayout(new GridLayout(10,1));
        initializeMainMenu(frame);
    }

    private void initializeMainMenu(PathMaster3000 frame) {
        JButton play = new JButton("Play");
        play.addActionListener(e -> frame.play());
        add(play);

        JButton load = new JButton("Load");
        load.addActionListener(e -> frame.load());
        add(load);

        JButton options = new JButton("Options");
        options.addActionListener(e -> frame.showOptionsMenu());
        add(options);

        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> System.exit(0));
        add(exit);

    }

}