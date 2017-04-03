package eitc.splits.happen.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the {@link SplitsHappenService}.
 * 
 * @author Mike
 */
public class SplitsHappenServiceTest {

	private SplitsHappenService serviceUnderTest;

	@Before
	public void init() {
		serviceUnderTest = new SplitsHappenService();
	}

	/***************************
	 * Tests from README start.
	 ***************************/
	@Test
	public void computeBowlingScore_perfectScore() {
		// Setup
		String scoringLine = "XXXXXXXXXXXX";

		// Test
		int score = serviceUnderTest.computeBowlingScore(scoringLine);

		// Verify
		Assert.assertEquals(300, score);
	}

	@Test
	public void computeBowlingScore_misses() {
		// Setup
		String scoringLine = "9-9-9-9-9-9-9-9-9-9-";

		// Test
		int score = serviceUnderTest.computeBowlingScore(scoringLine);

		// Verify
		Assert.assertEquals(90, score);
	}

	@Test
	public void computeBowlingScore_spares() {
		// Setup
		String scoringLine = "5/5/5/5/5/5/5/5/5/5/5";

		// Test
		int score = serviceUnderTest.computeBowlingScore(scoringLine);

		// Verify
		Assert.assertEquals(150, score);
	}

	@Test
	public void computeBowlingScore_various() {
		// Setup
		String scoringLine = "X7/9-X-88/-6XXX81";

		// Test
		int score = serviceUnderTest.computeBowlingScore(scoringLine);

		// Verify
		Assert.assertEquals(167, score);
	}

	/***************************
	 * Tests from README end.
	 ***************************/

	@Test
	public void computeBowlingScore_edgeCase1() {
		// Setup
		String scoringLine = "X7/9-X-88/-6XX8/1";

		// Test
		int score = serviceUnderTest.computeBowlingScore(scoringLine);

		// Verify
		Assert.assertEquals(149, score);
	}
	
	
	@Test
	public void computeBowlingScore_edgeCase2() {
		// Setup
		String scoringLine = "X7/9-X-88/-6XX81";

		// Test
		int score = serviceUnderTest.computeBowlingScore(scoringLine);

		// Verify
		Assert.assertEquals(146, score);
	}

}
