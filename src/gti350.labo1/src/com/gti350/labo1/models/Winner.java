package com.gti350.labo1.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Laurianne Michaud, Alexandre Billot, Simon Turcotte-Langevin
 * @version 1.0
 */
public class Winner implements Parcelable {

	private final Fighter winner;
	private final WinMethod winMethod;

	/**
	 * Constructor.
	 * 
	 * @param winner
	 * @param winMethod
	 */
	public Winner(Fighter winner, WinMethod winMethod) {
		if (winner == null) {
			throw new IllegalArgumentException("winner cannot be null.");
		}

		if (winMethod == null) {
			throw new IllegalArgumentException("winMethod cannot be null.");
		}

		this.winner = winner;
		this.winMethod = winMethod;
	}

	/**
	 * Parcelable required constructor.
	 * 
	 * @param src
	 *            The parcel that describes this winner.
	 */
	public Winner(Parcel src) {
		if (src == null) {
			throw new IllegalArgumentException("src cannot be null.");
		}

		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		this.winner = src.readParcelable(classLoader);
		this.winMethod = WinMethod.valueOf(src.readString());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.winner, flags);
		dest.writeString(this.winMethod.name());
	}
}
