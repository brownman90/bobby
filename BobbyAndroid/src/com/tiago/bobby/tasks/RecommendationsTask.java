/**
 * 
 */
package com.tiago.bobby.tasks;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.tiago.bobby.rest.ClientREST;
import com.tiago.bobby.types.Person;

/**
 * @author tfr_souza
 *
 */
public class RecommendationsTask extends AsyncTask<String, Void, List<Person>> {

	@Override
	protected List<Person> doInBackground(String... params) {
		try {
			String response = ClientREST.serviceRecommendations(params[0]);
			JSONObject friends = new JSONObject(response);
			List<Person> listFriends = new ArrayList<Person>();
			
			JSONArray allRecommendations = friends.optJSONArray("objects");
			
			if (allRecommendations == null) {
				JSONObject recom = friends.optJSONObject("objects");
				if (recom == null) return listFriends;
				Person p = new Person(Long.parseLong(recom.getString("facebook_id")), recom.getString("name"));
				listFriends.add(p);
			} else {
				int size = allRecommendations.length();
				
				JSONObject obj = null;
				Person p = null;
				
				for (int i = 0; i < size; i++) {
					obj = (JSONObject) allRecommendations.get(i);
					p = new Person(Long.parseLong(obj.getString("facebook_id")), obj.getString("name"));
					listFriends.add(p);
				}
			}
			
			return listFriends;
		} catch (Exception e) {
			Person p = new Person(-1, e.getLocalizedMessage());
			List<Person> erro = new ArrayList<Person>();
			erro.add(p);
			return erro;
		}
	}

}
