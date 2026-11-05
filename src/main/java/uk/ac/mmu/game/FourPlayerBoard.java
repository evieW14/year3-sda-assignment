package uk.ac.mmu.game;

public class FourPlayerBoard implements Board {
    private static final int END_INDEX = 35;
    private final int[] redPositions = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36};
    private final int[] bluePositions = {31, 32, 33, 34, 35, 36, 25, 26, 27, 28, 29, 30, 19, 20, 21, 22, 23, 24, 13, 14, 15, 16, 17, 18, 7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6,};
    private final int[] greenPositions = {6, 5, 4, 3, 2, 1, 12, 11, 10, 9, 8, 7, 18, 17, 16, 15, 14, 13, 24, 23, 22, 21, 20, 19, 30, 29, 28, 27, 26, 25, 36, 35, 34, 33, 32, 31};
    private final int[] yellowPositions = {36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    @Override
    public int[] getRedPositions() {
        return redPositions;
    }

    @Override
    public int[] getBluePositions() {
        return bluePositions;
    }

    public int[] getGreenPositions() {
        return greenPositions;
    }

    public int[] getYellowPositions() {
        return yellowPositions;
    }

    @Override
    public int getHomeIndex() {
        return HOME_INDEX;
    }

    @Override
    public int getEndIndex() {
        return END_INDEX;
    }
}
