package uk.ac.mmu.game;

import java.util.List;

public class TeleportWormholes implements WormholeRule {
    private final List<Wormhole> wormholes;

    public TeleportWormholes(List<Wormhole> wormholes, Board board) {
        this.wormholes = wormholes;

        validateWormholes(board);
    }

    public void validateWormholes(Board board) {
        int[] forbiddenPositions = board instanceof TwoPlayerBoard ? new int[]{1, 25} : new int[]{1, 6, 31, 36};

        int boardPositionCount = board.getRedPositions().length;

        for (Wormhole wormhole : wormholes) {
            int firstPos = wormhole.firstPosition();
            int secondPos = wormhole.secondPosition();

            if (firstPos == secondPos) {
                throw new IllegalArgumentException("Wormhole endpoints must be different");
            }

            for (int pos : new int[]{firstPos, secondPos}) {
                if (pos < 1 || pos > boardPositionCount) {
                    throw new IllegalArgumentException("Wormhole position is outside of the board");
                }

                for (int forbidden : forbiddenPositions) {
                    if (pos == forbidden) {
                        throw new IllegalArgumentException("Wormhole cannot use HOME or END");
                    }
                }
            }
        }
    }

    @Override
    public int teleportToIndex(int currentIndex) {
        for (Wormhole wormhole : wormholes) {
            if (wormhole.firstPosition() == currentIndex) {
                return wormhole.secondPosition();
            } else if (wormhole.secondPosition() == currentIndex) {
                return wormhole.firstPosition();
            }
        }
        return currentIndex;
    }

}
