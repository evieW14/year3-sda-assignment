package uk.ac.mmu.game;

import java.util.Random;

public class RandomDice implements DiceRoller{
    private final Random random;

    public RandomDice(Random random) {
        this.random = random;
    }

    public int roll() {
        return random.nextInt(6) + 1;
    }
}
