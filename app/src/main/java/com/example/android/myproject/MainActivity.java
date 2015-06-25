package com.example.android.myproject;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private Button saveName;
    private static final String PREFS_NAME = "MyPrefsFile";
    private TextView showNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.nameEditText);
        saveName = (Button)findViewById(R.id.save);
        showNameText = (TextView)findViewById(R.id.showNameTextView);

        saveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences myPrefs = getSharedPreferences(PREFS_NAME,0);
                SharedPreferences.Editor editor = myPrefs.edit();

                if (name.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter a Name.",Toast.LENGTH_LONG).show();

                }else{
                    editor.putString("name",name.getText().toString());
                    editor.commit();

                }



            }
        });
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        if (prefs.contains("name")){
            String userName = prefs.getString("name", "not found");
            showNameText.setText("You are "+ userName);
        }else {
            showNameText.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
