package com.gti350.labo1.models.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Helper class for implementing parcelable.
 * 
 * @author Simon Turcotte-Langevin
 */
public final class ParcelableHelper {
	/** Cache of creator objects, by their classes. */
	private static final Map<Class<? extends Parcelable>, Parcelable.Creator<? extends Parcelable>> creatorCache = new HashMap<>();

	/**
	 * Creates a standard creator object using reflection. The parcelable class
	 * must have a constructor that takes a single Parcel object as parameter,
	 * or this will throw.
	 * 
	 * @return A creator object.
	 */
	@SuppressWarnings("unchecked")
	public static final <TParcelable extends Parcelable> Parcelable.Creator<TParcelable> getDefaultCreator(final Class<TParcelable> classObj) {
		if (creatorCache.containsKey(classObj)) {
			// Return cached value.
			return (Parcelable.Creator<TParcelable>) creatorCache.get(classObj);
		}

		// By reflection, create new instances of TParcelable.
		Parcelable.Creator<TParcelable> creator = new Parcelable.Creator<TParcelable>() {
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
			public TParcelable[] newArray(int size) {
				return (TParcelable[]) Array.newInstance(classObj, size);
			}
		};

		// Cache the creator object for future use.
		creatorCache.put(classObj, creator);

		return creator;
	}
}
