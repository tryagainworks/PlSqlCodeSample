package com.pcs.plsqlcodesample;

import com.pcs.plsqlcodesample.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.view.KeyEvent;

public class ClsEula {
	private String EULA_PREFIX = "eula_";
	private Activity mActivity;

	public ClsEula(Activity context) {
		mActivity = context;
	}

	private PackageInfo getPackageInfo() {
		PackageInfo pi = null;
		try {
			pi = mActivity.getPackageManager().getPackageInfo(mActivity.getPackageName(),
					PackageManager.GET_ACTIVITIES);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return pi;
	}

	public void show() {
		PackageInfo versionInfo = getPackageInfo();
		// the eulaKey changes every time you increment the version number in
		// the AndroidManifest.xml
		final String eulaKey = EULA_PREFIX + versionInfo.versionCode;
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mActivity);
		/* Block begin : Remove this when releasing the app */
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean(eulaKey, false);
		editor.commit();
		/* Block end : Remove this when releasing the app */
		boolean hasBeenShown = prefs.getBoolean(eulaKey, false);
		if (hasBeenShown == false) {

			// Show the Eula
			String title = mActivity.getString(R.string.app_name) + " v" + versionInfo.versionName;

			// Includes the updates as well so users know what changed.
			String message = mActivity.getString(R.string.app_modification) + "\n\n"
					+ mActivity.getString(R.string.app_eula);

			AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

			builder.setOnKeyListener(new OnKeyListener() {
				@Override
				public boolean onKey(DialogInterface dialoginterface, int keyCode, KeyEvent event) {
					if ((keyCode == KeyEvent.KEYCODE_HOME)) {
						return false;
					} else {
						return true;
					}
				}
			});
			//builder.setCancelable(false);

			builder.setTitle(title)
			.setMessage(message)
			.setCancelable(false)
			/*
			.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    // Close the activity as they have declined the EULA
                    mActivity.finish();
                }
            })
            */
			.setPositiveButton("I Agree" /*android.R.string.ok*/, new Dialog.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialogInterface, int i) {
							// Mark this version as read.
							SharedPreferences.Editor editor = prefs.edit();
							editor.putBoolean(eulaKey, true);
							editor.commit();
							dialogInterface.dismiss();
						}
					}).setNegativeButton("Disagree"/*android.R.string.cancel*/, new Dialog.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// Close the activity as they have declined the EULA
							mActivity.finish();
						}

					});
			

			
			builder.create().show();
		}
	}
}
