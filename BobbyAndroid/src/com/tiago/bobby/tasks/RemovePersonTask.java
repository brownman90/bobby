/**
 * 
 */
package com.tiago.bobby.tasks;

import com.tiago.bobby.rest.ClientREST;

import android.os.AsyncTask;

/**
 * @author tfr_souza
 *
 */
public class RemovePersonTask extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... params) {
		try {
			String response = ClientREST.serviceRemove(params[0]);
			return response;
		} catch (Exception e) {
			return e.getLocalizedMessage();
		}
	}

}
