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

    static ArrayList<ArrayList<Product>> arrLib = new ArrayList<>();
    ArrayList<Product> ap_kek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SpeechKit.getInstance().configure(this, API_KEY_YANDEX);


      //  if( !values.containsKey(key_for_adapterList )) {values.put(key_for_adapterList,arrayAdapter_list);}
     //   else {arrayAdapter_list = values.get(key_for_adapterList);}


        boxAdapter = new BoxAdapter(this,arrLib.get(key_for_adapterList));

        ListView lvMain1 = (ListView) findViewById(R.id.lvMein1);
        lvMain1.setAdapter(boxAdapter);

    }
    public void addToList1(View view ){
        arrLib.get(key_for_adapterList).add(new Product("word","word1"));
        //values.put(key_for_adapterList, arrayAdapter_list);
        Toast.makeText(this,String.valueOf(arrLib.get(key_for_adapterList).size())+" "+key_for_adapterList,Toast.LENGTH_SHORT).show();
        boxAdapter.notifyDataSetChanged();
    }

    public void go_Back(View v){
        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
        startActivity (intent);
    }


    public void play(View view) {
       ThreadPlayVoice th = new ThreadPlayVoice(arrLib.get(key_for_adapterList));
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
