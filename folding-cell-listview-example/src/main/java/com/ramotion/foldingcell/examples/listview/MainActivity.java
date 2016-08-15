package com.ramotion.foldingcell.examples.listview;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.ramotion.foldingcell.FoldingCell;
import com.ramotion.foldingcell.examples.R;
import com.ramotion.foldingcell.examples.adapter.FavoritesListAdapter;
import com.ramotion.foldingcell.examples.adapter.FoldingCellListAdapter;
import com.ramotion.foldingcell.examples.model.TvShow;
import com.ramotion.foldingcell.examples.service.FavoritesService;

import java.util.ArrayList;
import java.util.List;


/**
 * Example of using Folding Cell with ListView and ListAdapter
 */
public class MainActivity extends AppCompatActivity {

    private FavoritesListAdapter adapterFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get our list view
        ListView theListView = (ListView) findViewById(R.id.favListView);

        // prepare elements to display
        final ArrayList<Item> items = Item.getTestingList();

        // add custom btn handler to first list item
        items.get(0).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CUSTOM HANDLER FOR FIRST BUTTON", Toast.LENGTH_SHORT).show();
            }
        });

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(this, items);

        // add default btn handler for each request btn on each item if custom handler not found
        adapter.setDefaultRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "DEFAULT HANDLER FOR ALL BUTTONS", Toast.LENGTH_SHORT).show();
            }
        });

        // set elements to adapter
        theListView.setAdapter(adapter);

        // set on click event listener to list view
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                adapter.registerToggle(pos);
            }
        });

        // Init the image loader
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(5)
                .memoryCacheSizePercentage(40)
                .build();
        ImageLoader.getInstance().init(config);

        new LoadFavoritesTask(this).execute("BFB8F86F8C7FB1C0");

    }
    // Class to load the search response asynchronously
    private class LoadFavoritesTask extends AsyncTask<String, Void, List<TvShow>> {
        private Context context;

        public LoadFavoritesTask(Context context) {
            this.context = context;
        }
        @Override
        protected List<TvShow> doInBackground(String... query) {

            try {
                // Search the tvdb API
                FavoritesService tvdbFavoritesService = new FavoritesService();
                return tvdbFavoritesService.getFavorites(query[0]);

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<TvShow> results){

            // Setup the list adapter with the data from the web call
            adapterFav = new FavoritesListAdapter(context, results);

            ListView theListView = (ListView) findViewById(R.id.favListView);
            theListView.setAdapter(adapterFav);
            //getListView().setOnItemClickListener(new ItemClickedListener());

//            ProgressBar progress = (ProgressBar)findViewById(R.id.progress);
//            progress.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_search) {
            onSearchRequested();
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

}
