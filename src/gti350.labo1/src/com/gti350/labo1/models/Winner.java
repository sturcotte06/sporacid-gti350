package com.gti350.labo1.models;

import com.gti350.labo1.models.utils.ParcelableHelper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Laurianne Michaud, Alexandre Billot, Simon Turcotte-Langevin
 * @version 1.0
 */
public class Winner implements Parcelable {

	/** Creator object for the parcelable implementation. */
	public static final Parcelable.Creator<Winner> CREATOR = ParcelableHelper.getDefaultCreator(Winner.class);

	private final Fighter winner;
	private final WinMethod winMethod;
	private final JudgeDecision decision;

	/**
	 * Constructor.
	 * 
	 * @param winner
	 * @param winMethod
	 */
	public Winner(Fighter winner, WinMethod winMethod, JudgeDecision decision) {
		if (winner == null && decision != JudgeDecision.Undecided) {
			throw new IllegalArgumentException("winner cannot be null.");
		}

		if (winMethod == null) {
			throw new IllegalArgumentException("winMethod cannot be null.");
		}

		if (decision == null) {
			throw new IllegalArgumentException("decision cannot be null.");
		}

		this.winner = winner;
		this.winMethod = winMethod;
		this.decision = decision;
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
		this.decision = JudgeDecision.valueOf(src.readString());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.winner, flags);
		dest.writeString(this.winMethod.name());
		dest.writeString(this.decision.name());
	}

	/**
	 * @return the winner
	 */
	public Fighter getWinner() {
		return winner;
	}

	/**
	 * @return the winMethod
	 */
	public WinMethod getWinMethod() {
		return winMethod;
	}

	/**
	 * @return the decision
	 */
	public JudgeDecision getDecision() {
		return decision;
	}
}
