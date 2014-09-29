package com.gti350.labo1.models;

import com.gti350.labo1.models.utils.ParcelableHelper;

import android.os.Parcel;
import android.os.Parcelable;

public class Round implements Parcelable {

	/** Creator object for the parcelable implementation. */
	public static final Parcelable.Creator<Round> CREATOR = ParcelableHelper.getDefaultCreator(Round.class);

	/** The score given to each fighters by the first judge. */
	private JudgeScore judgeScore1;

	/** The score given to each fighters by the second judge. */
	private JudgeScore judgeScore2;

	/** The score given to each fighters by the third judge. */
	private JudgeScore judgeScore3;

	/**
	 * Constructor.
	 * 
	 * @param judgeScore1
	 *            The score given to each fighters by the first judge.
	 * @param judgeScore2
	 *            The score given to each fighters by the second judge.
	 * @param judgeScore3
	 *            The score given to each fighters by the third judge.
	 */
	public Round(JudgeScore judgeScore1, JudgeScore judgeScore2, JudgeScore judgeScore3) {
		if (judgeScore1 == null) {
			throw new IllegalArgumentException("judgeScore1 cannot be null.");
		}

		if (judgeScore2 == null) {
			throw new IllegalArgumentException("judgeScore2 cannot be null.");
		}

		if (judgeScore3 == null) {
			throw new IllegalArgumentException("judgeScore3 cannot be null.");
		}

		this.judgeScore1 = judgeScore1;
		this.judgeScore2 = judgeScore2;
		this.judgeScore3 = judgeScore3;
	}

	/**
	 * Parcelable required constructor.
	 * 
	 * @param src
	 *            The parcel that describes this round.
	 */
	public Round(Parcel src) {
		if (src == null) {
			throw new IllegalArgumentException("src cannot be null.");
		}

		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		this.judgeScore1 = src.readParcelable(classLoader);
		this.judgeScore2 = src.readParcelable(classLoader);
		this.judgeScore3 = src.readParcelable(classLoader);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.judgeScore1, flags);
		dest.writeParcelable(this.judgeScore2, flags);
		dest.writeParcelable(this.judgeScore3, flags);
	}
}
