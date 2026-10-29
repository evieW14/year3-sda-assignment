package uk.ac.mmu.game;

import java.util.Random;

public class RandomDice implements DiceRoller {
    private final Random random;
    private final int numberOfDice;

    public RandomDice(Random random, int numberOfDice) {
        if (numberOfDice < 1) {
            throw new IllegalArgumentException("Number of Dice must be at least 1");
        }

        this.random = random;
        this.numberOfDice = numberOfDice;
    }

    @Override
    public int roll() {
        int total = 0;
        for (int i = 0; i < numberOfDice; i++) {
            total += random.nextInt(6) + 1;
        }
        return total;
    }
}
