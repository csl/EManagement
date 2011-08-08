package com.example.android.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class Fail extends Activity {
	
	int my_id;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fail);

        //fetch data from Booking.java
        Bundle bunde = this.getIntent().getExtras();	    
        my_id = bunde.getInt("myid"); 
        
        Button b1=(Button) findViewById(R.id.widget34);
        b1.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
              Bundle bundle = new Bundle();
              bundle.putInt("myid", my_id);
        		
        		Intent intent = new Intent();
        		intent.setClass(Fail.this,Booking.class);
        		
        		intent.putExtras(bundle);
        		        		
        		startActivity(intent);
        		Fail.this.finish();
        	}
        });
        
        
        
        
        
    }
}