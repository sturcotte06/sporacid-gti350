package com.gti350.labo1;

import android.support.v7.app.ActionBarActivity;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public abstract class BaseActivity extends ActionBarActivity {

	/**
	 * Returns the logging tag for the current activity.
	 * 
	 * @return The logging tag.
	 */
	protected abstract String getLoggingTag();

	/**
	 * Applies the good background based on the app's orientation.
	 * 
	 * @param view
	 *            The view on which to apply the background.
	 */
	/*protected void applyOrientedBackground(View view) {
		Log.i(getLoggingTag(),
				"Applying background depending on screen orientation.");

		int orientation = getResources().getConfiguration().orientation;
		if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
			view.setBackgroundResource(R.drawable.boxing_ring_landscape);
		} else {
			view.setBackgroundResource(R.drawable.boxing_ring_portrait);
		}
	}*/
}
