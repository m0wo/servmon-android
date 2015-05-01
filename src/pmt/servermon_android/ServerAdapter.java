package pmt.servermon_android;

import java.util.ArrayList;
import java.util.List;

import server_classes.Server;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//Extremely basic adapter for server objects, can be extended easily
public class ServerAdapter extends ArrayAdapter<Server>{

	public ServerAdapter(Context context,  ArrayList<Server> servers) {
		super(context,R.layout.server_row, servers);
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	Server server = getItem(position);
    	
        if(convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.server_row, parent, false);
        }
        TextView tvName = (TextView)convertView.findViewById(R.id.tvServername);
        
        tvName.setText(server.getServerName());
        
        return convertView;
    }

}
