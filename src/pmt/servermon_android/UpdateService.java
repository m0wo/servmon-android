package pmt.servermon_android;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class UpdateService extends Service {
	
	private Handler mHandler;
    private boolean serviceStopped;
    
    private Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            if (serviceStopped == false)
            {
            	
            	//api calls
                Intent intent = new Intent("pmt.servermon_android");
                sendBroadcast(intent);
                
                //createNotificationIcon();
            }
            queueRunnable();
        }
    };
    
    private void queueRunnable() {
        mHandler.postDelayed(updateRunnable, 5000);

    }
    
    public void createNotificationIcon()
    {
        Log.d("MyServiceNotifications", "Hello");
    }    
    
	@Override
	public void onCreate(){
		Log.d("Service", "onStart");
		
		mHandler = new Handler();
		queueRunnable();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO run api calls
		Log.d("Service", "onStart");
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO for communication return IBinder implementation
		return null;
	}

}
