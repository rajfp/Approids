package com.example.rajat.approids;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Nuskhe extends AppCompatActivity implements SearchView.OnQueryTextListener {


    private static final String URL_PRODUCTS = "https://www.approids.com/android/rajat/nuskhe.php";


    List<Product> productList;
    RecyclerView recyclerView;
    ProductsAdapter adapter;
    Toolbar toolbar;
    View progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuskhe);

        toolbar= (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progress=findViewById(R.id.progress);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        productList = new ArrayList<>();


        loadProducts();
    }

    private void loadProducts() {

progress.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.print(response);

                            JSONArray array = new JSONArray(response);

                            progress.setVisibility(View.INVISIBLE);

                            for (int i = 0; i < array.length(); i++) {


                                JSONObject product = array.getJSONObject(i);


                                productList.add(new Product(

                                        product.getString("title"),
                                        product.getString("description")
                                ));
                            }


                             adapter = new ProductsAdapter(Nuskhe.this, productList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progress.setVisibility(View.INVISIBLE);
                    }
                });


        AppController.getInstance(this).getRequestQueue().getCache().invalidate(URL_PRODUCTS, true);
        Volley.newRequestQueue(this).add(stringRequest);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText=newText.toLowerCase();
        ArrayList<Product> newList=new ArrayList<>();
        for(Product product: productList)
        {
            String name=product.getTitle().toString().toLowerCase();
            if(name.contains((newText)))
                newList.add(product);
        }
        adapter.setFilter(newList);
        return true;
    }
}