package pmt.servermon_android;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import server_classes.Server;
import android.os.AsyncTask;
import android.util.Log;

public class ApiHelper {
	
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
	
	final class HttpGet extends AsyncTask<String, Integer, HttpResponse>{
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://student20265.201415.uk/pmt/api/user/server/");
		HttpResponse response;
		JSONObject tokenJson = new JSONObject();
		
    	@Override
    	protected HttpResponse doInBackground(String... params) {
    		
    		try {

    			tokenJson.put("token", params[0]);
    			
                post.setEntity(new StringEntity(tokenJson.toString()));
    		} catch (UnsupportedEncodingException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		try {
    			response = httpClient.execute(post);	
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		return response;
    	}    	
    	
		
	}
	
	public static Server jsonToServer(JSONObject json){
		Log.d("Server", json.toString());
		Server server = null;
		try {
			server = new Server(json.getString("server_id"), json.getString("server_name"), json.getString("operating_system"), json.getString("uptime"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d("Server object", server.toString());
		
		return server;
	}
	
	public static ArrayList<Server> getUserServers(String token){
		
		ArrayList<Server> serverList = new ArrayList<Server>();
		
		ApiHelper api = new ApiHelper();
		
		ApiHelper.HttpGet get = api.new HttpGet();	//what the fuck
		HttpResponse response = null;
		try {
			response = get.execute(token).get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String responseBody = null;
		try {
			responseBody = EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			JSONArray servers = new JSONArray(responseBody);
			
			for (int i = 0; i < servers.length(); i++) {
			    JSONObject server = servers.getJSONObject(i);
			    serverList.add(jsonToServer(server));
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.d("Servers:", responseBody);
		
		return serverList;
		
	}
	
	public static String login(String email, String password) throws InterruptedException, ExecutionException{
		
		ApiHelper api = new ApiHelper();
		
		ApiHelper.AuthTask auth = api.new AuthTask();	//what the fuck
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
