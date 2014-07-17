package com.example.lesson_activitylift;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class OtherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.other, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

	@Override
	protected void onPause() {

		super.onPause();
	}

	@Override
	protected void onRestart() {

		super.onRestart();
	}

	@Override
	protected void onResume() {

		super.onResume();
	}

	@Override
	protected void onStart() {

		super.onStart();
	}

	@Override
	protected void onStop() {

		super.onStop();
	}

}
