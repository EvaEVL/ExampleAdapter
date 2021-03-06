package com.example.a1.exampleadapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> apLib = new ArrayList<Product>();
    static int what_i_get ;
    static ArrayList<Product> ap;
    public BoxAdapter boxAdapter;

    private static final String TAG = "onPause";
    private static final String TAG1 = "onResume";

    final Context context = this;

    public static final String SAVE_ARRAY_AP = "array_ap.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (loadFromFile(context) == null) {
            ap = new ArrayList<Product>();
        } else {
            ap = loadFromFile(context);
        }
        boxAdapter = new BoxAdapter(this, ap);
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
                Button cancel = (Button) dialog.findViewById(R.id.cancel);
                Button ok = (Button) dialog.findViewById(R.id.ok);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ap.set(i, new Product(ed1.getText().toString(), ed2.getText().toString()));
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
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                Product product = ap.get(i);
                what_i_get = i;
                startActivity(intent);
                return false;
            }
        });
    }

    public void addToList(View view ){
        ap.add(new Product("some","something"));
        //  Main2Activity..add(apLib);
        boxAdapter.notifyDataSetChanged();
        Toast.makeText(this,String.valueOf(ap.size()),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.wtf(TAG,"KEK");
        saveToFile(ap,context);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.wtf(TAG1,"KEK resume");
        if(loadFromFile(context) != null) {
            ap = loadFromFile(context);
            boxAdapter.notifyDataSetChanged();
        }
    }

    static void saveToFile(ArrayList<Product> aP, Context context) {
        try {
            Log.d("FILE_DIR", context.getFilesDir().toString());
            FileOutputStream fos = new FileOutputStream(context.getFilesDir().toString() + "/" + SAVE_ARRAY_AP);
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(aP);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ArrayList<Product> loadFromFile(Context context) {
        try {
            FileInputStream fis = new FileInputStream(context.getFilesDir().toString() + "/" + SAVE_ARRAY_AP);
            ObjectInputStream ois= new ObjectInputStream(fis);
            ArrayList<Product> products = (ArrayList<Product>)ois.readObject();
            ois.close();
            return products;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}