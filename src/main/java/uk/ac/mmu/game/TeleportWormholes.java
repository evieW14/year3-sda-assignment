package uk.ac.mmu.game;

import java.util.List;

public class TeleportWormholes implements WormholeRule {
    private List<Wormhole> wormholes;

    public TeleportWormholes(List<Wormhole> wormholes) {
        this.wormholes = wormholes;
    }

    @Override
    public int teleportToIndex(int currentIndex) {
        for (Wormhole wormhole : wormholes) {
            if (wormhole.firstPosition() == currentIndex) {
                return wormhole.secondPosition();
            } else if (wormhole.secondPosition() == currentIndex) {
                return wormhole.firstPosition();
            }
        };
        return currentIndex;
    };
};
