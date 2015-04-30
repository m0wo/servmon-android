package pmt.servermon_android;

import java.util.ArrayList;

import org.json.JSONObject;

import server_classes.Server;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class AlertService extends Service{
	

	private Handler mHandler;
    private boolean serviceStopped;
	private ArrayList<Server> mUserServers;
	
    private Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            if(serviceStopped == false)
            {
            	scanServers();
            }
            queueRunnable();
        }
    };
	
	private void scanServers(){
		for (Server server : mUserServers) {
			//ApiHelper.getCpuHistory(id)
			ApiHelper.getCpuHistory(server.getServerId().toString());
		}
	}
	
    private void queueRunnable() {
        mHandler.postDelayed(updateRunnable, 5000);
    }
    
	@Override
	public void onCreate(){
		
		mHandler = new Handler();
		queueRunnable();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		mUserServers = intent.getParcelableArrayListExtra("servers");
		
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
