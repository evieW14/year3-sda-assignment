package uk.ac.mmu.game;

public class NormalEnd implements EndRule {
    @Override
    public int destinationIndex(int currentIndex, int roll, int endIndex) {
        currentIndex += roll;

        if (currentIndex > endIndex) {
            return endIndex;
        }

        return currentIndex;
    }

}

