package pmt.servermon_android;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
		
		try {
			ApiHelper helper = new ApiHelper();
			String token = helper.login(email, password);
			
			SharedPreferences sharedpreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);	
			Editor editor = sharedpreferences.edit();
			editor.putString("token", token);
			editor.commit();
			
			Log.d("key stored:", sharedpreferences.getString("token", null));
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Log.d("Response Test", ApiHelper.mToken);

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
