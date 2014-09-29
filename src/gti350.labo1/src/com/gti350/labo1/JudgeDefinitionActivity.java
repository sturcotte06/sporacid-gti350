package com.gti350.labo1;

import com.gti350.labo1.listeners.SwipeGestureListener;
import com.gti350.labo1.listeners.SwipeGestureListener.IOnSwipeListener;
import com.gti350.labo1.models.Fight;
import com.gti350.labo1.models.Fighter;
import com.gti350.labo1.models.Judge;

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

/**
 * @author Laurianne Michaud, Alexandre Billot, Simon Turcotte-Langevin
 * @version 1.0
 */
public class JudgeDefinitionActivity extends BaseActivity {

	/** The logging tag to quickly identify logs generated by this class. */
	private static final String LoggingTag = JudgeDefinitionActivity.class.getName();

	/** */
	private GestureDetectorCompat gestureDetector;

	/** */
	private Fighter redFighter;

	/** */
	private Fighter blueFighter;

	/** */
	private EditText firstJudgeTextbox;

	/** */
	private EditText secondJudgeTextbox;

	/** */
	private EditText thirdJudgeTextbox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_judge_definition);

		Bundle extras = getIntent().getExtras();
		Log.i(LoggingTag, "Retrieved extras from intent.");

		this.redFighter = (Fighter) extras.get(BaseActivity.RedFighterKey);
		if (this.redFighter == null) {
			throw new IllegalStateException("Bundle did not contain the red fighter. Cannot proceed with judge definition activity.");
		}

		Log.i(LoggingTag, "Found red fighter in intent.");

		this.blueFighter = (Fighter) extras.get(BaseActivity.BlueFighterKey);
		if (this.blueFighter == null) {
			throw new IllegalStateException("Bundle did not contain the blue fighter. Cannot proceed with judge definition activity.");
		}

		Log.i(LoggingTag, "Found blue fighter in intent.");

		// Cache useful controls.
		firstJudgeTextbox = (EditText) findViewById(R.id.textbox_first_judge);
		secondJudgeTextbox = (EditText) findViewById(R.id.textbox_second_judge);
		thirdJudgeTextbox = (EditText) findViewById(R.id.textbox_third_judge);

		// Create the listener for swiping.
		SwipeGestureListener swipeGestureListener = new SwipeGestureListener(new OnPreviousSwipeListener(), new OnNextSwipeListener());
		this.gestureDetector = new GestureDetectorCompat(this, swipeGestureListener);
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
		return this.gestureDetector.onTouchEvent(event) && super.onTouchEvent(event);
	}

	@Override
	public void onConfigurationChanged(Configuration configure) {
		super.onConfigurationChanged(configure);
		Log.i(LoggingTag, "Configuration change detected.");
	}

	/**
	 * 
	 * @author Laurianne Michaud, Alexandre Billot, Simon Turcotte-Langevin
	 * @version 1.0
	 */
	private class OnPreviousSwipeListener implements IOnSwipeListener {
		@Override
		public boolean onSwipe(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
			Intent i = new Intent(JudgeDefinitionActivity.this, FighterDefinitionActivity.class);

			i.putExtra(FighterDefinitionActivity.RedFighterKey, redFighter);
			Log.i(LoggingTag, "Serialized red fighter.");

			i.putExtra(FighterDefinitionActivity.BlueFighterKey, blueFighter);
			Log.i(LoggingTag, "Serialized blue fighter.");
			startActivity(i);

			return true;
		}
	}

	/**
	 * @author Laurianne Michaud, Alexandre Billot, Simon Turcotte-Langevin
	 * @version 1.0
	 */
	private class OnNextSwipeListener implements IOnSwipeListener {
		@Override
		public boolean onSwipe(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
			// Validate current activity content.
			Editable firstJudgeName = firstJudgeTextbox.getText();
			Editable secondJudgeName = secondJudgeTextbox.getText();
			Editable thirdJudgeName = thirdJudgeTextbox.getText();
			boolean firstJudgeValid = !firstJudgeName.toString().isEmpty();
			boolean secondJudgeValid = !secondJudgeName.toString().isEmpty();
			boolean thirdJudgeValid = !thirdJudgeName.toString().isEmpty();

			if (firstJudgeValid && secondJudgeValid && thirdJudgeValid) {
				Log.i(LoggingTag, "Judge definitions were valid.");

				Intent i = new Intent(JudgeDefinitionActivity.this, RoundDefinitionActivity.class);
				Judge firstJudge = new Judge(firstJudgeName.toString());
				Judge secondJudge = new Judge(firstJudgeName.toString());
				Judge thirdJudge = new Judge(firstJudgeName.toString());

				Fight fight = new Fight(redFighter, blueFighter, firstJudge, secondJudge, thirdJudge);
				i.putExtra(BaseActivity.FightKey, fight);
				Log.i(LoggingTag, "Serialized fight.");

				startActivity(i);
				return true;
			} else {
				Log.i(LoggingTag, "Detected invalid judge definition.");

				if (!firstJudgeValid) {
					firstJudgeTextbox.setError(getString(R.string.first_judge_empty));
				}

				if (!secondJudgeValid) {
					secondJudgeTextbox.setError(getString(R.string.second_judge_empty));
				}

				if (!thirdJudgeValid) {
					thirdJudgeTextbox.setError(getString(R.string.third_judge_empty));
				}

				return false;
			}
		}
	}
}
