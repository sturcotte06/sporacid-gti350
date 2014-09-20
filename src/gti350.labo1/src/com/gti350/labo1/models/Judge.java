package com.gti350.labo1.models;

/**
 * Structure to define a fighter in a box fight.
 * 
 * @author Simon Turcotte-Langevin
 */
public class Judge {
	/** The name of the judge. */
	private final String name;
	/** The nationality of the judge. */
	private final String nationality;
	
	/**
	 * Constructor.
	 * 
	 * @param name The name of the judge.
	 */
	public Judge(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name cannot be null nor empty.");
		}
		
		this.name = name;
		this.nationality = "Unknown";
	}
	
	/**
	 * Constructor.
	 * 
	 * @param name The name of the judge.
	 * @param nationality The nationality of the judge.
	 */
	public Judge(String name, String nationality) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name cannot be null nor empty.");
		}
		
		if (nationality == null || nationality.isEmpty()) {
			throw new IllegalArgumentException("nationality cannot be null nor empty.");
		}
		
		this.name = name;
		this.nationality = "Unknown";
	}

	/**
	 * @return The nationality of the judge.
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @return The name of the judge.
	 */
	public String getName() {
		return name;
	}
}
