package com.gti350.labo1.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.gti350.labo1.models.utils.ParcelableHelper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class Score implements Parcelable {
	/** Creator object for the parcelable implementation. */
	public static final Parcelable.Creator<Score> CREATOR = ParcelableHelper.getDefaultCreator(Score.class);

	/** The minimum effective score for a round. */
	private static final int MinimumEffectiveScore = 0;
	/** The minimum initial score for a round. */
	private static final int MinimumInitialScore = 9;
	/** The maximum score for a round. */
	private static final int MaximumScore = 10;

	/** The initial value for the score. */
	private final int initialScore;
	/** A collection of penalties. */
	private final List<Penalty> penalties;

	/**
	 * Constructor.
	 * 
	 * @param initialScore
	 * @param penalties
	 */
	public Score(int initialScore, Penalty... penalties) {
		if (initialScore < MinimumInitialScore || initialScore > MaximumScore) {
			throw new IllegalArgumentException(String.format("initialScore must be between %d and %d.", MinimumInitialScore, MaximumScore));
		}

		this.initialScore = initialScore;
		this.penalties = Arrays.asList(penalties);
	}

	/**
	 * Parcelable required constructor.
	 * 
	 * @param src
	 *            The parcel that describes this score.
	 */
	public Score(Parcel src) {
		if (src == null) {
			throw new IllegalArgumentException("src cannot be null.");
		}

		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		this.initialScore = src.readInt();

		this.penalties = new ArrayList<>();
		src.readList(this.penalties, classLoader);
	}

	/**
	 * Return the effective score, which is the initial score with every
	 * penalties applied.
	 * 
	 * @return The effective score.
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.initialScore);
		dest.writeList(this.penalties);
	}
}
