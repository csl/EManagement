package com.example.android.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class Success extends Activity 
{
	private Button info_list;
	private int my_id;
	
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);
        
       //fetch data from Booking.java
        Bundle bunde = this.getIntent().getExtras();	    
	    String today_data = bunde.getString("today_data");  
        my_id = bunde.getInt("myid");

        // fix Button for 
	    info_list = (Button) findViewById(R.id.bt_info);
	    info_list.setText("已於 "+today_data+" 新增一筆訂單!!!!");
	    

        Button b1=(Button) findViewById(R.id.widget34);
        b1.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
              Bundle bundle = new Bundle();
              bundle.putInt("myid", my_id);
        		
        		Intent intent = new Intent();
        		intent.setClass(Success.this,Function.class);
              
        		intent.putExtras(bundle);

        		startActivity(intent);
        		Success.this.finish();
        	}
        });
        

        
}
}