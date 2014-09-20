package com.gti350.labo1.models;

public class Penalty {
	private static final int MinimumWeight = 0;
	private static final int MaximumWeight = 10;
	
	private final int weight;
	private final PenaltyCause cause;
	
	public Penalty(int weight, PenaltyCause cause) {
		if (weight < MinimumWeight || weight > MaximumWeight) {
			throw new IllegalArgumentException(String.format(
					"weight must be between %d and %d.",
					MinimumWeight, MaximumWeight));
		}
		
		if (cause == null) {
			throw new IllegalArgumentException("cause cannot be null.");
		}
		
		this.weight = weight;
		this.cause = cause;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @return the cause
	 */
	public PenaltyCause getCause() {
		return cause;
	}
}
