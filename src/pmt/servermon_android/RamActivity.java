package pmt.servermon_android;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;

import server_classes.Server;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class RamActivity extends Activity {

	private Button findMagicBtn;
	private Server mServer;
	private JSONArray ramArray;
	private LineGraphSeries<DataPoint> mSeries;

	private BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			try {
				ramArray = new JSONArray(intent.getStringExtra("ramHistory"));
				updateGraph();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	};

	private void initUpdateService() {
		Intent i = new Intent(this, RamUpdateService.class);
		i.putExtra("id", mServer.getServerId());

		startService(i);
	}

	public void initGraph() {
		GraphView graph = (GraphView) findViewById(R.id.cpuGraph);
		LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(
				new DataPoint[] {

				new DataPoint(0, 1), new DataPoint(1, 5), new DataPoint(2, 3),
						new DataPoint(3, 2), new DataPoint(4, 6) });
		graph.addSeries(series);
	}

	public void updateGraph() {
		GraphView graph = (GraphView) findViewById(R.id.cpuGraph);

		ArrayList<DataPoint> ramData = new ArrayList<DataPoint>();

		for (int i = 0; i < ramArray.length(); i++) {
			try {

				Date tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(ramArray.getJSONObject(i).get("timestamp")
								.toString());
				Long tempLong = Long.parseLong(ramArray.getJSONObject(i)
						.get("used_ram").toString());
				Log.d("Date", tempDate.toString());
				Log.d("Ram", tempLong.toString());

				ramData.add(new DataPoint(tempDate, tempLong));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(
				ramData.toArray(new DataPoint[ramData.size()]));
		// mSeries.resetData(ramData.toArray(new DataPoint[ramData.size()]));
		graph.addSeries(series);

		graph.getGridLabelRenderer().setLabelFormatter(
				new DateAsXAxisLabelFormatter(this));
		graph.getGridLabelRenderer().setNumHorizontalLabels(ramArray.length()); // only 4
																// because of
																// the space

		try {
			graph.getViewport().setMinX(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.parse(ramArray.getJSONObject(0).get("timestamp").toString()).getTime());
			
			
			graph.getViewport().setMaxX(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.parse(ramArray.getJSONObject(ramArray.length() - 1).get("timestamp").toString()).getTime());
			
		} catch (ParseException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

		graph.getViewport().setXAxisBoundsManual(true);
	}

	public void setRamButton() {
		findMagicBtn = (Button) findViewById(R.id.magic_btn);
		findMagicBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LinearLayout findMagicLl = (LinearLayout) findViewById(R.id.magic_layout);
				if (findMagicLl.getVisibility() == View.VISIBLE) {
					findMagicLl.setVisibility(View.GONE);
				} else {
					findMagicLl.setVisibility(View.VISIBLE);
				}
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ram);
		Intent i = getIntent();
		mServer = i.getParcelableExtra("server");
		registerReceiver(receiver, new IntentFilter("pmt.servermon_android"));
		mSeries = new LineGraphSeries<>();
		initUpdateService();
		initGraph();
		setRamButton();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ram, menu);
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
