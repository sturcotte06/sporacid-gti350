package com.gti350.labo1;

import android.content.Intent;
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
			// Start the fighter's definition activity.
			Intent i = new Intent(MainActivity.this, FighterDefinitionActivity.class);
			startActivity(i);

			// close this activity
			finish();
		}
	}
}
