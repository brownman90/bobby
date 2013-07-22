/**
 * 
 */
package com.tiago.bobby.tasks;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;

import com.tiago.bobby.rest.ClientREST;

import android.os.AsyncTask;

/**
 * @author tfr_souza
 *
 */
public class AddPersonTask extends AsyncTask<JSONObject, Void, String> {

	@Override
	protected String doInBackground(JSONObject... params) {
		try {
			JSONObject person = params[0];
			String response = ClientREST.serviceAddPerson(person);
			return response;
		} catch (ClientProtocolException cpe) {
			return cpe.getLocalizedMessage();
		} catch (IOException ioe) {
			return ioe.getLocalizedMessage();
		}		
	}

}
