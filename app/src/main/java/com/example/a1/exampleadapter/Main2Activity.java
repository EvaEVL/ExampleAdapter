package com.example.a1.exampleadapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import ru.yandex.speechkit.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Main2Activity extends AppCompatActivity  {
    BoxAdapter boxAdapter;
    Vocalizer vocalizer;
    static final String API_KEY_YANDEX = "670655db-edd8-4ee5-b3b7-e9d47ec78ed8";

    // Map<Integer,ArrayList<Product>> values = new TreeMap();
    //  static ArrayList<Product> arrayAdapter_list = new ArrayList<Product>();
    int key_for_adapterList = MainActivity.what_i_get;

    ArrayList<Product> arrLib = new ArrayList<>();
    ArrayList<Product> ap_kek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (MainActivity.ap.get(key_for_adapterList).arrProducts != null){
            arrLib = MainActivity.ap.get(key_for_adapterList).arrProducts;
        }
        SpeechKit.getInstance().configure(this, API_KEY_YANDEX);

        boxAdapter = new BoxAdapter(this,arrLib);

        ListView lvMain1 = (ListView) findViewById(R.id.lvMein1);
        lvMain1.setAdapter(boxAdapter);

    }
    public void addToList1(View view ){
        arrLib.add(new Product("word","word1"));
        //values.put(key_for_adapterList, arrayAdapter_list);
        Toast.makeText(this,String.valueOf(arrLib.size())+" "+key_for_adapterList,Toast.LENGTH_SHORT).show();
        if (arrLib != null)
            MainActivity.ap.get(key_for_adapterList).arrProducts = arrLib;

        boxAdapter.notifyDataSetChanged();
        MainActivity.saveToFile(MainActivity.ap,getApplicationContext());
    }

    public void go_Back(View v){
        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
        startActivity (intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //key_for_adapterList = MainActivity.what_i_get;
        if (MainActivity.ap.get(key_for_adapterList).arrProducts != null){
            arrLib = MainActivity.ap.get(key_for_adapterList).arrProducts;
        }
    }

    public void play(View view) {
        ThreadPlayVoice th = new ThreadPlayVoice(arrLib);
        th.run();
//        final Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.dialog_get_names);
//        dialog.show();
//
//        Button stop = (Button)dialog.findViewById(R.id.stopVoice);
//
///*        stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                dialog.dismiss();
//            }
        //       });*/
    }

}
