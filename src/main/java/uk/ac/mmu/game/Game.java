package uk.ac.mmu.game;

public class Game {

    private final Dice dice;
    private final Player redPlayer;
    private final Player bluePlayer;

    private Player currentPlayer;

    private int turnCount = 0;

    public Game() {
        Board board = new Board();

        dice = new Dice();
        redPlayer = new Player("RED", board.getRedPositions());
        bluePlayer = new Player("BLUE", board.getBluePositions());

        currentPlayer = redPlayer;
    }

    public void play() {

        System.out.println("=================================");
        System.out.printf("Start: Red=%s | Blue=%s%n", redPlayer, bluePlayer);
        System.out.println("=================================");

        while (!redPlayer.isAtEnd() && !bluePlayer.isAtEnd()) {
            playTurn();
        }

        displayWinner();
    }

    private void displayWinner() {

        System.out.println("=================================");

        if (redPlayer.isAtEnd()) {
            System.out.printf("Red wins in %d moves%n", turnCount);
        } else {
            System.out.printf("Blue wins in %d moves%n", turnCount);
        }

        System.out.println("=================================");
    }

    private void playTurn() {

        turnCount++;

        int roll = dice.roll();

        System.out.printf("%n--- Turn %d ---%n", turnCount);

        String start = currentPlayer.toString();

        currentPlayer.advance(roll);

        System.out.printf("%s rolls %d%n", currentPlayer.getColour(), roll);

        System.out.printf("%s moves from %s to %s%n", currentPlayer.getColour(), start, currentPlayer);

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

