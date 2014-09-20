package com.gti350.labo1.models;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @author Simon Turcotte-Langevin
 *
 */
public class Fight {
	/** */
	private static final int MaxRound = 12;
	/** */
	private final Collection<Round> rounds;
	/** */
	private final Judge judge1;
	/** */
	private final Judge judge2;
	/** */
	private final Judge judge3;
	/** */
	private final Fighter fighter1;
	/** */
	private final Fighter fighter2;
	
	/**
	 * 
	 * @param fighter1
	 * @param fighter2
	 * @param judge1
	 * @param judge2
	 * @param judge3
	 */
	public Fight(Fighter fighter1, Fighter fighter2, Judge judge1, Judge judge2, Judge judge3) {
		if (fighter1 == null) {
			throw new IllegalArgumentException("fighter1 cannot be null.");
		}
		
		if (fighter2 == null) {
			throw new IllegalArgumentException("fighter2 cannot be null.");
		}
		
		if (fighter1.getColor() == fighter2.getColor()) {
			throw new IllegalArgumentException("fighter1 and fighter2 cannot have the same color.");
		}
		
		if (judge1 == null) {
			throw new IllegalArgumentException("judge1 cannot be null.");
		}
		
		if (judge2 == null) {
			throw new IllegalArgumentException("judge2 cannot be null.");
		}
		
		if (judge3 == null) {
			throw new IllegalArgumentException("judge3 cannot be null.");
		}
		
		this.rounds = new ArrayList<>(MaxRound);
		this.judge1 = judge1;
		this.judge2 = judge2;
		this.judge3 = judge3;
		this.fighter1 = fighter1;
		this.fighter2 = fighter2;
	}
	
	/**
	 * 
	 * @param judgeScore1
	 * @param judgeScore2
	 * @param judgeScore3
	 */
	public void registerRound(JudgeScore judgeScore1, JudgeScore judgeScore2, JudgeScore judgeScore3) {
		if (this.rounds.size() >= MaxRound) {
			throw new IllegalStateException("Cannot register a new round for this fight.");
		}
		
		if (judgeScore1 == null) {
			throw new IllegalArgumentException("judgeScore1 cannot be null.");
		}
		
		if (judgeScore2 == null) {
			throw new IllegalArgumentException("judgeScore2 cannot be null.");
		}
		
		if (judgeScore3 == null) {
			throw new IllegalArgumentException("judgeScore3 cannot be null.");
		}
		
		Round newRound = new Round();
		this.rounds.add(newRound);
	}
}
