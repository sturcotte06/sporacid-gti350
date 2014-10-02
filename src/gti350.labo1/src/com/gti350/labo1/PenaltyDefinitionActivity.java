package com.gti350.labo1;

import java.util.Arrays;
import java.util.List;

import com.gti350.labo1.models.Fight;
import com.gti350.labo1.models.Score;

import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * @author Laurianne Michaud, Alexandre Billot, Simon Turcotte-Langevin
 * @version 1.0
 */
public class PenaltyDefinitionActivity extends BaseActivity {

	/** The logging tag to quickly identify logs generated by this class. */
	private static final String LoggingTag = PenaltyDefinitionActivity.class.getName();

	/** */
	private Fight fight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_penalty_definition);

		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			throw new IllegalStateException("Bundle did not contain any extra data. Cannot proceed with judge definition activity.");
		}

		Log.i(LoggingTag, "Retrieved extras from intent.");

		this.fight = (Fight) extras.get(BaseActivity.FightKey);
		if (this.fight == null) {
			throw new IllegalStateException("Bundle did not contain the fight object. Cannot proceed with penalty definition activity.");
		}

		ToggleButton firstJudgeButton = (ToggleButton) findViewById(R.id.button_first_judge);
		ToggleButton secondJudgeButton = (ToggleButton) findViewById(R.id.button_second_judge);
		ToggleButton thirdJudgeButton = (ToggleButton) findViewById(R.id.button_third_judge);

		firstJudgeButton.setTextOn(this.fight.getJudge1().getName());
		firstJudgeButton.setTextOff(this.fight.getJudge1().getName());
		firstJudgeButton.setOnCheckedChangeListener(new OnExclusiveToggleButtonCheckedChanged(secondJudgeButton, thirdJudgeButton));
		firstJudgeButton.setChecked(firstJudgeButton.isChecked());
		secondJudgeButton.setTextOn(this.fight.getJudge2().getName());
		secondJudgeButton.setTextOff(this.fight.getJudge2().getName());
		secondJudgeButton.setOnCheckedChangeListener(new OnExclusiveToggleButtonCheckedChanged(firstJudgeButton, thirdJudgeButton));
		secondJudgeButton.setChecked(secondJudgeButton.isChecked());
		thirdJudgeButton.setTextOn(this.fight.getJudge3().getName());
		thirdJudgeButton.setTextOff(this.fight.getJudge3().getName());
		thirdJudgeButton.setOnCheckedChangeListener(new OnExclusiveToggleButtonCheckedChanged(secondJudgeButton, thirdJudgeButton));
		thirdJudgeButton.setChecked(thirdJudgeButton.isChecked());

		ToggleButton redFighterButton = (ToggleButton) findViewById(R.id.button_fighter_red);
		ToggleButton blueFighterButton = (ToggleButton) findViewById(R.id.button_fighter_blue);

		redFighterButton.setTextOn(this.fight.getFighter1().getName());
		redFighterButton.setTextOff(this.fight.getFighter1().getName());
		redFighterButton.setOnCheckedChangeListener(new OnExclusiveToggleButtonCheckedChanged(blueFighterButton));
		redFighterButton.setChecked(redFighterButton.isChecked());
		blueFighterButton.setTextOn(this.fight.getFighter2().getName());
		blueFighterButton.setTextOff(this.fight.getFighter2().getName());
		blueFighterButton.setOnCheckedChangeListener(new OnExclusiveToggleButtonCheckedChanged(redFighterButton));
		blueFighterButton.setChecked(blueFighterButton.isChecked());

		NumberPicker weightNumberPicker = (NumberPicker) findViewById(R.id.number_picker_weight);
		weightNumberPicker.setMinValue(Score.MinimumEffectiveScore);
		weightNumberPicker.setMaxValue(Score.MaximumScore);
		weightNumberPicker.setValue(1);
		weightNumberPicker.setWrapSelectorWheel(false);

		Button okButton = (Button) findViewById(R.id.button_ok);
		Button cancelButton = (Button) findViewById(R.id.button_cancel);
	}

	private class OnExclusiveToggleButtonCheckedChanged implements OnCheckedChangeListener {
		/** */
		private List<CompoundButton> linkedButtons;

		/** The color of the background when the button is checked. */
		private int onColor;

		/** The color of the background when the button is not checked. */
		private int offColor;

		public OnExclusiveToggleButtonCheckedChanged(CompoundButton... linkedButtons) {
			this.linkedButtons = Arrays.asList(linkedButtons);
			this.onColor = getResources().getColor(R.color.toggle_button_on);
			this.offColor = getResources().getColor(R.color.toggle_button_off);
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// Toggle the other linked buttons.
			for (CompoundButton linkedButton : linkedButtons) {
				linkedButton.setChecked(false);
				linkedButton.setBackgroundColor(offColor);
			}

			buttonView.setChecked(true);
			buttonView.setBackgroundColor(onColor);
		}
	}
}
