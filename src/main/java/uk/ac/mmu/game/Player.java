package uk.ac.mmu.game;

public class Player {
    private static final int HOME_INDEX = 0;
    private static final int END_INDEX = 24;

    private final String colour;
    private final int[] positions;
    private int turnCount = 0;

    private int currentIndex = HOME_INDEX;

    public Player(String colour, int[] positions) {
        this.colour = colour;
        this.positions = positions;
    }

    public boolean isHome() {
        return currentIndex == HOME_INDEX;
    }

    public boolean isAtEnd() {
        return currentIndex == END_INDEX;
    }

    public int getPosition() {
        return positions[currentIndex];
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void incrementTurnCount() {
        turnCount++;
    }

    public void advance(int spaces) {
        currentIndex += spaces;

        if (currentIndex > END_INDEX) {
            currentIndex = END_INDEX;
        }
    }

    public String getColour() {
        return colour;
    }

    @Override
    public String toString() {
        if (isHome()) return "HOME";
        if (isAtEnd()) return "END";
        else return String.format("Position %d", getPosition());

    }
}
