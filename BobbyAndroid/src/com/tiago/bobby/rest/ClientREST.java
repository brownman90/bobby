/**
 * 
 */
package com.tiago.bobby.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

/**
 * @author tfr_souza
 *
 */
public final class ClientREST {
	
	public static String serviceAddPerson(JSONObject person) throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost(URLS.ADD_PERSON);
		
		StringEntity entity = new StringEntity(person.toString());
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		
		post.setEntity(entity);
		
		HttpEntity response = httpclient.execute(post).getEntity();
		
		if (response.getContentType().getValue().equalsIgnoreCase("text/plain")) {
			BufferedReader bufferResponse = new BufferedReader(new InputStreamReader(response.getContent()));
			String responseContent = bufferResponse.readLine();
			
			if (responseContent.contains("201")) {
				bufferResponse.close();
				return "pessoa adicionada";
			} else {
				bufferResponse.close();
				return responseContent.replace("HTTP", "");
			}
		} else {
			return "erro no servidor";
		}		
	}
	
	public static String serviceAddFriend(String id, JSONObject friend) throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost(URLS.ADD_FRIEND.replace("{id}", id));
		
		StringEntity entity = new StringEntity(friend.toString());
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		
		post.setEntity(entity);
		
		HttpEntity response = httpclient.execute(post).getEntity();
		
		if (response.getContentType().getValue().equalsIgnoreCase("text/plain")) {
			BufferedReader bufferResponse = new BufferedReader(new InputStreamReader(response.getContent()));
			String responseContent = bufferResponse.readLine();
			
			if (responseContent.contains("201")) {
				bufferResponse.close();
				return "amigo adicionado";
			} else {
				bufferResponse.close();
				return responseContent.replace("HTTP", "");
			}
		} else {
			return "erro no servidor";
		}
	}
	
	public static String serviceRemove(String id) throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet get = new HttpGet(URLS.REMOVE.replace("{id}", id));
		
		HttpEntity response = httpclient.execute(get).getEntity();
		
		if (response.getContentType().getValue().equalsIgnoreCase("text/plain")) {
			BufferedReader bufferResponse = new BufferedReader(new InputStreamReader(response.getContent()));
			String responseContent = bufferResponse.readLine();
			
			if (responseContent.contains("201")) {
				bufferResponse.close();
				return "pessoa removida";
			} else {
				bufferResponse.close();
				return responseContent.replace("HTTP", "");
			}
		} else {
			return "erro no servidor";
		}
	}
	
	public static String serviceListFriends(String id) throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet get = new HttpGet(URLS.LIST_FRIENDS.replace("{id}", id));
		
		HttpEntity response = httpclient.execute(get).getEntity();
		
		if (response.getContentType().getValue().equalsIgnoreCase("application/json")) {
			String responseContent = convertStreamToString(response.getContent());
			return responseContent;
		} else {
			return "erro no servidor";
		}
	}
	
	public static String serviceRecommendations(String id) throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet get = new HttpGet(URLS.RECOMMENDATIONS.replace("{id}", id));
		
		HttpEntity response = httpclient.execute(get).getEntity();
		
		if (response.getContentType().getValue().equalsIgnoreCase("application/json")) {
			String responseContent = convertStreamToString(response.getContent());
			return responseContent;
		} else {
			return "erro no servidor";
		}
	}
	
	private static String convertStreamToString(InputStream stream) throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
		StringBuilder toString = new StringBuilder();
		String line = null;
		
		while ((line = buffer.readLine()) != null) {
			toString.append(line);
		}
		
		return toString.toString();
	}

}
