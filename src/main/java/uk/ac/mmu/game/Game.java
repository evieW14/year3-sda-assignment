package uk.ac.mmu.game;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final DiceRoller dice;
    private final EndRule endRule;
    private final HitRule hitRule;
    private final WormholeRule wormholeRule;
    private final List<Player> players = new ArrayList<>();
    private final Board board;
    private Player currentPlayer;
    private int totalTurns = 0;
    private int currentPlayerIndex;

    public Game(DiceRoller dice, EndRule endRule, HitRule hitRule, WormholeRule wormholeRule, Board board) {
        this.dice = dice;
        this.endRule = endRule;
        this.hitRule = hitRule;
        this.wormholeRule = wormholeRule;
        this.board = board;


        //TODO: Remove this, have a single function to iterate over players setup list
        Player redPlayer = new Player("RED", board.getRedPositions(), board.getHomeIndex(), board.getEndIndex());
        Player bluePlayer = new Player("BLUE", board.getBluePositions(), board.getHomeIndex(), board.getEndIndex());
        players.add(redPlayer);
        players.add(bluePlayer);

        //TODO: Fix this as its ugly code
        if (board instanceof FourPlayerBoard) {
            Player greenPlayer = new Player("GREEN", ((FourPlayerBoard) board).getGreenPositions(), board.getHomeIndex(), board.getEndIndex());
            Player yellowPlayer = new Player("YELLOW", ((FourPlayerBoard) board).getYellowPositions(), board.getHomeIndex(), board.getEndIndex());
            players.add(greenPlayer);
            players.add(yellowPlayer);
        }

        currentPlayerIndex = 0;
        currentPlayer = players.get(currentPlayerIndex);
    }

    public void play() {
        System.out.println("=================================");
        System.out.println("        START");
        for (Player p : players) {
            System.out.printf("%s Player = %s", p.getColour(), p);
            System.out.println();
        }
        System.out.println("=================================");

        while (!playerAtEnd()) {
            playTurn();
        }

        displayWinner();
    }

    private boolean playerAtEnd() {
        for (Player p : players) {
            if (p.isAtEnd()) {
                return true;
            }
        }
        return false;
    }

    private void displayWinner() {

        System.out.println("=================================");

        //TODO: Fix this as its ugly code
        for (Player p : players) {
            if (p.isAtEnd()) {
                currentPlayer = p;
            }
        }

        System.out.printf("%s WINS in %d moves%n", currentPlayer.getColour(), currentPlayer.getTurnCount());

        System.out.printf("Total Turns: %d%n", totalTurns);

        System.out.println("=================================");
    }

    private List<Integer> getOccupiedPositions() {
        return players.stream().filter(player -> player != currentPlayer).filter(player -> !player.isHome()).map(Player::getPosition).toList();
    }

    private void playTurn() {

        totalTurns++;
        currentPlayer.incrementTurnCount();

        int roll = dice.roll();

        System.out.printf("%n--- Turn %d ---%n", totalTurns);

        String start = currentPlayer.toString();

        List<Integer> opponentPositions = getOccupiedPositions();

        currentPlayer.advance(roll, endRule, hitRule, opponentPositions, wormholeRule);

        System.out.printf("%s rolls %d%n", currentPlayer.getColour(), roll);

        System.out.printf("%s moves from %s to %s%n", currentPlayer.getColour(), start, currentPlayer);

        swapPlayer();
    }

    private void swapPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }
}

