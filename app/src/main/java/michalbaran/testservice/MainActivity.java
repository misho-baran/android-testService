package michalbaran.testservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_start  = null;
    private Button btn_stop = null;
    private Button btn_exit = null;
    private TextView textView_info = null;
    private CheckBox checkBox_autoexit = null;

    private void init_component(){
        //init obj component
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        textView_info = (TextView) findViewById(R.id.textView_info);
        checkBox_autoexit = (CheckBox) findViewById(R.id.checkBox_autoexit);

        // add component to listener
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_component();
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {

            case R.id.btn_exit:
                finish();
                System.exit(0);
                break;

            case R.id.btn_start:
                textView_info.setText("Service started.");
                startService(new Intent(getBaseContext(), MyService.class));
                if(checkBox_autoexit.isChecked()){
                    finish();
                    System.exit(0);
                }
                break;

            case R.id.btn_stop:
                textView_info.setText("Service stoped.");
                stopService(new Intent(getBaseContext(), MyService.class));
                break;

            default:
                break;
        }

    }
}
