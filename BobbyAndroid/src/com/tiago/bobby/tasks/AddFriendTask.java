/**
 * 
 */
package com.tiago.bobby.tasks;

import org.json.JSONObject;

import android.os.AsyncTask;

import com.tiago.bobby.rest.ClientREST;

/**
 * @author tfr_souza
 *
 */
public class AddFriendTask extends AsyncTask<JSONObject, Void, String> {

	@Override
	protected String doInBackground(JSONObject... params) {
		try {
			String id = params[0].getString("person_id");
			params[0].remove("person_id");
			String response = ClientREST.serviceAddFriend(id, params[0]);
			return response;
		} catch (Exception je) {
			return je.getLocalizedMessage();
		}
	}

}
