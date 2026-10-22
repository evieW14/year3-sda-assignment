package uk.ac.mmu.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);

		RedPlayer red = new RedPlayer();
		BluePlayer blue = new BluePlayer();
		final int[] rolls = new int[]{6, 5, 5, 3, 1, 6, 6, 6, 3, 3, 2, 1};
		int turns = 0;
		int redTurns = 0;
		int blueTurns = 0;

		System.out.println("=================================");
		System.out.printf("Start: Red=%s | Blue=%s%n", red, blue);
		System.out.println("=================================");

		while (!red.isAtEnd() & !blue.isAtEnd()) {
			int roll = rolls[turns++];
			System.out.printf("%n--- Turn %d ---%n", turns);
			if (turns % 2 == 1) {
				redTurns++;
				System.out.format("Red play %d rolls %d%n", redTurns, roll);
				String start = red.toString();
				red.advance(roll);
				System.out.format("Red moves from %s to %s%n", start, red);
			} else {
				blueTurns++;
				System.out.format("Blue play %d rolls %d%n", blueTurns, roll);
				String start = blue.toString();
				blue.advance(roll);
				System.out.format("Blue moves from %s to %s%n", start, blue);
			}
		}
		if (red.isAtEnd()) {
			System.out.println("=================================");
			System.out.format("Red wins in %d moves%n", redTurns);
			System.out.println("=================================");
		} else {
			System.out.println("=================================");
			System.out.format("Blue wins in %d moves%n", blueTurns);
			System.out.println("=================================");
		}
		System.out.format("Total plays %d%n", turns);

	}

}
