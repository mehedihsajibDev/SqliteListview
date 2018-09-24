package com.example.sajib.sqlitelistview;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText idedittext,nameEdittext;
    private Button save,display,update,delete;
    private  DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        idedittext=findViewById(R.id.textviewid);
        nameEdittext=findViewById(R.id.textviewnameid);
        save=findViewById(R.id.buttonid);
        display=findViewById(R.id.dispplaybuttonid);
        update=findViewById(R.id.updatebuttonid);
        delete=findViewById(R.id.deletebuttonid);
        save.setOnClickListener(this);
        display.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String id=idedittext.getText().toString();
        String name=nameEdittext.getText().toString();

        if(v.getId()== R.id.buttonid){
     if(id.equals("")&&name.equals("")){
         Toast.makeText(this, "PLease enterr all data", Toast.LENGTH_SHORT).show();
     }
     else {
         long rownumber=databaseHelper.saveData(id,name);
         if(rownumber > -1){
             Toast.makeText(this, "SUCCESSFULLY"+rownumber, Toast.LENGTH_SHORT).show();
             idedittext.setText("");
             nameEdittext.setText("");
         }
         else {
             Toast.makeText(this, " Unsuccessfull: "+rownumber, Toast.LENGTH_SHORT).show();

         }
     }
        }
        else if(v.getId()==R.id.dispplaybuttonid){
            Intent intent=new Intent(MainActivity.this, ListActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.updatebuttonid){

        }
        else if(v.getId()==R.id.deletebuttonid){

        }
    }
}
