package example.isuru.com.wbn_test1;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AnswerActivity extends AppCompatActivity {

    private TextView title;
    private Button ansA;
    private Button ansB;
    private Button ansC;
    private Button ansD;
    private Button ansE;
    private Button submit;
    private Button back;
    private String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_answer_activity);
        String index = getIntent().getExtras().getString("index");
        initView();
        title.setText("Answering as : " + index);

        ansA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ansA.setBackgroundResource(R.drawable.btn_selected);
                setBg("A");
                setPadding();
                answer = "A";
            }
        });

        ansB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ansB.setBackgroundResource(R.drawable.btn_selected);
                setBg("B");
                setPadding();
                answer = "B";
            }
        });

        ansC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ansC.setBackgroundResource(R.drawable.btn_selected);
                setBg("C");
                setPadding();
                answer = "C";
            }
        });

        ansD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ansD.setBackgroundResource(R.drawable.btn_selected);
                setBg("D");
                setPadding();
                answer = "D";
            }
        });

        ansE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ansE.setBackgroundResource(R.drawable.btn_selected);
                setBg("E");
                setPadding();
                answer = "E";
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AnswerActivity.this,"Sending Answer: "+answer,Toast.LENGTH_SHORT).show();

                //Network code goes here, send response from client to server


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initView(){
        title = (TextView)findViewById(R.id.textView);
        ansA = (Button)findViewById(R.id.ansA);
        ansB = (Button)findViewById(R.id.ansB);
        ansC = (Button)findViewById(R.id.ansC);
        ansD = (Button)findViewById(R.id.ansD);
        ansE = (Button)findViewById(R.id.ansE);
        submit = (Button)findViewById(R.id.submit);
        back = (Button)findViewById(R.id.back);
        ansA.setBackgroundResource(R.drawable.btn_unselected);
        ansB.setBackgroundResource(R.drawable.btn_unselected);
        ansC.setBackgroundResource(R.drawable.btn_unselected);
        ansD.setBackgroundResource(R.drawable.btn_unselected);
        ansE.setBackgroundResource(R.drawable.btn_unselected);
    }

    public void setPadding(){
        ansA.setPadding(0,60,0,60);
        ansB.setPadding(0,60,0,60);
        ansC.setPadding(0,60,0,60);
        ansD.setPadding(0,60,0,60);
        ansE.setPadding(0,60,0,60);
    }

    public void setBg(String ans){
        if(ans.equals("A")){
            ansB.setBackgroundResource(R.drawable.btn_unselected);
            ansC.setBackgroundResource(R.drawable.btn_unselected);
            ansD.setBackgroundResource(R.drawable.btn_unselected);
            ansE.setBackgroundResource(R.drawable.btn_unselected);
        }else if(ans.equals("B")){
            ansA.setBackgroundResource(R.drawable.btn_unselected);
            ansC.setBackgroundResource(R.drawable.btn_unselected);
            ansD.setBackgroundResource(R.drawable.btn_unselected);
            ansE.setBackgroundResource(R.drawable.btn_unselected);
        }else if(ans.equals("C")){
            ansA.setBackgroundResource(R.drawable.btn_unselected);
            ansB.setBackgroundResource(R.drawable.btn_unselected);
            ansD.setBackgroundResource(R.drawable.btn_unselected);
            ansE.setBackgroundResource(R.drawable.btn_unselected);
        }else if(ans.equals("D")){
            ansA.setBackgroundResource(R.drawable.btn_unselected);
            ansB.setBackgroundResource(R.drawable.btn_unselected);
            ansC.setBackgroundResource(R.drawable.btn_unselected);
            ansE.setBackgroundResource(R.drawable.btn_unselected);
        }else if(ans.equals("E")){
            ansA.setBackgroundResource(R.drawable.btn_unselected);
            ansB.setBackgroundResource(R.drawable.btn_unselected);
            ansC.setBackgroundResource(R.drawable.btn_unselected);
            ansD.setBackgroundResource(R.drawable.btn_unselected);
        }
    }



}
