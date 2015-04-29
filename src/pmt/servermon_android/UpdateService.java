package pmt.servermon_android;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class UpdateService extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO run api calls
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO for communication return IBinder implementation
		return null;
	}

}
