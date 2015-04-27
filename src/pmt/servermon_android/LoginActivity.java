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

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

@SuppressWarnings("deprecation")
public class LoginActivity extends Activity {
	
	public void loginButton(View v){
		String email = ((EditText) findViewById(R.id.etEmail)).getEditableText().toString();
		String password = ((EditText) findViewById(R.id.etPassword)).getEditableText().toString();
		login(email, password);
	}
	
	public void login(String email, String password){
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
	    			
	    			Log.d("Response Test", responseBody);
	    			
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		return null;
	    	}
			
		}
		
		AuthTask auth = new AuthTask();
		auth.execute(email, password);
	}
	
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
