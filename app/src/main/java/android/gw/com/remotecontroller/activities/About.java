package android.gw.com.remotecontroller.activities;

import android.content.Intent;
import android.gw.com.remotecontroller.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class About extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.menu2);
        drawDrawer();
    }
    public void drawDrawer(){
        final Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withIdentifier(1).withName(R.string.menu4),
                        new SecondaryDrawerItem().withIdentifier(2).withName(R.string.menu3)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position){
                            case 1:{
                                startActivity(new Intent(About.this, StartActivity.class));
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
