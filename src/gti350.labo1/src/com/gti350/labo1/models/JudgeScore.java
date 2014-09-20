package com.gti350.labo1.models;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class JudgeScore {
	/** The judge that made this registered this score. */
	private final Judge judge;
	/** The score for the first fighter. */
	private final Score scoreFighter1;
	/** The score for the second fighter. */
	private final Score scoreFighter2;

	/**
	 * Constructor.
	 * 
	 * @param judge
	 *            The judge that made this registered this score.
	 * @param scoreFighter1
	 *            The score for the first fighter.
	 * @param scoreFighter2
	 *            The score for the second fighter.
	 */
	public JudgeScore(Judge judge, Score scoreFighter1, Score scoreFighter2) {
		if (judge == null) {
			throw new IllegalArgumentException("judge cannot be null.");
		}

		if (scoreFighter1 == null) {
			throw new IllegalArgumentException("scoreFighter1 cannot be null.");
		}

		if (scoreFighter2 == null) {
			throw new IllegalArgumentException("scoreFighter2 cannot be null.");
		}

		this.judge = judge;
		this.scoreFighter1 = scoreFighter1;
		this.scoreFighter2 = scoreFighter2;
	}

	/**
	 * @return the judge
	 */
	public Judge getJudge() {
		return judge;
	}

	/**
	 * @return the scoreFighter1
	 */
	public Score getScoreFighter1() {
		return scoreFighter1;
	}

	/**
	 * @return the scoreFighter2
	 */
	public Score getScoreFighter2() {
		return scoreFighter2;
	}

}
