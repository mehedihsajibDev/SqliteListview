package com.example.sajib.sqlitelistview;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
      private ListView listView;
      DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView=findViewById(R.id.listviewid);
        databaseHelper=new DatabaseHelper(this);
        loadData();
    }

    private void loadData() {
        ArrayList<String> listdata=new ArrayList<>();
        Cursor cursor=databaseHelper.showallData();
        if(cursor.getCount()==0){
            Toast.makeText(this, "No data is available", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                listdata.add(cursor.getString(0)+"\t"+ cursor.getString(1));
                }
        }
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.item_view,R.id.textviewhelperid,listdata);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String selectValue=adapter.getItem(position).toString();
                Toast.makeText(ListActivity.this, "selected value is"+selectValue, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
