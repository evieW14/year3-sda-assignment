package uk.ac.mmu.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class GameApplication {

    static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);

        // Real play, random dice
        Game randomGame = new Game(new RandomDice(new Random(), 1));
        randomGame.play();

        // Real play, 2 random dice
        Game randomDoubleGame = new Game(new RandomDice(new Random(), 2));
        randomDoubleGame.play();

        // Repeatable game, fixed dice
        Game fixedGame = new Game(new FixedSequenceDice(List.of(12, 10, 12)));
        fixedGame.play();

        // 2nd Repeatable game, fixed dice
        Game secondFixedGame = new Game(new FixedSequenceDice(List.of(5, 12, 2, 12)));
        secondFixedGame.play();
    }

}
