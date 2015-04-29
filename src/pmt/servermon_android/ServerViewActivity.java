package pmt.servermon_android;

import java.util.ArrayList;

import server_classes.Cpu;
import server_classes.Disk;
import server_classes.Network;
import server_classes.Ram;
import server_classes.Server;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ServerViewActivity extends Activity {

	private Server mServer;
	private Ram mRam;
	private Cpu mCpu;
	private Disk mDisk;
	private Network mNet;
	
	private ArrayList<Disk> mDisks;
	private ArrayList<Cpu> mCpus;
	
	
	private String mToken;
	
	private TextView ramTv;
	private TextView cpuTv;
	private TextView diskTv;
	private TextView netTv;
	private TextView tvServerName;
	
	public String getToken(){
		SharedPreferences sharedpreferences = getSharedPreferences("prefs",
				Context.MODE_PRIVATE);
		
		return sharedpreferences.getString("token", null);
	}
	
	private void initView(){
		ramTv = (TextView) findViewById(R.id.tvRamInfo);
		cpuTv = (TextView) findViewById(R.id.tvCpuInfo);
		diskTv = (TextView) findViewById(R.id.tvDiskInfo);
		netTv = (TextView) findViewById(R.id.tvNetworkInfo);
		
		tvServerName = (TextView) findViewById(R.id.tvServerTitle);
		
		tvServerName.setText(mServer.getServerName());
	}
	
	private void updateDiskView(){
		
		StringBuilder sb = new StringBuilder();
		
		for(Disk d : mDisks){
			sb.append(d.toString());
			sb.append("\n");
		}
		diskTv.setText(sb.toString());
	}
	
	private void updateView(){
		ramTv.setText(mRam.toString());
		cpuTv.setText(mCpu.toString());
		netTv.setText(mNet.toString());
		updateDiskView();
	}
	
	private void updateStats(){
		mRam = ApiHelper.getRam(mServer.getServerId());
		mCpu = ApiHelper.getCpu(mServer.getServerId());
		mCpus = ApiHelper.getCpus(mServer.getServerId());
		//mDisk = ApiHelper.getDisk(mServer.getServerId());
		mDisks = ApiHelper.getDisks(mServer.getServerId());
		mNet = ApiHelper.getNetwork(mServer.getServerId());
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		Intent i = getIntent();
		mServer = i.getParcelableExtra("server");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_server_view);
		initView();
		
		Log.d("Server", mServer.toString());
		Log.d("Server ID", mServer.getServerId());
		setmToken(getToken());
		
		//Server s = ApiHelper.getServer(mToken, mServer.getServerId());
		

		updateStats();
		updateView();
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

	public Ram getmRam() {
		return mRam;
	}

	public void setmRam(Ram mRam) {
		this.mRam = mRam;
	}

	public String getmToken() {
		return mToken;
	}

	public void setmToken(String mToken) {
		this.mToken = mToken;
	}
	
}
