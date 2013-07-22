package com.tiago.bobby;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;

import com.tiago.bobby.listener.ButtonListRecomClick;

public class RecommendationsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommendations);
		
		EditText personID = (EditText) findViewById(R.id.edtTxtIdRecom);
		ListView listViewRecom = (ListView) findViewById(R.id.listViewRecom);
		
		findViewById(R.id.btnListRecom).setOnClickListener(new ButtonListRecomClick(personID, listViewRecom));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recommendations, menu);
		return true;
	}

}
