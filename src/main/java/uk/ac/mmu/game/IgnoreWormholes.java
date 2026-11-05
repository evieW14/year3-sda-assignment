package uk.ac.mmu.game;

public class IgnoreWormholes implements WormholeRule {
    @Override
    public int teleportToIndex(int currentIndex) {
        return currentIndex;
    }
}
