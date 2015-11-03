package example.isuru.com.wbn_test1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.MalformedInputException;
import java.util.ArrayList;

import example.isuru.com.wbn_test1.Utils.Values;

public class MainActivity extends AppCompatActivity {

    private Button btn_add;
    private Button btn_check;
    private int count;
    private Spinner selectIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_activity);
        setTitle("The Clicker");

        btn_add =  (Button) findViewById(R.id.btn_add_student);
        btn_check = (Button) findViewById(R.id.btn_check_for_broadcast);
        selectIndex =  (Spinner) findViewById(R.id.spinner_select_index);

        SharedPreferences appSP = getSharedPreferences(Values.PREFS_INDEX, Context.MODE_PRIVATE);
        String stuCount = appSP.getString("count", null);

        ArrayList indexList = new ArrayList<String>();

        if(stuCount!=null){
            count = Integer.parseInt(stuCount);
            if(count==1){
                indexList.add(appSP.getString("index1",null));
            }else if(count==2){
                indexList.add(appSP.getString("index1",null));
                indexList.add(appSP.getString("index2",null));
            }else if(count==3){
                indexList.add(appSP.getString("index1",null));
                indexList.add(appSP.getString("index2",null));
                indexList.add(appSP.getString("index3",null));
            }
        }

        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item);
        spinAdapter.addAll(indexList);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectIndex.setAdapter(spinAdapter);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addStudent();
            }
        });

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenToService();
            }
        });

    }

    public void addStudent(){
        if(count==3){
            //not allowed
            Toast.makeText(MainActivity.this, "No more students allowed", Toast.LENGTH_SHORT).show();
        }else if(count<3){
            //allowed
            SharedPreferences appSP = getSharedPreferences(Values.PREFS_INDEX, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = appSP.edit();
            editor.putString("firstRun","Yes");
            editor.commit();
            Intent mainIntent = new Intent(MainActivity.this, InitialActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }

    public void listenToService(){

        //network code to check for broadcast messages





        //if a new quiz is detected (if server's broadcast message is received)
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("New Quiz");
        alertDialogBuilder
                .setMessage("New Quiz detected")
                .setCancelable(false)
                .setPositiveButton("Go",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        //go to quiz answer screen
                        Intent mainIntent = new Intent(MainActivity.this, AnswerActivity.class);
                        mainIntent.putExtra("index",selectIndex.getSelectedItem().toString());
                        startActivity(mainIntent);
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }




}
