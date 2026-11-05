package uk.ac.mmu.game;

import java.util.List;

public interface HitRule {
    boolean canMove(int destinationPosition, List<Integer> opponentPositions);
}
