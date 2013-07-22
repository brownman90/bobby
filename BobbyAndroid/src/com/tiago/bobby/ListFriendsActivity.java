package com.tiago.bobby;

import com.tiago.bobby.listener.ButtonListFriendsClick;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;

public class ListFriendsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_friends);
		
		EditText personID = (EditText) findViewById(R.id.edtTxtIdListFriends);
		ListView listViewFriends = (ListView) findViewById(R.id.listViewFriends);
		
		findViewById(R.id.btnListFriends).setOnClickListener(new ButtonListFriendsClick(personID, listViewFriends));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_friends, menu);
		return true;
	}

}
