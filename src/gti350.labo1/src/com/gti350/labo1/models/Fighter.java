package com.gti350.labo1.models;

/**
 * Structure to define a fighter in a box fight.
 * 
 * @author Simon Turcotte-Langevin
 */
public class Fighter {
	/**
	 * Enumeration of all possible fighter colors.
	 * 
	 * @author Simon Turcotte-Langevin
	 */
	public enum FighterColor {
		Red,
		Blue
	}
	
	/** The color of the fighter. */
	private final FighterColor color;
	
	/** The name of the fighter. */
	private final String name;
	
	/**
	 * Constructor.
	 * 
	 * @param color The color of the fighter.
	 * @param name The name of the fighter.
	 */
	public Fighter(FighterColor color, String name) {
		if (color == null) {
			throw new IllegalArgumentException("color cannot be null.");
		}
		
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name cannot be null nor empty.");
		}

		this.color = color;
		this.name = name;
	}

	/**
	 * @return The name of the fighter.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The color of the fighter.
	 */
	public FighterColor getColor() {
		return color;
	}
}
