package pmt.servermon_android;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

import server_classes.Cpu;
import server_classes.Ram;
import server_classes.Server;
import android.os.AsyncTask;
import android.util.Log;

public class ApiHelper {
	
	public static JSONObject mToken;
	
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
	
	final class GetServer extends AsyncTask<String, Integer, HttpResponse>{
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = null;
		HttpResponse response;
		JSONObject tokenJson = new JSONObject();
		
    	@Override
    	protected HttpResponse doInBackground(String... params) {
    		
    		try {
    			Log.d("url", "http://student20265.201415.uk/pmt/api/user/server/" + params[1]);
    			tokenJson.put("token", params[0]);
    			post = new HttpPost("http://student20265.201415.uk/pmt/api/user/server/" + params[1]);
    			
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
	
	final class GetRam extends AsyncTask<String, Integer, HttpResponse>{

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = null;
		HttpResponse response = null;
		JSONObject tokenJson = new JSONObject();
		
		@Override
		protected HttpResponse doInBackground(String... params) {
			String url = "http://student20265.201415.uk/pmt/api/user/server/" + params[1] + "/ram/";
			try {
				tokenJson.put("token", params[0]);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			post = new HttpPost(url);
			try {
				post.setEntity(new StringEntity(tokenJson.toString()));
			} catch (UnsupportedEncodingException e) {
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
	
	final class GenericCall extends AsyncTask<String, Integer, HttpResponse>{
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = null;
		HttpResponse response;
		
		
    	@Override
    	protected HttpResponse doInBackground(String... params) {
    		post = new HttpPost(params[0]);
    		try {
    			
                post.setEntity(new StringEntity(mToken.toString()));
    		} catch (UnsupportedEncodingException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
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
	
	public static Cpu getCpu(String id){

		String url = "http://student20265.201415.uk/pmt/api/user/server/" + id + "/cpu/";
		ApiHelper api = new ApiHelper();
		ApiHelper.GenericCall get = api.new GenericCall();
		
		HttpResponse response;
		try {
			response = get.execute(url).get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
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
	
	public static Server getServer(String token, String id){
		//Server server = null;
		
		ApiHelper api = new ApiHelper();
		
		ApiHelper.GetServer get = api.new GetServer();	//what the fuck
		try {
			
			HttpResponse response = get.execute(token,id).get();
			String responseBody = EntityUtils.toString(response.getEntity());
			
			Log.d("Servers!", responseBody);
			Server s =  jsonToServer(new JSONObject(responseBody));
			Log.d("Server Object!", s.toString());
			//Log.d("Server result",EntityUtils.toString(get.execute(token, id).get().getEntity()));
			return s;
		} catch (ParseException | JSONException | IOException
				| InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Ram getRam(String token, String id){
		ApiHelper api = new ApiHelper();
		ApiHelper.GetRam ramGet = api.new GetRam();
		HttpResponse response = null;
		Ram ram = new Ram();
		
		try {
			response = ramGet.execute(token, id).get();
			String responseBody = EntityUtils.toString(response.getEntity());
			
			Log.d("Ram Response", responseBody);
			
			
			

			JSONObject ramObj = new JSONObject(responseBody);
			ram.setTotalRam(ramObj.getString("total_ram"));
			ram.setUsedRam(ramObj.getString("used_ram_mb"));			
			
			
		} catch (InterruptedException | ExecutionException | ParseException | IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ram;
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
