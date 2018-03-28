package android.gw.com.remotecontroller.adapter;

import android.content.Context;
import android.gw.com.remotecontroller.R;
import android.gw.com.remotecontroller.models.Port;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PortAddapter extends BaseAdapter {

    private List<Port> ports;
    private LayoutInflater inflater;

    public PortAddapter(Context context,List<Port> ports) {
        this.ports = ports;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ports.size();
    }

    @Override
    public Object getItem(int i) {
        return ports.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View thisView = view;
        if(thisView == null){
            view = inflater.inflate(R.layout.port_item_list,viewGroup,false);
        }
        Port port = getModel(i);
        TextView type  = (TextView)view.findViewById(R.id.list_item_type);
        TextView status  = (TextView)view.findViewById(R.id.list_item_status);
        TextView id  = (TextView)view.findViewById(R.id.list_item_id);
        status.setText(port.getStatus().getDesc());
        type.setText(port.getType().getDesc());
        id.setText(String.valueOf(port.getId()));
        if(port.isActive()){
            id.setBackgroundResource(R.drawable.background_on);
        }else{
            id.setBackgroundResource(R.drawable.background_off);
        }
        return view;
    }
    public Port getModel(int position){
        return (Port)getItem(position);
    }
}
