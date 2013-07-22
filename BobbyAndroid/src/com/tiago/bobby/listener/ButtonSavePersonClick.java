/**
 * 
 */
package com.tiago.bobby.listener;

import java.util.HashMap;

import org.json.JSONObject;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.tiago.bobby.MainActivity;
import com.tiago.bobby.tasks.AddPersonTask;

/**
 * @author tfr_souza
 *
 */
public class ButtonSavePersonClick implements OnClickListener {
	
	private EditText mEditID;
	private EditText mEditName;

	public ButtonSavePersonClick(EditText editID, EditText editName) {
		mEditID = editID;
		mEditName = editName;
	}
	
	@Override
	public void onClick(View v) {
		try {
			if (MainActivity.checkInternetConnection(v.getContext())) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("name", mEditName.getText().toString());
				map.put("facebook_id", mEditID.getText().toString());
				
				JSONObject person = new JSONObject(map);
				
				AddPersonTask task = new AddPersonTask();
				String response = task.execute(person).get();
				
				mEditID.setText(null);
				mEditName.setText(null);
				
				Toast.makeText(v.getContext(), response, Toast.LENGTH_LONG).show();
			} else {
				MainActivity.noConnection(v.getContext());
			}
		} catch (Exception je) {
			Toast.makeText(v.getContext(), je.getLocalizedMessage(), Toast.LENGTH_LONG).show();
		}
	}

}
