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
    private String mServerId;
    private ApiHelper mHelper;
    
    private Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            if (serviceStopped == false)
            {
                Intent intent = new Intent("pmt.servermon_android.updateserver");
                intent.putExtra("ram", mHelper.getRam(mServerId));
                intent.putExtra("cpu", mHelper.getCpu(mServerId));
                intent.putParcelableArrayListExtra("disk", mHelper.getDisks(mServerId));
                intent.putExtra("network", mHelper.getNetwork(mServerId));
                sendBroadcast(intent);
                
            }
            queueRunnable();
        }
    };
    
    private void queueRunnable() {
        mHandler.postDelayed(updateRunnable, 1000);
    }
    
	@Override
	public void onCreate(){
		mHelper = new ApiHelper();
		mHandler = new Handler();
		queueRunnable();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO run api calls
		Log.d("Service", "onStart");
		mServerId = intent.getStringExtra("id");
		
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO for communication return IBinder implementation
		return null;
	}

}
