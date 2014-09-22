package com.gti350.labo1.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Structure to define a fighter in a box fight.
 * 
 * @author Simon Turcotte-Langevin
 */
public class Fighter implements Parcelable {
	/**
	 * Enumeration of all possible fighter colors.
	 * 
	 * @author Simon Turcotte-Langevin
	 */
	public enum FighterColor {
		Red, Blue
	}

	/** The color of the fighter. */
	private final FighterColor color;

	/** The name of the fighter. */
	private final String name;

	/**
	 * Constructor.
	 * 
	 * @param color
	 *            The color of the fighter.
	 * @param name
	 *            The name of the fighter.
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

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

	}
}
