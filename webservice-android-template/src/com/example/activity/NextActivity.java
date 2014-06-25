package com.example.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author 321930
 * 
 */

public class NextActivity extends Activity {
	private TextView content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		content = new TextView(this);
		content.setText("NextActivity started....");

		setContentView(content);
	}
}
