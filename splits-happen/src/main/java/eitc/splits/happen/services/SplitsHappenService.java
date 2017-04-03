package eitc.splits.happen.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import eitc.splits.happen.domain.Frame;
import eitc.splits.happen.domain.FrameType;

/**
 * A service to compute bowling score given a valid bowling score line string.
 * 
 * @author Mike
 */
@Component
public class SplitsHappenService {

	private static final String SCORING_LINE_REGEX = "(X)|(\\d\\d)|(-\\d)|(\\d-)|(\\d/)|(--)|(\\d)";

	/**
	 * Takes a scoring line and calculates the overall bowling score.
	 * 
	 * @param scoringLine
	 * @return the bowling score
	 */
	public int computeBowlingScore(String scoringLine) {
		List<Frame> frames = parseScore(scoringLine);
		computeFrameScores(frames);
		return frames.stream().mapToInt(frame -> frame.getScore()).sum();
	}

	/**
	 * Convenience method to use a regex to parse the score line and create an
	 * {@link List} of frames.
	 * 
	 * @param scoringLine
	 * @return the list of frames
	 */
	private List<Frame> parseScore(String scoringLine) {
		List<Frame> frames = new ArrayList<>();
		Pattern pattern = Pattern.compile(SCORING_LINE_REGEX);
		Matcher matcher = pattern.matcher(scoringLine);

		// Retrieve each match and compute the rolls for the frame.
		while (matcher.find()) {
			Frame frame = new Frame();
			frame.setValue(matcher.group());
			frame.setType(FrameType.getType(frame.getValue()));
			frames.add(frame);
		}

		return frames;
	}

	/**
	 * Given a {@link List} of frames will compute their scores for the value
	 * string.
	 * 
	 * @param frames
	 */
	private void computeFrameScores(List<Frame> frames) {
		for (int i = 0; i < frames.size() && i < 10; i++) {
			Frame frame = frames.get(i);
			int score = frame.getRolls().stream().mapToInt(v -> v).sum();
			int peekRolls = frame.getLookaheadRolls();
			int peekIndex = i + 1;

			while (peekRolls > 0) {
				List<Integer> rolls = frames.get(peekIndex).getRolls();
				score += rolls.stream().mapToInt(v -> v).limit(peekRolls).sum();
				peekIndex++;
				peekRolls -= rolls.size();
			}

			frame.setScore(score);
		}
	}

}
