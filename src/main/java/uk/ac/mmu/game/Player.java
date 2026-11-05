package uk.ac.mmu.game;

public class Player {
    private final String colour;
    private final int[] positions;
    private final int HOME_INDEX;
    private final int END_INDEX;
    private int turnCount = 0;
    private int currentIndex;

    public Player(String colour, int[] positions, int HOME_INDEX, int END_INDEX) {
        this.colour = colour;
        this.positions = positions;
        this.HOME_INDEX = HOME_INDEX;
        this.END_INDEX = END_INDEX;

        currentIndex = HOME_INDEX;
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

    public void advance(int spaces, EndRule endRule, HitRule hitRule, int opponentPosition, WormholeRule wormholeRule) {
        int destinationIndex = endRule.destinationIndex(currentIndex, spaces, END_INDEX);

        // WormholeRule and HitRule use BoardPositions as these are shared
        // Indexes are different for players as they have their own sequence of the board.
        int destinationPosition = wormholeRule.teleportToIndex(positions[destinationIndex]);

        if (hitRule.canMove(destinationPosition, opponentPosition)) {
            // currentIndex is an index so need to convert destinationPosition to its index
            currentIndex = getIndexOfPosition(destinationPosition);
        }
    }

    private int getIndexOfPosition(int boardPosition) {
        for (int index = 0; index < positions.length; index++) {
            if (positions[index] == boardPosition) {
                return index;
            }
        }
        throw new IllegalArgumentException("Position is not on this player's track");
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
