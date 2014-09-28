package com.gti350.labo1.models;

import com.gti350.labo1.models.utils.ParcelableHelper;

import android.os.Parcel;
import android.os.Parcelable;

public class Round implements Parcelable {

	/** Creator object for the parcelable implementation. */
	public static final Parcelable.Creator<Round> CREATOR = ParcelableHelper.getDefaultCreator(Round.class);

	public Round() {
		
	}
	
	/**
	 * Parcelable required constructor.
	 * 
	 * @param src
	 *            The parcel that describes this judge.
	 */
	public Round(Parcel src) {
		if (src == null) {
			throw new IllegalArgumentException("src cannot be null.");
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

	}
}
