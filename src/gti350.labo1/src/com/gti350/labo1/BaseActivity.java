package com.gti350.labo1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;

/**
 * 
 * @author Laurianne Michaud, Simon Turcotte-Langevin
 */
public abstract class BaseActivity extends ActionBarActivity {
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
