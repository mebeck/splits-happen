package eitc.splits.happen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import eitc.splits.happen.services.SplitsHappenService;

/**
 * Main entry class for the splits-happen bowling simulation.
 * 
 * @author Mike
 */
@SpringBootApplication
public class SplitsHappenApplication implements CommandLineRunner {

	@Autowired
	private SplitsHappenService splitsHappenService;

	public static void main(String[] args) {
		SpringApplication.run(SplitsHappenApplication.class, args);
	}

	public void run(String... args) throws Exception {
		if (args == null || args.length == 0) {
			System.out.println("Please supply the scoring line for the bowling simulation: "
					+ " java -jar splits-happen.jar <$input>");
			System.exit(-1);
		}

		int score = splitsHappenService.computeBowlingScore(args[0]);
		System.out.println("Computed final bowling score: " + score);
	}

}
