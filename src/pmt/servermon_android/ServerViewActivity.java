package pmt.servermon_android;

import pmt.servermon_android.ApiHelper.GetServer;
import server_classes.Server;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class ServerViewActivity extends Activity {

	private Server mServer;
	private String mToken;
	
	public String getToken(){
		SharedPreferences sharedpreferences = getSharedPreferences("prefs",
				Context.MODE_PRIVATE);
		
		return sharedpreferences.getString("token", null);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		Intent i = getIntent();
		mServer = i.getParcelableExtra("server");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_server_view);
		
		Log.d("Server", mServer.toString());
		Log.d("Server ID", mServer.getServerId());
		
		String token = getToken();
		String id = mServer.getServerId();
		
		Server s = ApiHelper.getServer(token, id);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.server_view, menu);
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
