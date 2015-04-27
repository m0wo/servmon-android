package pmt.servermon_android;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;

public class ApiHelper {
	
	public static String mToken = null;
	
	public static String login(String email, String password) throws InterruptedException, ExecutionException{
		final class AuthTask extends AsyncTask<String, Integer, HttpResponse>{
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost("http://student20265.201415.uk/pmt/api/auth/user");
			HttpResponse response;
			JSONObject userJson = new JSONObject();
	    	@Override
	    	protected HttpResponse doInBackground(String... params) {
	    		
	    		try {

	    			userJson.put("email", params[0]);
	    			userJson.put("password", params[1]);
	    			
	                post.setEntity(new StringEntity(userJson.toString()));
	    		} catch (UnsupportedEncodingException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		try {
	    			response = httpClient.execute(post);	
	    			

	    			
	    			
	    			//all for getting server stuff
	    			
	    			/*HttpPost userServers = new HttpPost("http://student20265.201415.uk/pmt/api/user/server/");
	    			userServers.setEntity(new StringEntity(token.toString()));
	    			response = httpClient.execute(userServers);
	    			responseBody = EntityUtils.toString(response.getEntity());*/
	    			
	    			
	    			
	    			
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		return response;
	    	}    	
	    	
			
		}
		
		AuthTask auth = new AuthTask();
		HttpResponse response = auth.execute(email, password).get();
		
		
		String responseBody = null;
		String tokenString = null;
		
		try {
			responseBody = EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			JSONObject token = new JSONObject(responseBody);
			tokenString = token.get("token").toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tokenString;
		
	}
}
