package uk.ac.mmu.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class GameApplication {

    static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);

        EndRule normalEnd = new NormalEnd();
        EndRule exactEnd = new ExactEnd();

        HitRule spaceCollide = new SpaceCollide();
        HitRule spaceShare = new SpaceShare();
        
        WormholeRule teleportWormholes = new TeleportWormholes(List.of(
                new Wormhole(4, 9),
                new Wormhole(19, 23)
        ));
        WormholeRule ignoreWormholes = new IgnoreWormholes();

        System.out.println("\n========== RANDOM DICE DEMOS ==========");

        // Random, one die: Exact End + Space Share
        runGame("Random | 1 die | Exact End | Space Share", new Game(new RandomDice(new Random(), 1), exactEnd, spaceShare, ignoreWormholes));

        // Random, two dice: Normal End + Space Collide
        runGame("Random | 2 dice | Normal End | Space Collide", new Game(new RandomDice(new Random(), 2), normalEnd, spaceCollide, ignoreWormholes));

        System.out.println("\n========== FIXED RULE DEMOS ==========");

        // These rolls make Red and Blue both attempt to land on position 13.
        // Under SpaceShare they can share the square; under SpaceCollide Blue stays at Home.
        List<Integer> collisionRolls = List.of(12, 12, 6, 6, 6);

        runGame("Fixed | Normal End | Space Share | Collision scenario", new Game(new FixedSequenceDice(collisionRolls), normalEnd, spaceShare, ignoreWormholes));

        runGame("Fixed | Normal End | Space Collide | Collision scenario", new Game(new FixedSequenceDice(collisionRolls), normalEnd, spaceCollide, ignoreWormholes));

        runGame("Fixed | Exact End | Space Share | Collision scenario", new Game(new FixedSequenceDice(collisionRolls), exactEnd, spaceShare, ignoreWormholes));

        runGame("Fixed | Exact End | Space Collide | Collision scenario", new Game(new FixedSequenceDice(collisionRolls), exactEnd, spaceCollide, ignoreWormholes));

        // The roll on turn 17 makes Red overshoot from position 24.
        // Normal End wins by overshooting; Exact End bounces back and continues.
        List<Integer> overshootRolls = List.of(6, 1, 6, 1, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

        runGame("Fixed | Normal End | Space Share | Overshoot scenario", new Game(new FixedSequenceDice(overshootRolls), normalEnd, spaceShare, ignoreWormholes));

        runGame("Fixed | Exact End | Space Share | Bounce-back scenario", new Game(new FixedSequenceDice(overshootRolls), exactEnd, spaceShare, ignoreWormholes));

        // Red lands on position 4 on turn 1 and should be teleported to position 9.
        List<Integer> wormholeRolls = List.of(3, 1, 12, 12, 4);
        runGame("Fixed | Normal End | Space Share | Teleport Wormholes (4 <-> 9, 19 <-> 23)",
                new Game(new FixedSequenceDice(wormholeRolls), normalEnd, spaceShare, teleportWormholes));
    }

    private static void runGame(String label, Game game) {
        System.out.printf("\n\n========== %s ==========%n", label);
        game.play();
    }
}
