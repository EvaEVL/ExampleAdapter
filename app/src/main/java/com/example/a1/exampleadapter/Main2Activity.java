package com.example.a1.exampleadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    static ArrayList<Product> ap = new ArrayList<Product>();
    BoxAdapter boxAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        boxAdapter = new BoxAdapter(this,ap);

        ListView lvMain1 = (ListView) findViewById(R.id.lvMein1);
        lvMain1.setAdapter(boxAdapter);
    }
    public void addToList1(View view ){
        ap.add(new Product("word","word1"));
        Toast.makeText(this,String.valueOf(ap.size()),Toast.LENGTH_SHORT).show();
        boxAdapter.notifyDataSetChanged();
    }
}
