package pmt.servermon_android;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import server_classes.Server;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private String mToken;	//token sent to api calls
	private ListView lvServers;	//listview of user's servers
	private ServerAdapter mAdapter;	//adapter to display server name

	//redirect to login if user token is not set in shared preferences
	private boolean checkAuth() {
		boolean result = false;
		SharedPreferences sharedpreferences = getSharedPreferences("prefs",
				Context.MODE_PRIVATE);

		if (sharedpreferences.getString("token", null) == null) {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		} else {
			mToken = sharedpreferences.getString("token", null);
			try {
				JSONObject objToken = new JSONObject();
				objToken.put("token", mToken);
				ApiHelper.mToken = objToken;
				result = true;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// populate listview etc.
		}

		return result;
	}
	//initialize background processing thread
	private void initAlertService(ArrayList<Server> servers) {
		Intent i = new Intent(this, AlertService.class);
		i.putParcelableArrayListExtra("servers", servers);

		startService(i);

	}
	//open the server page for the selected server
	private void openServerPage(Server s) {
		Intent intent = new Intent(this, ServerViewActivity.class);
		intent.putExtra("server", s);
		startActivity(intent);
	}

	//add onclicklisteners to each listview item
	private void setOnClick() {

		lvServers.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				final AdapterView<ServerAdapter> v = (AdapterView<ServerAdapter>) arg0;
				View view = v.getChildAt(arg2);

				Server s = v.getAdapter().getItem(arg2);

				TextView txt1 = (TextView) view.findViewById(R.id.tvServername);
				Log.d("serverObject", s.getServerName());
				Log.d("listView", txt1.getText().toString());
				openServerPage(s);
			}

		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (checkAuth()) {
			ApiHelper helper = new ApiHelper();
			ArrayList<Server> servers = helper.getUserServers(mToken);

			initAlertService(servers);

			lvServers = (ListView) findViewById(R.id.lvServers);

			mAdapter = new ServerAdapter(this, servers);

			lvServers.setAdapter(mAdapter);
			setOnClick();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	public void logOut() {
		this.getSharedPreferences("prefs", 0).edit().clear().commit();
		checkAuth();
		// Destroy key
		// Redirect to login activity
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} else if (id == R.id.action_logout) {
			logOut();
		}
		return super.onOptionsItemSelected(item);
	}
}
