package com.gti350.labo1;

import com.gti350.labo1.listeners.SwipeGestureListener;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class JudgeDefinitionActivity extends BaseActivity {

	/** */
	private static final String LoggingTag = "FighterDefinitionActivity";

	/** */
	private GestureDetectorCompat gestureDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_judge_definition);

		this.gestureDetector = new GestureDetectorCompat(this, new SwipeGestureListener(this, MainActivity.class, SplashScreenActivity.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.judge_definition, menu);
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
	public boolean onTouchEvent(MotionEvent event) {
		this.gestureDetector.onTouchEvent(event);
		return super.onTouchEvent(event);
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
}
