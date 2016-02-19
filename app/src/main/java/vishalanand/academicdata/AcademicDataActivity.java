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

public class AcademicDataActivity extends AppCompatActivity {

    private PieChart RC_Chart;
    Button mathButton;
    int position;
    private String[] year = new String[]{"2013", "2014", "2015"};

    private float rc[] = new float[3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        String string = intent.getExtras().getString("Main");
        setTitle(string);
        final String pos = intent.getExtras().getString("position");
         position = Integer.parseInt(pos);
        String name = null;

        switch (position) {
            case 0:
                name = "Nisha";
                rc[0] = (float) 1.5;
                rc[1] = 2;
                rc[2] = (float) 2.5;

                break;
            case 1:
                name = "Kajal";
                rc[0] = (float) 3.5;
                rc[1] = 4;
                rc[2] = 5;

                break;
            case 2:
                name = "Roshni";
                rc[0] = 4;
                rc[1] = (float) 4.5;
                rc[2] = (float) 4.5;

                break;
            case 3:
                name = "Barkha";
                rc[0] = 3;
                rc[1] = (float) 3.5;
                rc[2] = (float) 3.5;

                break;
            case 4:
                name = "Sanjay";
                rc[0] = (float) 3.5;
                rc[1] = 4;
                rc[2] = (float) 4.5;


                break;
            case 5:
                name = "Aakash";
                rc[0] = 2;
                rc[1] = 3;
                rc[2] = (float) 3.5;

                break;
            case 6:
                name = "Ariji";
                rc[0] = (float) 1.5;
                rc[1] = 2;
                rc[2] = 3;

                break;
            case 7:
                name = "Anshul";
                rc[0] = 2;
                rc[1] = 3;
                rc[2] = 4;


                break;
            case 8:
                name = "Gauraang";
                rc[0] = 4;
                rc[1] = (float) 4.5;
                rc[2] = (float) 5.5;

                break;


        }
        setTitle(name + " RC Level");

        RC_Chart = (PieChart) findViewById(R.id.pieChart);
        RC_Chart.setBackgroundColor(Color.LTGRAY);


        //configure pie chart
        RC_Chart.setUsePercentValues(true);
        RC_Chart.setDescription(name);


        //enable hole and configure
        RC_Chart.setDrawHoleEnabled(true);
        RC_Chart.setHoleColorTransparent(true);


        RC_Chart.setHoleRadius(58f);
        RC_Chart.setTransparentCircleRadius(61f);


        //enable rotation of the chart by touch
        RC_Chart.setRotationAngle(0);
        RC_Chart.setRotationEnabled(true);


        //set chat selected listener
        RC_Chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                //display the value selected
                if (e == null) {
                    return;
                }
                Toast.makeText(AcademicDataActivity.this, "RC Level in " +
                        year[e.getXIndex()] + " is " + e.getVal() ,
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected() {

            }
        });


        //add data
        addData();


        // customize legend
        Legend l = RC_Chart.getLegend();
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

        mathButton = (Button) findViewById(R.id.mathM);
        mathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AcademicDataActivity.this, MathMastery.class);
                intent.putExtra("posit",String.valueOf(pos));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                startActivity(intent);
            }
        });
    }


    private void addData() {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < rc.length; i++)
            yVals1.add(new Entry(rc[i], i));

       /* int j = 0;
        for (int i = rc.length; i < rc.length + 3; i++) {
            yVals1.add(new Entry(math[j++], i));
        }*/
        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < year.length ; i++)
            xVals.add(year[i]);

        //create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "Student Acsdemic Data");
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


        RC_Chart.setData(data);


        RC_Chart.highlightValues(null);

        RC_Chart.invalidate();


    }


}
