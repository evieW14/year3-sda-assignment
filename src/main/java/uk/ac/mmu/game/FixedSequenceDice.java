package uk.ac.mmu.game;

import java.util.List;

public class FixedSequenceDice implements DiceRoller {
    private final List<Integer> rolls;
    private int nextIndex = 0;

    public FixedSequenceDice(List<Integer> rolls) {
        this.rolls = rolls;
    }

    @Override
    public int roll() {
        if(nextIndex >= rolls.size()) {
            throw new IllegalStateException(
                    "No more rolls in the fixed sequence"
            );
        }
        int value = rolls.get(nextIndex);
        nextIndex++;
        return value;
    }
}
