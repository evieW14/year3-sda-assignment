package uk.ac.mmu.game;

import java.util.List;

public class SpaceCollide implements HitRule {
    @Override
    public boolean canMove(int destinationPosition, List<Integer> opponentPositions) {
        return !opponentPositions.contains(destinationPosition);
    }
}