package com.example.rajat.approids;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
ImageButton button,button1,button2;
    ImageButton button3;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar= (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        NavigationView navigationView= (NavigationView) findViewById(R.id.nv);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout= (DrawerLayout) findViewById(R.id.dr);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        button= (ImageButton) findViewById(R.id.butt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.onclick));
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        button1= (ImageButton) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.onclick));
                Intent intent=new Intent(Main2Activity.this,Food.class);
                startActivity(intent);
            }
        });
        button2= (ImageButton) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.onclick));
                Intent intent=new Intent(Main2Activity.this,Nuskhe.class);
                startActivity(intent);
            }
        });
        button3= (ImageButton) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.onclick));
                Intent intent=new Intent(Main2Activity.this,yoga.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onPostCreate( Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int d=item.getItemId();
        switch(d)
        {

            case R.id.sugg:
                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "rajatnfo@gmail.com"));
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    drawerLayout.closeDrawers();
                }
                break;
            case R.id.share:
                Intent i=new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
                i.putExtra(android.content.Intent.EXTRA_TEXT,"Coming Soon");
                startActivity(Intent.createChooser(i,"Share via"));
                drawerLayout.closeDrawers();
                break;
            case R.id.rate:
                Toast.makeText(this,"Work in progress",Toast.LENGTH_LONG).show();
                drawerLayout.closeDrawers();
                break;
            case R.id.like:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri
                        .parse("https://www.facebook.com/approids/")));
                drawerLayout.closeDrawers();
                break;
            case R.id.more:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri
                        .parse("market://search?q=pub:Approids+Tech")));
               drawerLayout.closeDrawers();
                break;

        }
        return false;
    }
}
