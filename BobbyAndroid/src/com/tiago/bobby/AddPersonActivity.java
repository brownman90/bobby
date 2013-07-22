package com.tiago.bobby;

import com.tiago.bobby.listener.ButtonSavePersonClick;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

public class AddPersonActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_person);
		EditText editID = (EditText) findViewById(R.id.edtTxtIdPerson);
		EditText editName = (EditText) findViewById(R.id.edtTxtNamePerson);
		findViewById(R.id.btnSavePerson).setOnClickListener(new ButtonSavePersonClick(editID, editName));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_person, menu);
		return true;
	}

}
