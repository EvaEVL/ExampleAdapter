package com.example.a1.exampleadapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static int what_i_get ;
    static ArrayList<Product> ap = new ArrayList<Product>();
    BoxAdapter boxAdapter;

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        boxAdapter = new BoxAdapter(this,ap);

        ListView lvMain = (ListView) findViewById(R.id.lvMein);
        lvMain.setAdapter(boxAdapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                LayoutInflater li = LayoutInflater.from(context);
                View prv = li.inflate(R.layout.dialog_get_names,null);

                AlertDialog.Builder mDialoGbulder = new AlertDialog.Builder(context);

                final EditText ed1 = (EditText) prv.findViewById(R.id.ed1);
                final EditText ed2 = (EditText) prv.findViewById(R.id.edi2);

                mDialoGbulder.setCancelable(false).setPositiveButton("EEHHHUU", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       // ap.set(i,ed1.getText().toString(),ed2.getText().toString()));//new Product(ed1.getText().toString(),ed2.getText().toString()));
                        ap.set(i,new Product(ed1.getText().toString(),ed2.getText().toString()));
                        boxAdapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("К ЧЕРТУ!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = mDialoGbulder.create();
                alertDialog.show();
            }
        });

        lvMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                what_i_get = i;

                startActivity(intent);
                return false;
            }
        });
    }



  public void addToList(View view ){
        ap.add(new Product("something","something"));
        Toast.makeText(this,String.valueOf(ap.size()),Toast.LENGTH_SHORT).show();
        boxAdapter.notifyDataSetChanged();
    }




}
