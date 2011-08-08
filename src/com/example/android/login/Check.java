package com.example.android.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

public class Check extends Activity {

	private TextView list_infoview;
	private Bundle bunde;
	private Intent intent;
	private int my_id;
	private int check_num;
	
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check);
       
        //fetch data form Inquire    
        intent=this.getIntent();
        bunde = intent.getExtras();
        
        String total_info = bunde.getString("total_info");
        check_num = bunde.getInt("check_num");
        my_id = bunde.getInt("myid");
        
        //Load list info
        list_infoview = (TextView) findViewById(R.id.list_infoview);     
        list_infoview.setText(total_info);
        
        //return to main menu
        Button b2=(Button) findViewById(R.id.widget44);
        b2.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
        	}
        });
    }
}
