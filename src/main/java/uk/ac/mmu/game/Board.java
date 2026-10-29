package uk.ac.mmu.game;

public class Board {
    private final int[] redPositions = {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25
    };

    private final int[] bluePositions = {
            25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1
    };

    public int[] getRedPositions() {
        return redPositions;
    }

    public int[] getBluePositions() {
        return bluePositions;
    }
}
