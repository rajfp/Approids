package com.example.rajat.approids;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

public class Display extends AppCompatActivity {
    String TAG="Display";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        getIncomingIntent();
        toolbar= (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    private  void getIncomingIntent()
    {
        if(getIntent().hasExtra("description"))
        {
            Log.d(TAG,"getIncomingIntent: found intent extras");
        }
        String description=getIntent().getStringExtra("description");
        setTitle(description);
    }
    private  void setTitle(String description)
    {
        TextView textView= (TextView) findViewById(R.id.textview);
        textView.setText(Html.fromHtml(Html.fromHtml(description).toString()));
    }
}
