import javax.swing.*;
import java.awt.*;

public class EndGame extends JPanel {

    protected double score;
    protected int moves;

    public EndGame() {
        setLayout(new GridLayout(10, 1));
    }

    protected void initializeEndGame(Game game) {
        this.score = game.score;
        this.moves = game.moves;

        JLabel scoreLabel = new JLabel("Score: " + this.score);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(scoreLabel);

        JLabel movesLabel = new JLabel("Moves: " + this.moves);
        movesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(movesLabel);

        JButton endGameButton = new JButton("End Game");
        endGameButton.addActionListener(e -> System.exit(99));
        add(endGameButton);
    }


    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }
}
