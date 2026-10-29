package uk.ac.mmu.game;

public class Game {

    private final Dice dice;
    private final Player redPlayer;
    private final Player bluePlayer;

    private Player currentPlayer;

    public Game() {
        Board board = new Board();

        dice = new Dice();
        redPlayer = new Player("red", board.getRedPositions());
        bluePlayer = new Player("blue", board.getBluePositions());

        currentPlayer = redPlayer;
    }

    public void playTurn() {
        int roll = dice.roll();

        currentPlayer.advance(roll);

        System.out.println(currentPlayer.getColour() + " rolled " + roll + " -> " + currentPlayer);

        swapPlayer();
    }

    private void swapPlayer() {

        if (currentPlayer == redPlayer) {
            currentPlayer = bluePlayer;
        } else {
            currentPlayer = redPlayer;
        }

    }
}
