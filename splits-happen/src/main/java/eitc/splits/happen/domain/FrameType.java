package eitc.splits.happen.domain;

import java.util.List;
import java.util.function.Function;

import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;

/**
 * Types of bowling frames and their associated character sets.
 * 
 * @author Mike
 */
public enum FrameType {
	STRIKE("X", (x) -> Lists.newArrayList(10), 2), SPARE("/", (x) -> {
		Integer first = Integer.parseInt(x.substring(0, 1));
		return Lists.newArrayList(first, 10 - first);
	}, 1), OTHER("\\d", (x) -> {
		String firstStr = x.substring(0, 1);
		String secondStr = x.substring(1);

		int first = (firstStr.equals("-")) ? 0 : Integer.parseInt(firstStr);
		int second = (StringUtils.isEmpty(secondStr) || secondStr.equals("-")) ? 0 : Integer.parseInt(secondStr);

		return Lists.newArrayList(first, second);
	}, 0);

	private final String value;
	private final Function<String, List<Integer>> rollsFunc;
	private final int lookaheadRolls;

	private FrameType(String value, Function<String, List<Integer>> rollsFunc, int lookaheadRolls) {
		this.value = value;
		this.rollsFunc = rollsFunc;
		this.lookaheadRolls = lookaheadRolls;
	}

	public List<Integer> getScores(String value) {
		return rollsFunc.apply(value);
	}

	public int getLookaheadRolls() {
		return lookaheadRolls;
	}

	public static FrameType getType(String value) {
		if (value.contains(STRIKE.value))
			return STRIKE;
		else if (value.contains(SPARE.value))
			return SPARE;
		else
			return OTHER;
	}

}
