package com.example.android.login;

import java.io.IOException;
import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.content.Intent;
import android.widget.EditText;

import android.util.Xml.Encoding;

import android.widget.Toast;

public class BookingData extends Activity 
{
	//login use
    private EditText id_name;
    private EditText id_mobile;
    private EditText id_email;
    
    private String error_booking;

    //pass use
    private String today_data;
    private String bookingdata;
    private ArrayList<String> mybooking_list;
    private int my_id;
    
    //data use
    private String name;
    private String mobile;    
    private String email;

    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookingdata);

        error_booking  = (String) this.getResources().getText(R.string.booking_null);
        //fetch data from Booking.java
        Bundle bunde = this.getIntent().getExtras();	    
        
        bookingdata = bunde.getString("bookingdata");
        today_data = bunde.getString("today_data");  
        my_id = bunde.getInt("myid");
        mybooking_list = bunde.getStringArrayList("mybooking_list");

        Button b2=(Button) findViewById(R.id.id_next);
        b2.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{        		
          	  id_name = (EditText) findViewById(R.id.id_name);
        	  id_mobile = (EditText) findViewById(R.id.id_mobile);
        	  id_email = (EditText) findViewById(R.id.id_email);

        	  name = id_name.getText().toString();
        	  mobile = id_mobile.getText().toString();
        	  email = id_email.getText().toString();
        	  
        	  //account or password is NULL
              if (name.equals("") || mobile.equals("") || email.equals(""))
              {
            	  openOptionsDialog(error_booking);
                  
                  return;
              }
             
              //booking data, for confirm
              Bundle bundle = new Bundle();
              bundle.putString("bookingdata",bookingdata);
              bundle.putInt("myid", my_id);
              bundle.putString("today_data",today_data);  
              bundle.putStringArrayList("mybooking_list",mybooking_list);

              //customer data
              bundle.putString("name",name);  
              bundle.putString("mobile",mobile);  
              bundle.putString("email",email);  

              Intent intent = new Intent();
              intent.setClass(BookingData.this,Confirm.class);
          	  
              //Bundle, assign Intent
           	  intent.putExtras(bundle);

        	  startActivity(intent);
        	  BookingData.this.finish();
        	}
        });
        
        Button b3=(Button) findViewById(R.id.id_back);
        b3.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{        		
              //booking data, for confirm
              Bundle bundle = new Bundle();
              bundle.putInt("myid", my_id);

              Intent intent = new Intent();
              intent.setClass(BookingData.this,Confirm.class);
          	  
              //Bundle, assign Intent
           	  intent.putExtras(bundle);

        	  startActivity(intent);
        	  BookingData.this.finish();
        	}
        });
    }
    
      //error message
	  private void openOptionsDialog(String info)
	  {
	    new AlertDialog.Builder(this)
	    .setTitle(R.string.login_1)
	    .setMessage(info)
	    .setPositiveButton("OK",
	        new DialogInterface.OnClickListener()
	        {
	         public void onClick(DialogInterface dialoginterface, int i)
	         {
	         }
	         }
	        )
	    .show();
	  }
}
