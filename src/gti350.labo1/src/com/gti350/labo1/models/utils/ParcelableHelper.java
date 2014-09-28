package com.gti350.labo1.models.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Helper class for implementing parcelable.
 * 
 * @author Simon Turcotte-Langevin
 */
public final class ParcelableHelper {
	/**
	 * Creates a standard creator object using reflection. The parcelable class
	 * must have a constructor that takes a single Parcel object as parameter,
	 * or this will throw.
	 * 
	 * @return A creator object.
	 */
	public static final <TParcelable extends Parcelable> Parcelable.Creator<TParcelable> getDefaultCreator(final Class<TParcelable> classObj) {
		// By reflection, create new instances of TParcelable.
		return new Parcelable.Creator<TParcelable>() {
			@Override
			public TParcelable createFromParcel(Parcel in) {
				Constructor<TParcelable> parcelableConstructor;
				try {
					// Try to get the constructor that take a single Parcel
					// object as parameter.
					parcelableConstructor = classObj.getConstructor(Parcel.class);
				} catch (NoSuchMethodException ex) {
					// The class does not have such constructor. Bad
					// implementation of parcelable.
					throw new BadParcelableException(ex);
				}

				try {
					// Create the new object with the constructor.
					return parcelableConstructor.newInstance(in);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
					// Something bad happenened using reflection.
					throw new BadParcelableException(ex);
				}
			}

			@Override
			@SuppressWarnings("unchecked")
			public TParcelable[] newArray(int size) {
				return (TParcelable[]) Array.newInstance(classObj, size);
			}
		};
	}
}
