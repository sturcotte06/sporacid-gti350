package com.gti350.labo1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;

/**
 * 
 * @author Laurianne Michaud, Alexandre Billot, Simon Turcotte-Langevin
 * @version 1.0
 */
public abstract class BaseActivity extends ActionBarActivity {
	/**
	 * Key to be used whenever we want to serialize the red fighter object into
	 * an intent.
	 */
	protected static final String RedFighterKey = "FighterDefinition_RedFighter";

	/**
	 * Key to be used whenever we want to serialize the blue fighter object into
	 * an intent.
	 */
	protected static final String BlueFighterKey = "FighterDefinition_BlueFighter";

	/**
	 * Key to be used whenever we want to serialize the blue fighter object into
	 * an intent.
	 */
	protected static final String FirstJudgeKey = "JudgeDefinition_FirstJudge";

	/**
	 * Key to be used whenever we want to serialize the first judge object into
	 * an intent.
	 */
	protected static final String SecondJudgeKey = "JudgeDefinition_SecondJudge";

	/**
	 * Key to be used whenever we want to serialize the second judge object into
	 * an intent.
	 */
	protected static final String ThirdJudgeKey = "JudgeDefinition_ThirdJudge";

	/**
	 * Key to be used whenever we want to serialize the third judge into an
	 * intent.
	 */
	protected static final String FightKey = "FightDefinition_Fight";

	/**
	 * Key to be used whenever we want to serialize the current round counter
	 * into an intent.
	 */
	protected static final String CurrentRoundCounterKey = "RoundDefinition_CurrentRoundCounter";

	/**
	 * Displays an alert for the validations messages in parameter.
	 * 
	 * @param validationMessages
	 *            The validation messages.
	 */
	protected void displayValidationAlert(String... validationMessages) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String validationMessage : validationMessages) {
			stringBuilder.append(validationMessage).append("\n");
		}

		// Truncate last line carriage.
		stringBuilder.setLength(stringBuilder.length() - "\n".length());

		// Call overload.
		displayValidationAlert(stringBuilder);
	}

	/**
	 * Displays an alert for the validations message in parameter.
	 * 
	 * @param validationMessage
	 *            The validation message.
	 */
	protected void displayValidationAlert(CharSequence validationMessage) {
		AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
		builder.setTitle(R.string.title_alert_validation);
		builder.setMessage(validationMessage);
		builder.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
			}
		});

		builder.create().show();
	}
}
