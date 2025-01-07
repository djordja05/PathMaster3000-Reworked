import javax.swing.*;
import java.awt.*;

public class EndGame extends JPanel {

    protected double score;
    protected int moves;
    protected int scoreSum;
    protected String elapsedTime;

    public EndGame() {
        setLayout(new GridLayout(10, 1));
    }

    protected void initializeEndGame(Game game, PathMaster3000 frame) {
        moves = game.moves;
        score = game.score / moves;
        scoreSum = (int)game.score;
        elapsedTime = (game.gameEndTime.getHour() - game.gameStartTime.getHour()) + "h "
                    + (game.gameEndTime.getMinute() - game.gameStartTime.getMinute()) + "m "
                    + (game.gameEndTime.getSecond() - game.gameStartTime.getSecond() + "s ");

        frame.setSize(700, 700);

        JLabel scoreLabel = new JLabel("Score: " + round(this.score));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(scoreLabel);

        JLabel movesLabel = new JLabel("Moves: " + this.moves);
        movesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(movesLabel);

        JLabel scoreSumLabel = new JLabel("Sum of selected fields: " + this.scoreSum);
        scoreSumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(scoreSumLabel);

        JLabel elapsedTimeLabel = new JLabel("Elapsed time: " + elapsedTime);
        elapsedTimeLabel.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        elapsedTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(elapsedTimeLabel);

        JButton endGameButton = new JButton("End Game");
        endGameButton.addActionListener(e -> System.exit(99));
        add(endGameButton);
    }

    private double round(double value) {
        return Math.round(value*100.0)/100.0;
    }
    }
