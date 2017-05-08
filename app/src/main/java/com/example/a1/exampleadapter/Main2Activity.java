package com.example.a1.exampleadapter;

import android.app.Dialog;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import ru.yandex.speechkit.*;
import ru.yandex.speechkit.Error;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Main2Activity extends AppCompatActivity  {
    BoxAdapter boxAdapter;
    Vocalizer vocalizer;
    static final String API_KEY_YANDEX = "670655db-edd8-4ee5-b3b7-e9d47ec78ed8";


    Map<String,ArrayList<Product>> values = new TreeMap();
    static ArrayList<Product> arrayAdapter_list = new ArrayList<Product>();
    String key_for_adapterList = MainActivity.what_i_get;


    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SpeechKit.getInstance().configure(this, API_KEY_YANDEX);


        if( !values.containsKey(key_for_adapterList )) {values.put(key_for_adapterList,arrayAdapter_list);}
        else {arrayAdapter_list = values.get(key_for_adapterList);}


        boxAdapter = new BoxAdapter(this,values.get(key_for_adapterList));

        ListView lvMain1 = (ListView) findViewById(R.id.lvMein1);
        lvMain1.setAdapter(boxAdapter);

    }
    public void addToList1(View view ){
        arrayAdapter_list.add(new Product("word","word1"));
        values.put(key_for_adapterList, arrayAdapter_list);
        Toast.makeText(this,String.valueOf(arrayAdapter_list.size())+" "+key_for_adapterList,Toast.LENGTH_SHORT).show();
        boxAdapter.notifyDataSetChanged();
    }

    public void go_Back(View v){
        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
        startActivity (intent);
    }


    public void play(View view) {
       ThreadPlayVoice th = new ThreadPlayVoice(arrayAdapter_list);
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
