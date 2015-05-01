package pmt.servermon_android;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import server_classes.Server;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class AlertService extends Service{
	

	private Handler mHandler;
    private boolean serviceStopped;
	private ArrayList<Server> mUserServers;
	private boolean alert = false;
	private ApiHelper mHelper;
	
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
	
    private int averageRam(JSONArray ram){
    	
    	int averageSize = 8;
    	
    	if(ram == null){
    		return 0;
    	}
    	
    	int average = 0;
    	for(int i = 0; i < averageSize; i++){
    		try {
				average += Integer.parseInt(ram.getJSONObject(i).get("used_ram").toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	average /= averageSize;
    	
    	return average;
    }
    
    private int averageCpu(String name, JSONArray cpu){
    	if(cpu == null){
    		return 0;
    	}
    	int average = 0;
    	for(int i = 0; i < 4; i++){
    		try {
				int temp = Integer.parseInt(cpu.getJSONObject(i).get("cpu_usage_percentage").toString());

				average += temp;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	average /= 4;
    	

		if (average > 80){
			if (alert == false){
				warnCpu(name);
			}
		}

    	
    	return average;
    }
    
    private void warnCpu(String name){
    	
    	NotificationCompat.Builder mBuilder =
    		    new NotificationCompat.Builder(this)
    		    .setSmallIcon(R.drawable.heart_monitor_white)
    		    .setContentTitle("Danger")
    		    .setDefaults(Notification.DEFAULT_SOUND)
    		    .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
    		    .setContentText(name + " CPU Usage High!");
    	
    	// Sets an ID for the notification
    	int mNotificationId = 001;
    	// Gets an instance of the NotificationManager service
    	NotificationManager mNotifyMgr = 
    	        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    	// Builds the notification and issues it.
    	mNotifyMgr.notify(mNotificationId, mBuilder.build());
    	alert = true;
    }
    
	private void scanServers(){
		for (Server server : mUserServers) {
			//ApiHelper.getCpuHistory(id)
			//ApiHelper.getCpuHistory(server.getServerId().toString());
			Log.d("CPU average", "" + averageCpu(server.getServerName(), mHelper.getCpuHistoryArray(server.getServerId().toString())));
			//Log.d("RAM average", "" + averageRam(ApiHelper.getRamHistory(server.getServerId().toString())));
		}
	}
	
    private void queueRunnable() {
        mHandler.postDelayed(updateRunnable, 5000);
    }
    
	@Override
	public void onCreate(){
		mHelper = new ApiHelper();
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
