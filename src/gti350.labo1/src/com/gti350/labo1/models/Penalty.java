package com.gti350.labo1.models;

import com.gti350.labo1.models.utils.ParcelableHelper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class Penalty implements Parcelable {
	/** Creator object for the parcelable implementation. */
	public static final Parcelable.Creator<Penalty> CREATOR = ParcelableHelper.getDefaultCreator(Penalty.class);

	/** Minimum weight on a penalty. */
	private static final int MinimumWeight = 0;
	/** Maximum weight on a penalty. */
	private static final int MaximumWeight = 10;

	/** The weight of the penalty, in points. */
	private final int weight;
	/** The cause of the penalty. */
	private final PenaltyCause cause;

	/**
	 * Constructor.
	 * 
	 * @param weight
	 *            The weight of the penalty, in points.
	 * @param cause
	 *            The cause of the penalty.
	 */
	public Penalty(int weight, PenaltyCause cause) {
		if (weight < MinimumWeight || weight > MaximumWeight) {
			throw new IllegalArgumentException(String.format("weight must be between %d and %d.", MinimumWeight, MaximumWeight));
		}

		if (cause == null) {
			throw new IllegalArgumentException("cause cannot be null.");
		}

		this.weight = weight;
		this.cause = cause;
	}

	/**
	 * Parcelable required constructor.
	 * 
	 * @param src
	 *            The parcel that describes this penalty.
	 */
	public Penalty(Parcel src) {
		if (src == null) {
			throw new IllegalArgumentException("src cannot be null.");
		}

		this.cause = PenaltyCause.valueOf(src.readString());
		this.weight = src.readInt();
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.cause.name());
		dest.writeInt(this.weight);
	}
}
