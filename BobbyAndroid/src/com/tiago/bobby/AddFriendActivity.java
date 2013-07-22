package com.tiago.bobby;

import com.tiago.bobby.listener.ButtonSaveFriendClick;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

public class AddFriendActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_friend);
		
		EditText personID = (EditText) findViewById(R.id.edtTxtPeIdFriend);
		EditText friendID = (EditText) findViewById(R.id.edtTxtFrIdFriend);
		
		findViewById(R.id.btnSaveFriend).setOnClickListener(new ButtonSaveFriendClick(personID, friendID));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_friend, menu);
		return true;
	}

}
