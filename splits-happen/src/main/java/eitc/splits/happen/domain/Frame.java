package eitc.splits.happen.domain;

import java.util.List;

/**
 * A domain object for the frame score.
 * 
 * @author Mike
 */
public class Frame {

	private String value;
	private int score;
	private FrameType type;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public FrameType getType() {
		return type;
	}

	public void setType(FrameType type) {
		this.type = type;
	}

	public List<Integer> getRolls() {
		return type.getScores(value);
	}

	public int getLookaheadRolls() {
		return type.getLookaheadRolls();
	}

}
