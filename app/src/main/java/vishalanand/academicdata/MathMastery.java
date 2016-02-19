package vishalanand.academicdata;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MathMastery extends AppCompatActivity {

    private PieChart MT_Chart;
    Button RlButton;

    private String[] year = new String[]{"2013", "2014", "2015"};
    int position;
    private  float math[] = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_mastery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String pos = intent.getExtras().getString("posit");
        position = Integer.parseInt(pos);
        String name = null;

        switch (position) {
            case 0:
                name = "Nisha";

                math[0] = 67;
                math[1] = 70;
                math[2] = 65;
                break;
            case 1:
                name = "Kajal";

                math[0] = 56;
                math[1] = 67;
                math[2] = 59;
                break;
            case 2:
                name = "Roshni";

                math[0] = 87;
                math[1] = 88;
                math[2] = 89;
                break;
            case 3:
                name = "Barkha";

                math[0] = 64;
                math[1] = 68;
                math[2] = 76;
                break;
            case 4:
                name = "Sanjay";

                math[0] = 54;
                math[1] = 69;
                math[2] = 78;
                break;
            case 5:
                name = "Aakash";

                math[0] = 78;
                math[1] = 78;
                math[2] = 82;
                break;
            case 6:
                name = "Ariji";


                math[0] = 49;
                math[1] = 59;
                math[2] = 67;
                break;
            case 7:
                name = "Anshul";

                math[0] = 83;
                math[1] = 80;
                math[2] = 87;
                break;
            case 8:
                name = "Gauraang";

                math[0] = 73;
                math[1] = 78;
                math[2] = 80;
                break;


        }
        setTitle(name + " Math Mastery");

        MT_Chart = (PieChart) findViewById(R.id.pieChart1);
        MT_Chart.setBackgroundColor(Color.LTGRAY);


        //configure pie chart
        MT_Chart.setUsePercentValues(false);
        MT_Chart.setDescription(name);


        //enable hole and configure
        MT_Chart.setDrawHoleEnabled(true);
        MT_Chart.setHoleColorTransparent(true);


        MT_Chart.setHoleRadius(58f);
        MT_Chart.setTransparentCircleRadius(61f);


        //enable rotation of the chart by touch
        MT_Chart.setRotationAngle(0);
        MT_Chart.setRotationEnabled(true);

        //set chat selected listener
        MT_Chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                //display the value selected
                if (e == null) {
                    return;
                }
                Toast.makeText(MathMastery.this,"Math Marks in "+
                        year[e.getXIndex()] + " is " + e.getVal() + "%",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected() {

            }
        });
        //add data
        addData();


        // customize legend
        Legend l = MT_Chart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private void addData() {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < math.length; i++)
            yVals1.add(new Entry(math[i], i));

       /* int j = 0;
        for (int i = rc.length; i < rc.length + 3; i++) {
            yVals1.add(new Entry(math[j++], i));
        }*/
        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < year.length ; i++)
            xVals.add(year[i]);

        //create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "Student Academic Data");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);


        //add color
        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        //instantiate pie data object now
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);


        MT_Chart.setData(data);


        MT_Chart.highlightValues(null);

        MT_Chart.invalidate();


    }

}
