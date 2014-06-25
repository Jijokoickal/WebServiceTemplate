package com.example.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

public class Logger {

	private static final boolean DEBUG = true;

	// Error
	public static void e(String tag, String msg) {
		if (DEBUG)
			android.util.Log.e(tag, msg);
	}

	public static void e(String TAG, int message) {
		e(TAG, TAG + ":" + message);
	}

	public static void e(String TAG, boolean message) {
		e(TAG, TAG + ":" + message);
	}

	// public static void e(String TAG, Object message) {
	// e(TAG, TAG + ":" + message);
	// }

	// Debug mode
	public static void d(String tag, String msg) {
		if (DEBUG)
			android.util.Log.d(tag, msg);
	}

	public static void d(String TAG, int message) {
		d(TAG, message);
	}

	public static void d(String TAG, boolean message) {
		d(TAG, message);
	}

	public static void writeToFile(String inStr) {
		if (DEBUG) {
			String path = Environment.getExternalStorageDirectory()
					+ "/log.txt";
			File file = new File(path);
			if (file.exists()) {
				file.delete();
			}
			try {
				file.createNewFile();
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(inStr);
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// public static void d(String TAG, Object message) {
	// d(TAG, message);
	// }

	// Verbose mode
	public static void v(String tag, String msg) {
		if (DEBUG)
			android.util.Log.v(tag, msg);
	}

	public static void v(String TAG, int message) {
		v(TAG, message);
	}

	public static void v(String TAG, boolean message) {
		v(TAG, message);
	}

	// public static void v(String TAG, Object message) {
	// v(TAG, message);
	// }

	public static void showShortToast(Context context, String mess) {
		showToast(context, mess, Toast.LENGTH_SHORT);
	}

	private static void showToast(Context context, String mess, int duration) {
		if (DEBUG)
			Toast.makeText(context, mess, duration).show();
	}

	public static void showLongToast(Context context, String mess) {

		showToast(context, mess, Toast.LENGTH_LONG);
	}

}
