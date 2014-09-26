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
public class SwipeListener extends GestureDetector.SimpleOnGestureListener {
	
	IOnPreviousSwipe previousSwipe; 
	IOnNextSwipe nextSwipe; 
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


	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

		if (velocityX > 0 && nextSwipe != null) {
			// Swiped from the left to the right.
			return nextSwipe.IOnNextSwipe(event1, event2, velocityX, velocityY);
			
			 
		} else if (velocityX < 0  && previousSwipe != null) {
			// Swiped from the right to the left.
			return previousSwipe.IOnPreviousSwipe(event1, event2, velocityX, velocityY);
		} else {
			// No velocity, so no movement was made toward the left nor the
			// right.
			return false;
		}

	}
	
	public interface IOnPreviousSwipe {
		
		public boolean IOnPreviousSwipe(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY);
			
	}
	
	public interface IOnNextSwipe{
		
		public boolean IOnNextSwipe(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY);
	}

	public IOnPreviousSwipe getPreviousSwipe() {
		return previousSwipe;
	}

	public void setPreviousSwipe(IOnPreviousSwipe previousSwipe) {
		this.previousSwipe = previousSwipe;
	}

	public IOnNextSwipe getNextSwipe() {
		return nextSwipe;
	}

	public void setNextSwipe(IOnNextSwipe nextSwipe) {
		this.nextSwipe = nextSwipe;
	}
	
	
}
