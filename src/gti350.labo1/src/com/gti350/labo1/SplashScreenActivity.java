package com.gti350.labo1;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

/**
 * 
 * @author Simon Turcotte-Langevin
 */
public class SplashScreenActivity extends BaseActivity {

	/** Splash screen's timer timeout. */
	private static int SplashTimeout = 5000;

	/** */
	private static final String LoggingTag = "SplashScreenActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		// Apply portrait or landscape background.
		applyOrientedBackground((View) findViewById(R.id.activity_splash_screen));

		// Show the splash screen with a delay.
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// This method will be executed once the timer is over
				// Start your app main activity
				Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
				startActivity(i);

				// close this activity
				finish();
			}
		}, SplashTimeout);
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.splash_screen, menu);
	// return true;
	// }

	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // Handle action bar item clicks here. The action bar will
	// // automatically handle clicks on the Home/Up button, so long
	// // as you specify a parent activity in AndroidManifest.xml.
	// int id = item.getItemId();
	// if (id == R.id.action_settings) {
	// return true;
	// }
	// return super.onOptionsItemSelected(item);
	// }

	@Override
	public void onConfigurationChanged(Configuration configure) {
		super.onConfigurationChanged(configure);
		Log.i(LoggingTag, "Configuration change detected.");

		// Apply portrait or landscape background.
		applyOrientedBackground((View) findViewById(R.id.activity_splash_screen));
	}

	@Override
	protected String getLoggingTag() {
		return LoggingTag;
	}
}
