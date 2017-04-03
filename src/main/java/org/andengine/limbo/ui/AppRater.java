package org.andengine.limbo.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;

public class AppRater {
	private static final String APPRATER = "apprater";
	private static final String DONTSHOWAGAIN = "dontshowagain";

	public static void onAppLaunched(Context ctx, String pAppName, String pPckgName, Drawable pIcon, int minutesUntilPrompt, int pLaunchesUntilPrompt) {
		SharedPreferences prefs = ctx.getSharedPreferences(APPRATER, 0);
		if (prefs.getBoolean(DONTSHOWAGAIN, false)) {
			return;
		}

		SharedPreferences.Editor editor = prefs.edit();

		// Increment launch counter
		long launch_count = prefs.getLong("launch_count", 0) + 1;
		editor.putLong("launch_count", launch_count);

		// Get date of first launch
		Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
		if (date_firstLaunch == 0) {
			date_firstLaunch = System.currentTimeMillis();
			editor.putLong("date_firstlaunch", date_firstLaunch);
		}

		// Wait at least n days before opening
		if (launch_count >= pLaunchesUntilPrompt) {
			if (System.currentTimeMillis() >= date_firstLaunch
					+ (minutesUntilPrompt * 60 * 1000)) {
				showRateDialog(ctx, editor, pAppName, pPckgName, pIcon);
			}
		}

		editor.commit();
	}

	public static void gotoPlaystoreAndMarkAsRated(final Context ctx) {
		SharedPreferences prefs = ctx.getSharedPreferences(APPRATER, 0);
		SharedPreferences.Editor editor = prefs.edit();

		gotoPlaystore(ctx, ctx.getPackageName());

		if (editor != null) {
			editor.putBoolean(DONTSHOWAGAIN, true);
			editor.commit();
		}
	}

	public static void gotoPlaystore(final Context ctx, final String pPckgName) {
		Intent intent = new Intent(
				Intent.ACTION_VIEW, Uri
						.parse("market://details?id="
								+ pPckgName));
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		ctx.startActivity(intent);
	}

	protected static void showRateDialog(final Context ctx,
			final SharedPreferences.Editor editor,
			final String pPleaseRateText, final String pPckgName, final Drawable pIcon) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

		builder.setTitle("Can you rate it, please?")
				.setMessage(pPleaseRateText)
				.setCancelable(false)
				.setIcon(pIcon)
				.setPositiveButton("Rate it now!",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								gotoPlaystore(ctx, pPckgName);
								if (editor != null) {
									editor.putBoolean(DONTSHOWAGAIN, true);
									editor.commit();
								}
								dialog.dismiss();
							}
						})
				.setNeutralButton("Later",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							}
						})
				.setNegativeButton("No, thanks",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								editor.putBoolean(DONTSHOWAGAIN, true);
								editor.commit();
							}
						});

		final Dialog dialog = builder.create();
		dialog.show();
	}
}