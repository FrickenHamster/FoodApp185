package edu.ucsb.cs.cs185.frickenhamster.food.history;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.FillFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import edu.ucsb.cs.cs185.frickenhamster.food.FoodOrder;
import edu.ucsb.cs.cs185.frickenhamster.food.R;

/**
 * Created by Dario on 6/62015.
 */
public class VisualizeActivity extends Activity {
    int days;
    ArrayList<FoodOrder> dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualize);
//        GraphView graph = (GraphView) findViewById(R.id.graph);
        days = -1;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(openFileInput("history.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        dataSet = new ArrayList<FoodOrder>();
        String line;

        if (reader == null) return;
        try {
            while ((line = reader.readLine()) != null) {
                dataSet.add(new FoodOrder(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] data = new int[5];
        for (FoodOrder order: dataSet) {
            if (order.type.compareTo("Hamburger") == 0) {
                data[0]++;
            }
            else if (order.type.compareTo("Pizza") == 0) {
                data[1]++;
            }
            else if (order.type.compareTo("Steak") == 0) {
                data[2]++;
            }
            else if (order.type.compareTo("Pancakes") == 0) {
                data[3]++;
            }
            else {
                data[4]++;
            }
        }

        drawGraph();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_visualize, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void drawGraph() {

        int[] data = new int[5];
        if (days == -1) {
            for (FoodOrder order: dataSet) {
                if (order.type.compareTo("Hamburger") == 0) {
                    data[0]++;
                }
                else if (order.type.compareTo("Pizza") == 0) {
                    data[1]++;
                }
                else if (order.type.compareTo("Steak") == 0) {
                    data[2]++;
                }
                else if (order.type.compareTo("Pancakes") == 0) {
                    data[3]++;
                }
                else {
                    data[4]++;
                }
            }
        }
        else {
            //need to compare date string to date
            for (FoodOrder order: dataSet) {
                if (order.type.compareTo("Hamburger") == 0) {
                    data[0]++;
                }
                else if (order.type.compareTo("Pizza") == 0) {
                    data[1]++;
                }
                else if (order.type.compareTo("Steak") == 0) {
                    data[2]++;
                }
                else if (order.type.compareTo("Pancakes") == 0) {
                    data[3]++;
                }
                else {
                    data[4]++;
                }
            }
        }

        BarChart chart = (BarChart) findViewById(R.id.chart);
        String[] labels = new String[]{"Hamburger", "Pizza", "Steak", "Pancakes"};

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        int maxVal = 0;
        for (int i = 0; i < 4; i++) {
            BarEntry entry = new BarEntry(data[i], i);
            entries.add(entry);
            if (data[i] > maxVal)
                maxVal = data[i];
        }
        BarDataSet barDataSet = new BarDataSet(entries, "Food Types");
        int[] colors = new int[]{getResources().getColor(R.color.hamburger), getResources().getColor(R.color.pizza), getResources().getColor(R.color.steak), getResources().getColor(R.color.pancakes)};
        barDataSet.setColors(colors);
        BarData barData = new BarData(labels, barDataSet);
        chart.setData(barData);
        chart.setDrawValueAboveBar(true);
        chart.getAxisRight().setDrawAxisLine(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisLeft().setAxisMaxValue(maxVal + 1);
        chart.getAxisRight().setAxisMaxValue(maxVal + 1);
        chart.getAxisLeft().setShowOnlyMinMax(true);
        chart.getXAxis().setDrawGridLines(false);
        chart.setDescription("");
    }


    public void allTime(MenuItem item) {
        days = -1;
        drawGraph();
    }

    public void set1month(MenuItem item) {
        days = 30;
        drawGraph();
    }

    public void set2weeks(MenuItem item) {
        days = 14;
        drawGraph();
    }

    public void set7days(MenuItem item) {
        days = 7;
        drawGraph();
    }
}
