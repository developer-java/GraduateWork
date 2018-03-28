package android.gw.com.remotecontroller;

import android.content.Context;
import android.gw.com.remotecontroller.models.Greeting;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener{
    private TextView tmp,hum;
    private String _URL = "http://192.168.0.141/";
    public Greeting _GREETING;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ImageButton b1,b2,b3,b4,b5,b6,b7,b8;
    private ScrollView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hum = (TextView) findViewById(R.id.hum);
        tmp = (TextView) findViewById(R.id.tmp);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorScheme(R.color.blue, R.color.colorPrimary, R.color.blue, R.color.purpur);
        b1 = (ImageButton)findViewById(R.id.one);
        b2 = (ImageButton)findViewById(R.id.two);
        b3 = (ImageButton)findViewById(R.id.three);
        b4 = (ImageButton)findViewById(R.id.four);
        b5 = (ImageButton)findViewById(R.id.five);
        b6 = (ImageButton)findViewById(R.id.six);
        b7 = (ImageButton)findViewById(R.id.seven);
        b8 = (ImageButton)findViewById(R.id.b8);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        content = (ScrollView)findViewById(R.id.content);
        new Sheduler(this).execute(_URL);
    }

    @Override
    public void onRefresh() {
        new Sheduler(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,_URL);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.one:{
                new HttpRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,_URL+"$1");
                break;
            }
            case R.id.two:{
                new HttpRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,_URL+"$2");
                break;
            }
            case R.id.three:{
                new HttpRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,_URL+"$3");
                break;
            }
            case R.id.four:{
                new HttpRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,_URL+"$4");
                break;
            }
            case R.id.five:{
                new HttpRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,_URL+"$5");
                break;
            }case R.id.six:{
                new HttpRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,_URL+"$6");
                break;
            }
            case R.id.seven:{
                new HttpRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,_URL+"$7");
                break;
            }
            case R.id.b8:{
                new HttpRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,_URL+"$8");
                break;
            }
            default:{
                break;
            }

        }
    }

    public class Sheduler extends AsyncTask<String,Greeting,Void>{
        private Context context;
        public Sheduler(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            content.setVisibility(View.INVISIBLE);
            mSwipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mSwipeRefreshLayout.setRefreshing(false);
            content.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Greeting... list) {
          _GREETING = list[0];
          tmp.setText(_GREETING.getTmp()+"'C");
          hum.setText(_GREETING.getHum()+"%");
        }

        @Override
        protected Void doInBackground(String... param) {
            try{
                final String url = param[0];
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Greeting greeting = restTemplate.getForObject(url, Greeting.class);
                publishProgress(greeting);
            }catch (RuntimeException ex){
                Toast.makeText(context,"Ошибка",Toast.LENGTH_SHORT).show();
            }finally {
                return null;
            }
        }

    }

    private class HttpRequestTask extends AsyncTask<String, Void, Greeting> {
        @Override
        protected Greeting doInBackground(String... params) {
            Greeting greeting = null;
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                greeting = restTemplate.getForObject(params[0], Greeting.class);
            }catch (RuntimeException ex){
            }finally {
                return greeting;
            }
        }

        @Override
        protected void onPostExecute(Greeting greeting) {
            if(greeting==null){
                Toast.makeText(MainActivity.this,"Ошибка",Toast.LENGTH_SHORT).show();
                return;
            }
            super.onPostExecute(greeting);
            MainActivity.this.findViewById(R.id.one).setBackgroundResource(R.drawable.background_on);
        }
    }
}
