package com.gti350.labo1.models;

import com.gti350.labo1.models.utils.ParcelableHelper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Structure to define a fighter in a box fight.
 * 
 * @author Simon Turcotte-Langevin
 */
public class Judge implements Parcelable {
	/** Creator object for the parcelable implementation. */
	public static final Parcelable.Creator<Judge> CREATOR = ParcelableHelper.getDefaultCreator(Judge.class);

	/** The name of the judge. */
	private final String name;
	/** The nationality of the judge. */
	private final String nationality;

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            The name of the judge.
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
	 * @param name
	 *            The name of the judge.
	 * @param nationality
	 *            The nationality of the judge.
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
	 * Parcelable required constructor.
	 * 
	 * @param src
	 *            The parcel that describes this judge.
	 */
	public Judge(Parcel src) {
		if (src == null) {
			throw new IllegalArgumentException("src cannot be null.");
		}

		this.nationality = src.readString();
		this.name = src.readString();
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.nationality);
		dest.writeString(this.name);
	}
}
