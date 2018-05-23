package android.gw.com.remotecontroller.adapter;

import android.graphics.Color;
import android.gw.com.remotecontroller.R;
import android.gw.com.remotecontroller.models.Item;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.RecyclerViewHolder> {

    private List<Item> items;

    public ItemsAdapter(List<Item> items) {
        this.items = items;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title;
        private TextView status;
        private TextView description;
        private TableRow row;


        RecyclerViewHolder(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.item_image);
            title = (TextView) itemView.findViewById(R.id.item_title);
            status = (TextView) itemView.findViewById(R.id.item_status);
            description = (TextView) itemView.findViewById(R.id.item_description);
            row = (TableRow) itemView.findViewById(R.id.item_row);
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_view, viewGroup, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int i) {
        final Item item = items.get(i);
        viewHolder.title.setText(item.getType().getStrTitleId());
        viewHolder.image.setImageResource(item.getType().getImgId());
        viewHolder.status.setText(item.isConnected() ? R.string.connected:R.string.disconnected);
        if(item.isConnected()){
            viewHolder.status.setTextColor(Color.parseColor("#00FF00"));
        }else{
            viewHolder.status.setTextColor(Color.parseColor("#FF0000"));
        }
        viewHolder.description.setText(item.getType().getDescId());
        if(item.getStatus()){
            viewHolder.row.setBackgroundResource(R.drawable.background_on);
        }else{
            viewHolder.row.setBackgroundResource(R.drawable.background_off);
        }

    }
    @Override
    public int getItemCount() {
        return items.size();
    }
}
