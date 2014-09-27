package com.gti350.labo1;

import com.gti350.labo1.listeners.SwipeGestureListener;
import com.gti350.labo1.listeners.SwipeGestureListener.IOnSwipeListener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.EditText;

public class FighterDefinitionActivity extends BaseActivity {

	/** */
	private static final String LoggingTag = "FighterDefinitionActivity";

	/** */
	private GestureDetectorCompat gestureDetector;

	/** */
	private EditText redFighterTextbox;

	/** */
	private EditText blueFighterTextbox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fighter_definition);

		// Cache useful controls.
		redFighterTextbox = (EditText) findViewById(R.id.textbox_red_fighter);
		blueFighterTextbox = (EditText) findViewById(R.id.textbox_blue_fighter);

		// Create the listener for swiping.
		SwipeGestureListener swipeGestureListener = new SwipeGestureListener(null, new IOnNextSwipeListener());
		gestureDetector = new GestureDetectorCompat(this, swipeGestureListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fighter_definition, menu);
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
		return gestureDetector.onTouchEvent(event) && super.onTouchEvent(event);
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
	 * @author Laurianne Michaud, Simon Turcotte-Langevin
	 */
	private class IOnNextSwipeListener implements IOnSwipeListener {
		@Override
		public boolean onSwipe(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
			// Validate current activity content.
			Editable redFighter = redFighterTextbox.getText();
			Editable blueFighter = blueFighterTextbox.getText();

			boolean requiresLineCarriage = false;
			StringBuilder stringBuilder = new StringBuilder();
			if (redFighter.length() == 0) {
				stringBuilder.append(getString(R.string.red_fighter_empty));
				requiresLineCarriage = true;
			}

			if (blueFighter.length() == 0) {
				stringBuilder.append(requiresLineCarriage ? "\n" : "").append(getString(R.string.blue_fighter_empty));
			}

			if (stringBuilder.length() == 0) {
				Intent i = new Intent(FighterDefinitionActivity.this, JudgeDefinitionActivity.class);
				startActivity(i);

				return true;
			}

			AlertDialog.Builder builder1 = new AlertDialog.Builder(FighterDefinitionActivity.this);
			builder1.setTitle("Alert");
			builder1.setMessage(stringBuilder);
			builder1.setCancelable(true);
			builder1.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
				}
			});

			AlertDialog alert11 = builder1.create();
			alert11.show();

			return false;
		}
	}
}
