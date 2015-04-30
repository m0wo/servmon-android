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

public class RamActivity extends Activity {

	
	Button findMagicBtn;
	

	public void initGraph(){
		GraphView graph = (GraphView) findViewById(R.id.cpuGraph);
		LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
		          new DataPoint(0, 1),
		          new DataPoint(1, 5),
		          new DataPoint(2, 3),
		          new DataPoint(3, 2),
		          new DataPoint(4, 6)
		});
		graph.addSeries(series);
	}
	
	public void setRamButton(){
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
