/**
 * 
 */
package com.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * @author 321930
 * 
 */
public class SplashActivity extends Activity {

	private static final int SPLASH_TIME = 1 * 1000;// 1 seconds

	/**
	 * 
	 */
	public SplashActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		new Handler().postDelayed(new Runnable() {

			public void run() {

				Intent intent = new Intent(SplashActivity.this,
						MainActivity.class);
				startActivity(intent);

				SplashActivity.this.finish();

				// overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

			}

		}, SPLASH_TIME);
	}

}
