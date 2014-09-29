package com.gti350.labo1.models;

import com.gti350.labo1.models.utils.ParcelableHelper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class JudgeScore implements Parcelable {
	/** Creator object for the parcelable implementation. */
	public static final Parcelable.Creator<JudgeScore> CREATOR = ParcelableHelper.getDefaultCreator(JudgeScore.class);

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
	 * Parcelable required constructor.
	 * 
	 * @param src
	 *            The parcel that describes this judge.
	 */
	public JudgeScore(Parcel src) {
		if (src == null) {
			throw new IllegalArgumentException("src cannot be null.");
		}

		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		this.judge = src.readParcelable(classLoader);
		this.scoreFighter1 = src.readParcelable(classLoader);
		this.scoreFighter2 = src.readParcelable(classLoader);
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.judge, flags);
		dest.writeParcelable(this.scoreFighter1, flags);
		dest.writeParcelable(this.scoreFighter2, flags);
	}
}
