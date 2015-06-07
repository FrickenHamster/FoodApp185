package edu.ucsb.cs.cs185.frickenhamster.food.history;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import edu.ucsb.cs.cs185.frickenhamster.food.R;

/**
 * Created by Dario on 6/62015.
 */
public class HistoryActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mRecyclerView = (RecyclerView) findViewById(R.id.history_recycler_view);
        //emptyText = (TextView)findViewById(R.id.empty);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<String>();
        myDataset.add("Hamburger");
        myDataset.add("Hamburger");
        myDataset.add("Pizza");
        myDataset.add("Pancakes");
        myDataset.add("Pizza");

        mAdapter = new HistoryAdapter(this, myDataset);
        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_visualize) {
            visualizeHistory();
        }

        return super.onOptionsItemSelected(item);
    }

    void visualizeHistory(){
        Intent intent = new Intent(this, VisualizeActivity.class);
        startActivity(intent);
    }
}
