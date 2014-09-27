package com.gti350.labo1.listeners;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Gesture detector for a previous/next swipe detector.
 * 
 * @author Laurianne Michaud, Simon Turcotte-Langevin
 */
public class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {
	/** */
	private static final String TAG = SwipeGestureListener.class.getName();

	/** The swipe listener for a swipe that happened from left to right. */
	private IOnSwipeListener leftToRightListener;
	/** The swipe listener for a swipe that happened from right to left. */
	private IOnSwipeListener rightToLeftListener;
	/** The swipe listener for a swipe that happened from bottom to top. */
	private IOnSwipeListener bottomToTopListener;
	/** The swipe listener for a swipe that happened from top to bottom. */
	private IOnSwipeListener topToBottomListener;

	/**
	 * Empty constructor.
	 */
	public SwipeGestureListener() {
		this(null, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param leftToRightListener
	 *            The swipe listener for a swipe that happened from left to
	 *            right.
	 * @param rightToLeftListener
	 *            The swipe listener for a swipe that happened from right to
	 *            left.
	 */
	public SwipeGestureListener(IOnSwipeListener leftToRightListener, IOnSwipeListener rightToLeftListener) {
		this(leftToRightListener, rightToLeftListener, null, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param leftToRightListener
	 *            The swipe listener for a swipe that happened from left to
	 *            right.
	 * @param rightToLeftListener
	 *            The swipe listener for a swipe that happened from right to
	 *            left.
	 * @param bottomToTopListener
	 *            The swipe listener for a swipe that happened from bottom to
	 *            top.
	 * @param topToBottomListener
	 *            The swipe listener for a swipe that happened from top to
	 *            bottom.
	 */
	public SwipeGestureListener(IOnSwipeListener leftToRightListener, IOnSwipeListener rightToLeftListener, IOnSwipeListener bottomToTopListener, IOnSwipeListener topToBottomListener) {
		this.leftToRightListener = leftToRightListener;
		this.rightToLeftListener = rightToLeftListener;
		this.bottomToTopListener = bottomToTopListener;
		this.topToBottomListener = topToBottomListener;
	}

	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
		boolean flingHandled = false;

		// Get then angle between the two velocities.
		float velocityRelativeAngle = getVelocityRelativeAngle(velocityX, velocityY);

		if (velocityRelativeAngle < 45) {
			// Swipe was either from left to right or right to left.
			if (velocityX >= 0) {
				// Swiped from left to right.
				flingHandled = leftToRightListener != null && leftToRightListener.onSwipe(event1, event2, velocityX, velocityY);
				Log.i(TAG, "Left to right swipe detected.");
			} else if (velocityX < 0) {
				// Swiped from right to left.
				flingHandled = rightToLeftListener != null && rightToLeftListener.onSwipe(event1, event2, velocityX, velocityY);
				Log.i(TAG, "Right to left swipe detected.");
			}
		} else {
			// Swipe was either from bottom to top or top to bottom.
			if (velocityY >= 0) {
				// Swiped from bottom to top.
				flingHandled = bottomToTopListener != null && bottomToTopListener.onSwipe(event1, event2, velocityX, velocityY);
				Log.i(TAG, "Bottom to top swipe detected.");
			} else if (velocityY < 0) {
				// Swiped from top to bottom.
				flingHandled = topToBottomListener != null && topToBottomListener.onSwipe(event1, event2, velocityX, velocityY);
				Log.i(TAG, "Top to bottom swipe detected.");
			}
		}

		return flingHandled;
	}

	/**
	 * Return the relative angle between a velocity and the x axis.
	 * 
	 * @param velocityX
	 *            The velocity along the x axis.
	 * @param velocityY
	 *            The velocity along the y axis.
	 * @return The angle between the vector of this velocity, and the x axis.
	 */
	private float getVelocityRelativeAngle(float velocityX, float velocityY) {
		float velocityRelativeAngle = 0;
		if (velocityX == 0) {
			// atan would be NaN if velocityX = 0.
			velocityRelativeAngle = 90;
		} else {
			velocityRelativeAngle = 180 * (float) (Math.atan(Math.abs(velocityY) / Math.abs(velocityX)) / Math.PI);
		}

		return velocityRelativeAngle;
	}

	/**
	 * Return the angle of the velocity. Reference axe is the x axis positive,
	 * and angles are calculated counter clockwise.
	 * 
	 * @param velocityX
	 *            The velocity along the x axis.
	 * @param velocityY
	 *            The velocity along the y axis.
	 * @return
	 */
	private float getVelocityAngle(float velocityX, float velocityY) {
		float velocityAngle = 0;
		float velocityRelativeAngle = getVelocityRelativeAngle(velocityX, velocityY);

		// Then, find the initial value for the angle based on its quadrant
		// in a virtual geometrical plan.
		if (velocityX >= 0 && velocityY >= 0) {
			// 1st quadrant.
			velocityAngle = 0 + velocityRelativeAngle;
		} else if (velocityX < 0 && velocityY >= 0) {
			// 2nd quadrant.
			velocityAngle = 180 - velocityRelativeAngle;
		} else if (velocityX < 0 && velocityY < 0) {
			// 3rd quadrant.
			velocityAngle = 180 + velocityRelativeAngle;
		} else if (velocityX >= 0 && velocityY < 0) {
			// 4th quadrant.
			velocityAngle = 360 - velocityRelativeAngle;
		}

		return velocityAngle;
	}

	/**
	 * Interface for the listener of a swipe event.
	 * 
	 * @author Laurianne Michaud, Simon Turcotte-Langevin
	 */
	public interface IOnSwipeListener {
		/**
		 * 
		 * @param event1
		 * @param event2
		 * @param velocityX
		 * @param velocityY
		 * @return
		 */
		public boolean onSwipe(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY);
	}

	/**
	 * @return the leftToRightListener
	 */
	public IOnSwipeListener getLeftToRightListener() {
		return leftToRightListener;
	}

	/**
	 * @param leftToRightListener
	 *            the leftToRightListener to set
	 */
	public void setLeftToRightListener(IOnSwipeListener leftToRightListener) {
		this.leftToRightListener = leftToRightListener;
	}

	/**
	 * @return the rightToLeftListener
	 */
	public IOnSwipeListener getRightToLeftListener() {
		return rightToLeftListener;
	}

	/**
	 * @param rightToLeftListener
	 *            the rightToLeftListener to set
	 */
	public void setRightToLeftListener(IOnSwipeListener rightToLeftListener) {
		this.rightToLeftListener = rightToLeftListener;
	}

	/**
	 * @return the bottomToTopListener
	 */
	public IOnSwipeListener getBottomToTopListener() {
		return bottomToTopListener;
	}

	/**
	 * @param bottomToTopListener
	 *            the bottomToTopListener to set
	 */
	public void setBottomToTopListener(IOnSwipeListener bottomToTopListener) {
		this.bottomToTopListener = bottomToTopListener;
	}

	/**
	 * @return the topToBottomListener
	 */
	public IOnSwipeListener getTopToBottomListener() {
		return topToBottomListener;
	}

	/**
	 * @param topToBottomListener
	 *            the topToBottomListener to set
	 */
	public void setTopToBottomListener(IOnSwipeListener topToBottomListener) {
		this.topToBottomListener = topToBottomListener;
	}
}
