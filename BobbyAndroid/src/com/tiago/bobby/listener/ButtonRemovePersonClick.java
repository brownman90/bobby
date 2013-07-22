/**
 * 
 */
package com.tiago.bobby.listener;

import com.tiago.bobby.MainActivity;
import com.tiago.bobby.tasks.RemovePersonTask;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author tfr_souza
 *
 */
public class ButtonRemovePersonClick implements OnClickListener {
	
	private EditText mPersonId;
	
	public ButtonRemovePersonClick(EditText personId) {
		mPersonId = personId;
	}

	@Override
	public void onClick(View v) {
		try {
			if (MainActivity.checkInternetConnection(v.getContext())) {
				String personId = mPersonId.getText().toString();
				
				RemovePersonTask task = new RemovePersonTask();
				String response = task.execute(personId).get();
				
				mPersonId.setText(null);
				
				Toast.makeText(v.getContext(), response, Toast.LENGTH_LONG).show();
			} else {
				MainActivity.noConnection(v.getContext());
			}
		} catch (Exception e) {
			Toast.makeText(v.getContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
		}
	}

}
