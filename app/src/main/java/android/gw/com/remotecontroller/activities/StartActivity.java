package android.gw.com.remotecontroller.activities;

import android.content.Intent;
import android.gw.com.remotecontroller.R;
import android.gw.com.remotecontroller.models.Responce;
import android.gw.com.remotecontroller.task.ItemExecute;
import android.gw.com.remotecontroller.utils.RecyclerClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class StartActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private TextView temp;
    private TextView hur;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorScheme(R.color.accent);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        drawDrawer();
        temp = (TextView) findViewById(R.id.temp);
        hur = (TextView) findViewById(R.id.hur);
        recyclerView = (RecyclerView) findViewById(R.id.itemRecycler);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(false);
        recyclerView.setNestedScrollingEnabled(true);
        new ItemExecute(recyclerView,this,temp,hur).execute("http://192.168.0.141");
        recyclerView.addOnItemTouchListener(
                new RecyclerClickListener(this, new RecyclerClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        new Power().execute("http://192.168.0.141/$"+(position+1));
                    }
                }));
    }

    @Override
    public void onRefresh() {
        new Power().execute("http://192.168.0.141/$9");
        new ItemExecute(recyclerView,StartActivity.this,temp,hur).execute("http://192.168.0.141");
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }

    public class Power extends AsyncTask<String,Void,Responce>{
        @Override
        protected Responce doInBackground(String... params) {
            Responce responce = null;
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                responce = restTemplate.getForObject(params[0], Responce.class);
            }catch (RuntimeException ex){
            }finally {
                return responce;
            }
        }

        @Override
        protected void onPostExecute(Responce responce) {
            if(responce==null){
                Toast.makeText(StartActivity.this,"Ошибка",Toast.LENGTH_SHORT).show();
                return;
            }
            super.onPostExecute(responce);
            new ItemExecute(recyclerView,StartActivity.this,temp,hur).execute("http://192.168.0.141");
        }
    }

    public void drawDrawer(){
        final Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withIdentifier(1).withName(R.string.menu2),
                        new SecondaryDrawerItem().withIdentifier(2).withName(R.string.menu3)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position){
                            case 1:{
                                startActivity(new Intent(StartActivity.this, About.class));
                                break;
                            }
                            case 2:{
                                System.exit(0);
                            }
                            finish();
                        }
                        return true;
                    }
                })
                .withActionBarDrawerToggle(false)
                .build();
    }

}
