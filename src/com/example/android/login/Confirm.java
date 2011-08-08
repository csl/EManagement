package com.example.android.login;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.content.DialogInterface;
import android.content.Intent;


public class Confirm extends Activity {
	
	private String today_data;
	private TextView list_infoview;
	
	//pass use
	private int my_id;
	private String string_id;
    private String name;
    private String mobile;    
    private String email;

	//list use
    private  ArrayList<String> mybooking_list;
    private BookStatusXMLContainer bookstatus;
    private ArrayList<HashMap<String, Object>> status_list;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);

        //fetch data from Booking.java
        Bundle bunde = this.getIntent().getExtras();	    
        String bookingdata = bunde.getString("bookingdata");
        
        today_data = bunde.getString("today_data");  
        
        my_id = bunde.getInt("myid");

        string_id = Integer.toString(my_id);
        
        mybooking_list = bunde.getStringArrayList("mybooking_list");
        
        name = bunde.getString("name");
        mobile = bunde.getString("mobile");
        email = bunde.getString("email");

        //display info (booking data) for user
        //load list info
        list_infoview = (TextView) findViewById(R.id.list_bookingview);
        
        String bdata = "房客姓名：" + name + "\n"
                     + "電話：" + mobile + "\n"
                     + "email：" + email + "\n"
                     + bookingdata;
                
        //total info
        list_infoview.setText(bdata);	    
	    
        Button b1=(Button) findViewById(R.id.widget44);
        b1.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
        		String app_id="";
        		boolean sucess=true;
                //Sure, so send info for url
                for(int i = 0; i < mybooking_list.size(); i++)
                {
                	if (i == mybooking_list.size()-1)
                	{
                		app_id = app_id +  mybooking_list.get(i).trim()
;
                	}
                	else
                	{
                		app_id = app_id + mybooking_list.get(i).trim() + ",";
                	}
                }
                
              String uriAPI = "http://60.251.49.27/service/get_customer0501.jsp?"
	                 + "id=" + string_id 
	                 + "&odate=" + today_data
	                 + "&roomno=" + app_id
	                 + "&username=" + name
              		 + "&mobile=" + mobile
              		 + "&email=" + email;
          
              //openOptionsDialog(uriAPI);
              //String uriAPI = "http://140.116.39.114:44441/bs.xml";
              
              //XML Parser
              URL url = null;
              try{
                  url = new URL(uriAPI);
                  SAXParserFactory spf = SAXParserFactory.newInstance();
                  SAXParser sp = spf.newSAXParser();                        
                  XMLReader xr = sp.getXMLReader();
                        
                  //Using login handler for xml
                  BookStatusXMLHandler myHandler = new BookStatusXMLHandler();
                  xr.setContentHandler(myHandler);
                  //open connection
                  xr.parse(new InputSource(url.openStream()));
                  bookstatus = myHandler.getContainer();
                  
              }
              catch(Exception e){
              	openOptionsDialog("BOOKING fail:"+e);
              }
              
                status_list = bookstatus.getBookingItems();
              
              sucess=true;
              for(int i = 0; i < status_list.size(); i++)
              {                    
                  Map<String, Object> user1 =  status_list.get(i);
                  String b_status = (String) user1.get("ItemStatus");

                  if (b_status.equals("1"))
                  {                	  
                	  sucess = false;
                  }
              }
              
              //OK
              if (sucess == true)
              {
	        	  //send data for Success.java
	              Bundle bundle = new Bundle();
	              bundle.putString("today_data",today_data);             
		          bundle.putInt("myid", my_id);
	
	        	  Intent intent = new Intent();
	        	  intent.setClass(Confirm.this,Success.class);
	        	  intent.putExtras(bundle);
	
	        	  startActivity(intent);
	        	  Confirm.this.finish();
              }
              else
              {
	        	  //send data for Fail.java
	              Bundle bundle = new Bundle();
		          bundle.putInt("myid", my_id);
	
	        	  Intent intent = new Intent();
	        	  intent.setClass(Confirm.this,Fail.class);
	        	  intent.putExtras(bundle);
	
	        	  startActivity(intent);
	        	  Confirm.this.finish();
            	  
              }
        }
        
        });
   
        
        Button b2=(Button) findViewById(R.id.widget45);
        b2.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
  	           Bundle bundle = new Bundle();
	           bundle.putInt("myid", my_id);

        		Intent intent = new Intent();
        		intent.setClass(Confirm.this,Fail.class);
        		
           	intent.putExtras(bundle);
           	
        		startActivity(intent);
        		Confirm.this.finish();
        	}
        });
	}
	
	   //error message
    private void openOptionsDialog(String info)
	{
	    new AlertDialog.Builder(this)
	    .setTitle("Confirm")
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