package uk.ac.mmu.game;

public interface HitRule {
    boolean canMove(int destinationPosition, int opponentPosition);
}
