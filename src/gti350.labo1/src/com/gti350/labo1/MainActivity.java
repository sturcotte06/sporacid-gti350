package com.gti350.labo1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

	/** */
	private static final String LoggingTag = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Apply portrait or landscape background.
		applyOrientedBackground((View) findViewById(R.id.activity_main));

		// Bind touch events on buttons.
		Button settingsButton = (Button) findViewById(R.id.button_settings);
		settingsButton.setOnClickListener(new OnSettingsButtonClicked());

		Button startFightButton = (Button) findViewById(R.id.button_start_fight);
		startFightButton.setOnClickListener(new OnStartFightButtonClicked());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConfigurationChanged(Configuration configure) {
		super.onConfigurationChanged(configure);
		Log.i(LoggingTag, "Configuration change detected.");

		// Apply portrait or landscape background.
		applyOrientedBackground((View) findViewById(R.id.activity_main));
	}

	@Override
	protected String getLoggingTag() {
		return LoggingTag;
	}

	/**
	 * 
	 * @author Simon Turcotte-Langevin
	 */
	private class OnSettingsButtonClicked implements View.OnClickListener {
		@Override
		public void onClick(View v) {

		}
	}

	/**
	 * 
	 * @author Simon Turcotte-Langevin
	 */
	private class OnStartFightButtonClicked implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			// simulateLenghtyOperation();
		}

		// private void simulateLenghtyOperation() {
		// Intent intent = new Intent(MainActivity.this,
		// SplashScreenActivity.class);
		// startActivity(intent);
		//
		// final int iterations = 10;
		// final ProgressBar progressBar = (ProgressBar)
		// findViewById(R.id.splash_screen_progress_bar);
		//
		// final Thread runnable = new Thread(new Runnable() {
		// @Override
		// public void run() {
		// try {
		//
		// for (int iIteration = 0; iIteration < iterations; iIteration++) {
		// int newProgress = (int) ((iIteration + 1.0) / iterations *
		// progressBar.getMax());
		// Log.i(getLoggingTag(), "New Progress : " + newProgress);
		//
		// Thread.sleep(500);
		// progressBar.setProgress(newProgress);
		// }
		// } catch (InterruptedException ex) {
		// // Propagate.
		// Thread.currentThread().interrupt();
		// }
		//
		// }
		// });
		//
		// runnable.start();
		//
		// try {
		// runnable.join();
		// } catch (InterruptedException e) {
		// // Propagate.
		// Thread.currentThread().interrupt();
		// }
		// }
	}
}
