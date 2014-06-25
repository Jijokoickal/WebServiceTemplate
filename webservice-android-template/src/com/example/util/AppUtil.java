package com.example.util;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public class AppUtil {

	public static void hideKeyBoard(final Activity activity) {
		if (activity == null)
			return;
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				InputMethodManager mgr = (InputMethodManager) activity
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				mgr.hideSoftInputFromWindow(activity.getWindow().getDecorView()
						.getWindowToken(), 0);

			}
		});
	}
}
