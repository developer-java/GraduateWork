package android.gw.com.remotecontroller.task;

import android.content.Context;
import android.gw.com.remotecontroller.adapter.ItemsAdapter;
import android.gw.com.remotecontroller.models.Item;
import android.gw.com.remotecontroller.models.Responce;
import android.gw.com.remotecontroller.models.TechType;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class ItemExecute extends AsyncTask<String, Void, Responce> {
    private RecyclerView recyclerView;
    private Context context;
    private List<Item> items = new ArrayList<>();
    private TextView temp;
    private TextView hur;
    public ItemExecute(RecyclerView recyclerView, Context context, TextView temp, TextView hur) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.temp = temp;
        this.hur = hur;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Responce responce) {
        super.onPostExecute(responce);
        if(responce!=null){
            for(int i=0;i<responce.getPorts().length;i++){
                Item item = new Item();
                item.setNumber(String.valueOf(i));
                item.setStatus(responce.getPorts()[i]);
                item.setConnected(responce.getStatus()[i]);
                temp.setText(responce.getInfo().getTmp()+" C");
                hur.setText(responce.getInfo().getHur()+" %");
                if(i==0){
                    item.setType(TechType.TV);
                }else if(i==1){
                    item.setType(TechType.COOL);
                }else{
                    item.setType(TechType.COFFE);
                }
                items.add(item);
            }
        }
        recyclerView.setAdapter(new ItemsAdapter(items));
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Responce doInBackground(String... strings) {
        Responce responce = null;
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            responce = restTemplate.getForObject(strings[0],Responce.class);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return responce;
    }
}
