package uk.ac.mmu.game;

public class ExactEnd implements EndRule {
    @Override
    public int destinationIndex(int currentIndex, int roll, int endIndex) {
        int destIndex = currentIndex + roll;

        if (destIndex <= endIndex) {
            return destIndex;
        } else {
            int amountPastEnd = destIndex - endIndex;
            return (endIndex - amountPastEnd);
        }
    }
}
