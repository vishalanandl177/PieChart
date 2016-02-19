package vishalanand.academicdata;

import android.content.Intent;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MultiDex.install(getBaseContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final boolean[] b = {false};

        //Creating a Spinner

        Spinner selectNameSpinner = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<CharSequence> spinnerAdapter =
                ArrayAdapter.createFromResource(this, R.array.student,
                        android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectNameSpinner.setAdapter(spinnerAdapter);

        //Add Listener to spinner

        selectNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                //  Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();

                // Do not start second activity first time.

                if (b[0]) {
                    Intent intent = new Intent(MainActivity.this, AcademicDataActivity.class);
                    intent.putExtra("Main", item);
                    intent.putExtra("position",String.valueOf(position));
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                    startActivity(intent);


                }
                b[0] = true;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.exittheapp) {
            finish();

        } else {
            Toast.makeText(MainActivity.this, "Developed By: Vishal Anand", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


}
