package com.gti350.labo1.models;

import java.util.Arrays;
import java.util.Collection;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class Score {
	/** The minimum effective score for a round. */
	private static final int MinimumEffectiveScore = 0;
	/** The minimum initial score for a round. */
	private static final int MinimumInitialScore = 9;
	/** The maximum score for a round. */
	private static final int MaximumScore = 10;

	/** The initial value for the score. */
	private final int initialScore;
	/** A collection of penalties. */
	private final Collection<Penalty> penalties;

	/**
	 * Constructor.
	 * 
	 * @param initialScore
	 * @param penalties
	 */
	public Score(int initialScore, Penalty... penalties) {
		if (initialScore < MinimumInitialScore || initialScore > MaximumScore) {
			throw new IllegalArgumentException(String.format(
					"initialScore must be between %d and %d.",
					MinimumInitialScore, MaximumScore));
		}

		this.initialScore = initialScore;
		this.penalties = Arrays.asList(penalties);
	}

	/**
	 * Return the effective score, which is the initial score with every
	 * penalties applied.
	 * 
	 * @return The effective score
	 */
	public int getEffectiveScore() {
		int score = initialScore;
		for (Penalty penalty : penalties) {
			score -= penalty.getWeight();
		}

		return score > MinimumEffectiveScore ? score : MinimumEffectiveScore;
	}

	/**
	 * @return the initialScore
	 */
	public int getInitialScore() {
		return initialScore;
	}

	/**
	 * @return the penalties
	 */
	public Collection<Penalty> getPenalties() {
		return penalties;
	}
}
