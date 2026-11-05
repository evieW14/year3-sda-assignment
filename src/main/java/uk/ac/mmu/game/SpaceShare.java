package uk.ac.mmu.game;

import java.util.List;

public class SpaceShare implements HitRule {
    @Override
    public boolean canMove(int destinationPosition, List<Integer> opponentPositions) {
        return true;
    }
}
