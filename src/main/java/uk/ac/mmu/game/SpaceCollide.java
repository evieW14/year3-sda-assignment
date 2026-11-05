package uk.ac.mmu.game;

public class SpaceCollide implements HitRule {
    @Override
    public boolean canMove(int destinationPosition, int opponentPosition) {
        return destinationPosition != opponentPosition;
    }
}
