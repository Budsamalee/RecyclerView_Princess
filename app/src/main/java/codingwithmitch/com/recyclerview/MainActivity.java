package codingwithmitch.com.recyclerview;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private  String [] mDrawerTitle = {"History", "Video", "Movie", "Game", "television", "Activities", "Product", "App", "About" , };
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ListView mListView;


    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");

        initImageBitmaps();
        mDrawerLayout = findViewById(R.id.drawer_layout);
//        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,
//                mDrawerLayout,
//                R.string.open_drawer,
//                R.string.close_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,mDrawerLayout,R.string.open_drawer,R.string.close_drawer);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = findViewById(R.id.drawer);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,
                android.R.layout.simple_list_item_1,mDrawerTitle );
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) mListView.getItemAtPosition(position);

                mDrawerLayout.closeDrawers();
                Toast.makeText(getApplicationContext(),
                        "Go" + " to " + itemValue + " !!!!!", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://lumiere-a.akamaihd.net/v1/images/character_disneyprincess_ariel_262253c9.jpeg?region=0,0,300,300");
        mNames.add("Ariel");

        mImageUrls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGN_n3heEZap8PTbtgaJCCwjGgs7joBspR1P36Xd9cBifHMJR5");
        mNames.add("belle");

        mImageUrls.add("https://lumiere-a.akamaihd.net/v1/images/character_disneyprincess_cidnerella_fbe77f43.jpeg?region=0,0,300,300");
        mNames.add("cinderella");

        mImageUrls.add("https://yt3.ggpht.com/a-/AJLlDp1FgsWeHw5PEs-NLIx7mTq5QMD35qP9qy5JAg=s900-mo-c-c0xffffffff-rj-k-no");
        mNames.add("snow white");

        mImageUrls.add("http://images6.fanpop.com/image/photos/39000000/Aladdin-Princess-Jasmine-disney-princess-39040302-300-300.jpg");
        mNames.add("jasmine");

        mImageUrls.add("https://lumiere-a.akamaihd.net/v1/images/au_character_disneyprincess_rapunzel_nr_02_ae6bbb67.jpeg?region=0,0,300,300");
        mNames.add("rapunzel");

        mImageUrls.add("https://vignette.wikia.nocookie.net/disney/images/b/b0/DP-Tiana.jpg/revision/latest?cb=20140515133539");
        mNames.add("tiana");

        mImageUrls.add("https://lumiere-a.akamaihd.net/v1/images/character_disneyprincess_mulan_693b57cb.jpeg?region=0,0,300,300");
        mNames.add("mulan");

        mImageUrls.add("https://vignette.wikia.nocookie.net/winx/images/a/a9/Icon_Merida.jpg/revision/latest?cb=20150909130627");
        mNames.add("merida");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            // Handle item selection
            switch (item.getItemId()) {
                case R.id.mnuNew:
                    Toast.makeText(this, "New!", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.mnuHelp:
                    Toast.makeText(this, "Help!", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
        else {
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}