package pmt.servermon_android;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class ApiHelper {
	
	public static void login(String email, String password){
		final class AuthTask extends AsyncTask<String, Integer, HttpResponse>{
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost("http://student20265.201415.uk/pmt/api/auth/user");
			
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
	    			HttpResponse response = httpClient.execute(post);	
	    			String responseBody = EntityUtils.toString(response.getEntity());
	    			
	    			JSONObject token = new JSONObject(responseBody);
	    			
	    			HttpPost userServers = new HttpPost("http://student20265.201415.uk/pmt/api/user/server/");
	    			userServers.setEntity(new StringEntity(token.toString()));
	    			response = httpClient.execute(userServers);
	    			responseBody = EntityUtils.toString(response.getEntity());
	    			
	    			Log.d("Response Test", responseBody);
	    			
	    			
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		return null;
	    	}
			
		}
		
		AuthTask auth = new AuthTask();
		auth.execute(email, password);
	}
}
