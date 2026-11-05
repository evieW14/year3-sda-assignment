package uk.ac.mmu.game;


public class Game {

    private final DiceRoller dice;
    private final Player redPlayer;
    private final Player bluePlayer;
    private final EndRule endRule;
    private final HitRule hitRule;
    private final WormholeRule wormholeRule;
    private Player currentPlayer;
    private int totalTurns = 0;

    public Game(DiceRoller dice, EndRule endRule, HitRule hitRule, WormholeRule wormholeRule) {
        this.dice = dice;
        this.endRule = endRule;
        this.hitRule = hitRule;
        this.wormholeRule = wormholeRule;
        Board board = new Board();

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
            System.out.printf("Red WINS in %d moves%n", redPlayer.getTurnCount());
        } else {
            System.out.printf("Blue WINS in %d moves%n", bluePlayer.getTurnCount());
        }

        System.out.printf("Total Turns: %d%n", totalTurns);

        System.out.println("=================================");
    }

    private void playTurn() {

        totalTurns++;
        currentPlayer.incrementTurnCount();

        int roll = dice.roll();

        System.out.printf("%n--- Turn %d ---%n", totalTurns);

        String start = currentPlayer.toString();

        Player opponent = currentPlayer == redPlayer ? bluePlayer : redPlayer;
        // A player at Home does not occupy a board square. In particular, the
        // blue player's Home coordinate (25) is also Red's finishing square.
        int opponentPosition = opponent.isHome() ? -1 : opponent.getPosition();

        currentPlayer.advance(roll, endRule, hitRule, opponentPosition, wormholeRule);

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

