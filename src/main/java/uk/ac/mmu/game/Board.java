package uk.ac.mmu.game;

import java.util.List;

public interface Board {
    int HOME_INDEX = 0;
    int END_INDEX = 0;
    int[] redPositions = new int[0];
    int[] bluePositions = new int[0];

    List<PlayerSetup> getPlayerSetups();

    int[] getRedPositions();

    int[] getBluePositions();

    int getHomeIndex();

    int getEndIndex();
}
