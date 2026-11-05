package uk.ac.mmu.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class GameApplication {

    static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);

        NormalEnd normalEnd = new NormalEnd();
        ExactEnd exactEnd = new ExactEnd();
        SpaceCollide spaceCollide = new SpaceCollide();
        SpaceShare spaceShare = new SpaceShare();

        System.out.println("\n========== RANDOM DICE DEMOS ==========");

        // Random, one die: Exact End + Space Share
        runGame("Random | 1 die | Exact End | Space Share", new Game(new RandomDice(new Random(), 1), exactEnd, spaceShare));

        // Random, two dice: Normal End + Space Collide
        runGame("Random | 2 dice | Normal End | Space Collide", new Game(new RandomDice(new Random(), 2), normalEnd, spaceCollide));

        System.out.println("\n========== FIXED RULE DEMOS ==========");

        // These rolls make Red and Blue both attempt to land on position 13.
        // Under SpaceShare they can share the square; under SpaceCollide Blue stays at Home.
        List<Integer> collisionRolls = List.of(12, 12, 6, 6, 6);

        runGame("Fixed | Normal End | Space Share | Collision scenario", new Game(new FixedSequenceDice(collisionRolls), normalEnd, spaceShare));

        runGame("Fixed | Normal End | Space Collide | Collision scenario", new Game(new FixedSequenceDice(collisionRolls), normalEnd, spaceCollide));

        runGame("Fixed | Exact End | Space Share | Collision scenario", new Game(new FixedSequenceDice(collisionRolls), exactEnd, spaceShare));

        runGame("Fixed | Exact End | Space Collide | Collision scenario", new Game(new FixedSequenceDice(collisionRolls), exactEnd, spaceCollide));

        // The roll on turn 17 makes Red overshoot from position 24.
        // Normal End wins by overshooting; Exact End bounces back and continues.
        List<Integer> overshootRolls = List.of(6, 1, 6, 1, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

        runGame("Fixed | Normal End | Space Share | Overshoot scenario", new Game(new FixedSequenceDice(overshootRolls), normalEnd, spaceShare));

        runGame("Fixed | Exact End | Space Share | Bounce-back scenario", new Game(new FixedSequenceDice(overshootRolls), exactEnd, spaceShare));
    }

    private static void runGame(String label, Game game) {
        System.out.printf("\n\n========== %s ==========%n", label);
        game.play();
    }
}
