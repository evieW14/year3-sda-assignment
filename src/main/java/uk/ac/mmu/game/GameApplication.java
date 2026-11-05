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

        WormholeRule teleportWormholes = new TeleportWormholes(List.of(new Wormhole(4, 9), new Wormhole(19, 23)));
        WormholeRule ignoreWormholes = new IgnoreWormholes();

        Board twoPlayerBoard = new TwoPlayerBoard();
        Board fourPlayerBoard = new FourPlayerBoard();

        System.out.println("\n========== RANDOM DICE DEMOS ==========");

        runGame("Random | 1 die | 6x6, 4 players | Exact End | Space Share | Ignore Wormholes", new Game(new RandomDice(new Random(), 1), exactEnd, spaceShare, ignoreWormholes, fourPlayerBoard));

        runGame("Random | 2 dice | 5x5, 2 players | Normal End | Space Collide | Ignore Wormholes", new Game(new RandomDice(new Random(), 2), normalEnd, spaceCollide, ignoreWormholes, twoPlayerBoard));

        System.out.println("\n========== FIXED RULE DEMOS (5x5, 2 players) ==========");

        // Both players attempt to land on position 13 on their first turn each.
        List<Integer> collisionRolls = List.of(12, 12, 6, 6, 6);

        runGame("Normal End | Space Share | Collision scenario", new Game(new FixedSequenceDice(collisionRolls), normalEnd, spaceShare, ignoreWormholes, twoPlayerBoard));
        runGame("Normal End | Space Collide | Collision scenario", new Game(new FixedSequenceDice(collisionRolls), normalEnd, spaceCollide, ignoreWormholes, twoPlayerBoard));
        runGame("Exact End | Space Share | Collision scenario", new Game(new FixedSequenceDice(collisionRolls), exactEnd, spaceShare, ignoreWormholes, twoPlayerBoard));
        runGame("Exact End | Space Collide | Collision scenario", new Game(new FixedSequenceDice(collisionRolls), exactEnd, spaceCollide, ignoreWormholes, twoPlayerBoard));

        // Red's roll on turn 17 overshoots from position 24.
        List<Integer> overshootRolls = List.of(6, 1, 6, 1, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

        runGame("Normal End | Overshoot scenario", new Game(new FixedSequenceDice(overshootRolls), normalEnd, spaceShare, ignoreWormholes, twoPlayerBoard));
        runGame("Exact End | Bounce-back scenario", new Game(new FixedSequenceDice(overshootRolls), exactEnd, spaceShare, ignoreWormholes, twoPlayerBoard));

        // Red lands on position 4 and should be teleported to position 9.
        List<Integer> wormholeRolls = List.of(3, 1, 12, 12, 4);
        runGame("Normal End | Space Share | Teleport Wormholes (4 <-> 9, 19 <-> 23)", new Game(new FixedSequenceDice(wormholeRolls), normalEnd, spaceShare, teleportWormholes, twoPlayerBoard));
    }

    private static void runGame(String label, Game game) {
        System.out.printf("\n\n========== %s ==========%n", label);
        game.play();
    }
}
