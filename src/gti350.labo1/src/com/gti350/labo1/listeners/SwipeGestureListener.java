package com.gti350.labo1.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Gesture detector for a previous/next swipe detector.
 * 
 * @author Simon Turcotte-Langevin
 */
public class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {
	/** The current activity. */
	private Activity currentActivity;
	/** The previous activity class. */
	private Class<? extends Activity> previousActivityClass;
	/** The next activity class. */
	private Class<? extends Activity> nextActivityClass;

	/**
	 * Constructor.
	 * 
	 * @param currentActivity
	 *            The current activity.
	 * @param previousActivityClass
	 *            The previous activity class.
	 * @param nextActivityClass
	 *            The next activity class.
	 */
	public SwipeGestureListener(Activity currentActivity, Class<? extends Activity> previousActivityClass, Class<? extends Activity> nextActivityClass) {
		if (currentActivity == null) {
			throw new IllegalArgumentException("currentActivity cannot be null.");
		}

		if (previousActivityClass == null) {
			throw new IllegalArgumentException("previousActivityClass cannot be null.");
		}

		if (nextActivityClass == null) {
			throw new IllegalArgumentException("nextActivityClass cannot be null.");
		}

		this.currentActivity = currentActivity;
		this.previousActivityClass = previousActivityClass;
		this.nextActivityClass = nextActivityClass;
	}

	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
		Intent i = null;
		if (velocityX > 0) {
			// Swiped from the left to the right.
			i = new Intent(currentActivity, nextActivityClass);
		} else if (velocityX < 0) {
			// Swiped from the right to the left.
			i = new Intent(currentActivity, previousActivityClass);
		} else {
			// No velocity, so no movement was made toward the left nor the
			// right.
			return false;
		}

		// Start the next or previous activity.
		currentActivity.startActivity(i);

		// Close this activity.
		currentActivity.finish();

		// Event consumed.
		return true;
	}
}
