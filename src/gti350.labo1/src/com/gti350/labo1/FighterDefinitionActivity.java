package com.gti350.labo1;

import com.gti350.labo1.listeners.SwipeGestureListener;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class FighterDefinitionActivity extends BaseActivity {

	/** */
	private static final String LoggingTag = "FighterDefinitionActivity";

	/** */
	private GestureDetectorCompat gestureDetector;

	/** */
	private EditText redFighterTextbox;

	/** */
	private EditText blueFighterTextbox;

	private ViewGroup messageContainer;

	/** */
	private TextView messageLabel;

	/** */
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fighter_definition);

		// Cache useful controls.
		redFighterTextbox = (EditText) findViewById(R.id.textbox_red_fighter);
		blueFighterTextbox = (EditText) findViewById(R.id.textbox_blue_fighter);
		messageLabel = (TextView) findViewById(R.id.label_message);
		messageContainer = (ViewGroup) findViewById(R.id.label_message_layout);
		handler = new Handler();
		gestureDetector = new GestureDetectorCompat(this, new SwipeGestureListener(this, MainActivity.class, JudgeDefinitionActivity.class));
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
			// No validation error. We may proceed.
			return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
		}

		// Show the error.
		messageLabel.setText(stringBuilder);
		messageContainer.setVisibility(View.VISIBLE);

		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				messageContainer.setVisibility(View.INVISIBLE);
				// messageContainer.setVisibility(View.GONE);
			}
		}, 5000);

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
