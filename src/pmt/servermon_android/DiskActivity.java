package pmt.servermon_android;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class DiskActivity extends Activity {

	Button remainingMagicBtn;
	Button readMagicBtn;
	Button writeMagicBtn;
	GraphView mGraph;
	
	public void initGraph(GraphView graph){
		
		LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
		          new DataPoint(0, 1),
		          new DataPoint(1, 5),
		          new DataPoint(2, 3),
		          new DataPoint(3, 2),
		          new DataPoint(4, 6)
		});
		graph.addSeries(series);
	}
	
	public void setRemainingMagicButton(){
		remainingMagicBtn = (Button) findViewById(R.id.magic_btn_remaining);
		remainingMagicBtn.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            LinearLayout findMagicLl = (LinearLayout) findViewById(R.id.magic_layout_remaining);
	            if (findMagicLl.getVisibility() == View.VISIBLE) {
	                findMagicLl.setVisibility(View.GONE);
	            } else {
	                findMagicLl.setVisibility(View.VISIBLE);
	            }
	        }
	    });
	}
	
	public void setReadMagicBtn(){
		readMagicBtn = (Button) findViewById(R.id.magic_btn_read);
		readMagicBtn.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            LinearLayout findMagicLl = (LinearLayout) findViewById(R.id.magic_layout_read);
	            if (findMagicLl.getVisibility() == View.VISIBLE) {
	                findMagicLl.setVisibility(View.GONE);
	            } else {
	                findMagicLl.setVisibility(View.VISIBLE);
	            }
	        }
	    });
	}
	
	public void setWriteMagicBtn(){
		readMagicBtn = (Button) findViewById(R.id.magic_btn_write);
		readMagicBtn.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            LinearLayout findMagicLl = (LinearLayout) findViewById(R.id.magic_layout_write);
	            if (findMagicLl.getVisibility() == View.VISIBLE) {
	                findMagicLl.setVisibility(View.GONE);
	            } else {
	                findMagicLl.setVisibility(View.VISIBLE);
	            }
	        }
	    });
	}
	
	public void setupButtons(){
		setRemainingMagicButton();
		setReadMagicBtn();
		setWriteMagicBtn();
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disk);
		setupButtons();
		
		initGraph((GraphView) findViewById(R.id.remainingGraph));
		initGraph((GraphView) findViewById(R.id.readGraph));
		initGraph((GraphView) findViewById(R.id.writeGraph));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.disk, menu);
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
