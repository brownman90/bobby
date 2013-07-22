/**
 * 
 */
package com.tiago.bobby.listener;

import java.util.HashMap;

import org.json.JSONObject;

import com.tiago.bobby.MainActivity;
import com.tiago.bobby.tasks.AddFriendTask;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author tfr_souza
 *
 */
public class ButtonSaveFriendClick implements OnClickListener {

	private EditText mPersonID;
	private EditText mFriendID;

	public ButtonSaveFriendClick(EditText personID, EditText friendID) {
		mPersonID = personID;
		mFriendID = friendID;
	}
	
	@Override
	public void onClick(View v) {
		try {
			if (MainActivity.checkInternetConnection(v.getContext())) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("person_id", mPersonID.getText().toString());
				map.put("facebook_id", mFriendID.getText().toString());
				
				JSONObject friend = new JSONObject(map);
				
				AddFriendTask task = new AddFriendTask();
				String response = task.execute(friend).get();
				
				mPersonID.setText(null);
				mFriendID.setText(null);
				
				Toast.makeText(v.getContext(), response, Toast.LENGTH_LONG).show();
			} else {
				MainActivity.noConnection(v.getContext());
			}
		} catch (Exception e) {
			Toast.makeText(v.getContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
		}
	}

}
