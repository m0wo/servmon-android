package pmt.servermon_android;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.http.ParseException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private String mToken;
	
	private void checkAuth(){
		SharedPreferences sharedpreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
		if (sharedpreferences.getString("token", null) == null){
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		}else{
			mToken = sharedpreferences.getString("token", null);
			//populate listview etc.
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		checkAuth();
		ApiHelper.getUserServers(mToken);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
