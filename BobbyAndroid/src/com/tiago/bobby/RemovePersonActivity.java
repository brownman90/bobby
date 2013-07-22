package com.tiago.bobby;

import com.tiago.bobby.listener.ButtonRemovePersonClick;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

public class RemovePersonActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remove_person);
		
		EditText id = (EditText) findViewById(R.id.edtTxtIdRemove);
		findViewById(R.id.btnRemove).setOnClickListener(new ButtonRemovePersonClick(id));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.remove_person, menu);
		return true;
	}

}
