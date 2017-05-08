package com.example.a1.exampleadapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static String what_i_get ;
    ArrayList<Product> ap = new ArrayList<Product>();
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
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_get_names);

                dialog.show();

                final EditText ed1 = (EditText) dialog.findViewById(R.id.ed1);
                final EditText ed2 = (EditText) dialog.findViewById(R.id.edi2);
                Button cancel =(Button)dialog.findViewById(R.id.cancel);
                Button ok =(Button)dialog.findViewById(R.id.ok);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ap.set(i,new Product(ed1.getText().toString(),ed2.getText().toString()));
                        boxAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        lvMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                Product product = ap.get(i);
                what_i_get = product.name;
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
