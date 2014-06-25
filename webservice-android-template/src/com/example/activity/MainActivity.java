package com.example.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.R;
import com.example.util.Constants;
import com.example.wservice.io.CommStatus;
import com.example.wservice.io.RequestType;
import com.example.wservice.io.data.ServiceResponseData;
import com.example.wservice.io.ws.request.LoginRequestData;
import com.example.wservice.io.ws.response.BaseResponseData;

/**
 * @author 321930
 * 
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {

	private String userId = "bip";
	private String password = "rrb6165";
	private String product = "bip";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);

	}

	protected void showMessageDialogOk(final String message,
			final DialogInterface.OnClickListener okButtonClickListener) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				new AlertDialog.Builder(MainActivity.this).setTitle("Gazelle")
						.setMessage(message)
						.setPositiveButton("Ok", okButtonClickListener).show();
			}
		});

	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub

		switch (view.getId()) {
		case R.id.button1:
			doServiceCall(new LoginRequestData(RequestType.POST,
					Constants.HTTP_COMM_CODE, userId, password, product), this);

			break;
		}

	}

	@Override
	public void onSuccessResponse(CommStatus commStatus,
			BaseResponseData serviceResponseData) {
		if (commStatus.isCommunicationSuccess()) {
			hideProgress();

		}
	}

}