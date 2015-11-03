package example.isuru.com.wbn_test1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.isuru.com.wbn_test1.Utils.Values;

public class InitialActivity extends AppCompatActivity {

    private EditText txt_index;
    private Button btn_save;
    private String err_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_initial_activity);

        txt_index = (EditText) findViewById(R.id.txt_index);
        btn_save =  (Button) findViewById(R.id.btn_save);

        if(firstRun()){
            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveIndex();
                    if(err_string.equals("")){
                        Toast.makeText(InitialActivity.this,"Index number saved successfully",Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(InitialActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }else{
                        Toast.makeText(InitialActivity.this,"An error has occured:"+err_string,Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Intent mainIntent = new Intent(InitialActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }

    public boolean firstRun(){
        SharedPreferences appSP = getSharedPreferences(Values.PREFS_INDEX, Context.MODE_PRIVATE);
        String firstRun = appSP.getString("firstRun",null);

        if(firstRun!=null){
            if(firstRun.equals("No")){
                return  false;
            }else{
                return true;
            }
        }else{
            return true;
        }
    }


    public void saveIndex(){
        SharedPreferences appSP = getSharedPreferences(Values.PREFS_INDEX, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = appSP.edit();

        String value = txt_index.getText().toString();
        //parity check


        //first check how many student ids has been saved
        String count = appSP.getString("count",null);

        int studentKey = 1;

        err_string="";

        if(count !=null){
            if(Integer.parseInt(count)==1){
                studentKey=2;
                editor.putString("index"+String.valueOf(studentKey), value);
                editor.putString("count","2");
            }else if(Integer.parseInt(count)==2){
                studentKey=3;
                editor.putString("index"+String.valueOf(studentKey), value);
                editor.putString("count","3");
            }else {
                err_string="Cannot use by more than 3 students";
            }
        }else{
            editor.putString("index"+String.valueOf(studentKey), value);
            editor.putString("count","1");
        }
        editor.putString("firstRun","No");
        editor.commit();
    }


    public void validateIndex(){

    }

}
