package com.example.android.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SimpleAdapter;
import android.content.Intent;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;  
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;   
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class Checknomoney extends Activity {
	
	private ListView nopay_view;
	public ProgressDialog myDialog = null;
	private String total_info;

	//pass use
	private Bundle bunde;
	private Intent intent;
	private int my_id;
	private int check_num;
	private String string_id;

	//date use
	String szToday;
	
	//display use
	ChecknoMoneyXMLContainer cnm;
	private ArrayList<HashMap<String, Object>> Checknomoney_list;
	private String status_list;
	
	//XML use
	private CheckXMLContainer check_id;	
    private StatusXMLStruct status;
    private String handler_1;
    private String handler_2;
    private String handler_3;

	//start
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checknomoney);
        
        //this is check: 2
        check_num = 2;
        
        status_list="";
        
        handler_1 = (String) this.getResources().getText(R.string.handler_1);
        handler_2 = (String) this.getResources().getText(R.string.handler_2);
        handler_3 = (String) this.getResources().getText(R.string.handler_3);
         
        //Fetch data form Inquire    
        intent=this.getIntent();
        bunde = intent.getExtras();

        my_id = bunde.getInt("myid");
        
        //Date
    	Date today = Calendar.getInstance().getTime();
    	DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        szToday = df.format(today); 

        string_id = Integer.toString(my_id);

        String uriAPI = "http://60.251.49.27/service/get_customer04.jsp?id=" + string_id 
                                                            + "&odate=" + szToday;

        //String uriAPI = "http://140.116.39.114:44441/cnm.xml";
        
        //XML Parser
        URL url = null;
        try{
            url = new URL(uriAPI);
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
                  
            XMLReader xr = sp.getXMLReader();
                  
            //Using login handler for xml
            ChecknoMoneyXMLHandler myHandler = new ChecknoMoneyXMLHandler();
            xr.setContentHandler(myHandler);
            //open connection
            xr.parse(new InputSource(url.openStream()));
            cnm = myHandler.getContainer();
            
        }
        catch(Exception e){
        	openOptionsDialog("login fail:"+e);
        }

        
        //Display: create ListView class
        nopay_view=(ListView)findViewById(R.id.listview);
        
        Checknomoney_list = cnm.getCMXSItems();
        
        SimpleAdapter listitemAdapter=new SimpleAdapter(this,  
        										Checknomoney_list, 
        										R.layout.check_listview_style,
        										new String[]{"ItemTitle","ItemText"}, 
        										new int[]{R.id.topTextView,R.id.bottomTextView}  
        										);  
        
        nopay_view.setAdapter(listitemAdapter);      
                       
        nopay_view.setOnItemClickListener(new OnItemClickListener() 
        {          
        	   @Override  
        	   public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
        	     long arg3) {
        		   
	           		//Check info       		   
	           	    //fetch who click
	               	Map<String, Object> user1 =  Checknomoney_list.get(arg2);
	               	String uriAPI = "http://60.251.49.27/service/get_customer03.jsp?id=" + string_id
	              	                                                +"&orderno=" + user1.get("ItemText") 
	            	                                                + "&odate=" + szToday;
	               	//String uriAPI = "http://140.116.39.114:44441/check.xml";	        
	                //openOptionsDialog(uriAPI);
	               	//total_info = uriAPI;
	           	    URL url = null;
	           	    try{
	           	        url = new URL(uriAPI);
	           	        SAXParserFactory spf = SAXParserFactory.newInstance();
	           	        SAXParser sp = spf.newSAXParser();
	           	                  
	           	        XMLReader xr = sp.getXMLReader();
	           	                  
	           	        //Using login handler for xml
	           	        CheckXMLHandler myHandler = new CheckXMLHandler();
	           	        xr.setContentHandler(myHandler);
	           	        
	           	        //open connection
	           	        xr.parse(new InputSource(url.openStream()));
	           	        check_id = myHandler.getContainer();        	            
	           	     }
	           	     catch(Exception e)
	           	     {
	           	        	openOptionsDialog("check fail:"+e);
	           	     }       		   
	
	                 total_info =  check_id.getCheckItems();
	                 
	                 //openOptionsDialog(total_info);
	             		 
					 Bundle bundle = new Bundle();
					 bundle.putString("total_info",total_info);
					 bundle.putInt("check_num",check_num);
		             bundle.putInt("myid", my_id);
						
					 Intent intent = new Intent();
					 intent.setClass(Checknomoney.this,Check.class);
						
					 intent.putExtras(bundle);
						
					 startActivity(intent);
					 Checknomoney.this.finish(); 
					      	            	  
        	   }  
        });
               
        //goto checkmoney
        Button b3=(Button) findViewById(R.id.widget71);
        b3.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
   	           Bundle bundle = new Bundle();
	           bundle.putInt("myid", my_id);
        		        		
        		Intent intent = new Intent();
        		intent.setClass(Checknomoney.this,Checkmoney.class);
        		
        	 	intent.putExtras(bundle);
        		
        		startActivity(intent);
        		Checknomoney.this.finish();
        	}
        });
        
        //goto checkmoney
        Button b1=(Button) findViewById(R.id.widget38);
        b1.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
   	           Bundle bundle = new Bundle();
	           bundle.putInt("myid", my_id);
        		        		
        		Intent intent = new Intent();
        		intent.setClass(Checknomoney.this,Function.class);
        		
        	 	intent.putExtras(bundle);
        		
        		startActivity(intent);
        		Checknomoney.this.finish();
        	}
        });
                
        //Press bottom for cancel rent
        Button b2=(Button) findViewById(R.id.widget70);
        b2.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
        		status_list="";
   	            //check info
   	            for(int i = 0; i < nopay_view.getChildCount(); i++)
   	            {
    	                View view = nopay_view.getChildAt(i);
    	                CheckBox cb = (CheckBox)view.findViewById(R.id.cancel_check);
    	                if (cb.isChecked() == false)
    	                {
    	                   //be checked info: data
    	                	Map<String, Object> data =  Checknomoney_list.get(i);
    	                	
    	                	String uriAPI = "http://60.251.49.27/service/get_customer0401.jsp?bookingno=" 
    	                		          + data.get("ItemText");       	                	
    	                    //openOptionsDialog(uriAPI);
    	                	//String uriAPI = "http://140.116.39.114:44441/cs.xml"; 
    	                	
           	               //send URL to Internet and return status
    		           	    URL url = null;
    		           	    try{
    		           	        url = new URL(uriAPI);
    		           	        SAXParserFactory spf = SAXParserFactory.newInstance();
    		           	        SAXParser sp = spf.newSAXParser();
    		           	                  
    		           	        XMLReader xr = sp.getXMLReader();
    		           	                  
    		           	        //Using login handler for XML
    		           	        StatusXMLHandler myHandler = new StatusXMLHandler();
    		           	        xr.setContentHandler(myHandler);
    		           	        
    		           	        //open connection
    		           	        xr.parse(new InputSource(url.openStream()));
    		           	        status = myHandler.getContainer();        	            
    		           	     }
    		           	     catch(Exception e)
    		           	     {
    		           	        	openOptionsDialog("login fail:"+e);
    		           	     }       		   
    	                
    		           	status_list = status_list 
    		           	                 + handler_1 
    		           	                 + data.get("ItemText");
    		           	     
    		           	if (status.getStatus().equals("0"))
    		           	    	status_list = status_list + handler_2+ "\n";        		           	    	 
    		           	else
     		           	    	status_list = status_list + handler_3 + "\n";     		           	    	 
   	                }
   	             } 
   	         openCancelCheckDialog(status_list);
  		        
        	}
        });
    }
	
    //error message
    private void openOptionsDialog(String info)
	{
	    new AlertDialog.Builder(this)
	    .setTitle(R.string.money)
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
    
    //error message
    private void openCancelCheckDialog(String info)
	{
	    new AlertDialog.Builder(this)
	    .setTitle(R.string.money)
	    .setMessage(info)
	    .setPositiveButton("OK",
	        new DialogInterface.OnClickListener()
	        {
	         public void onClick(DialogInterface dialoginterface, int i)
	         {
				 Bundle bundle = new Bundle();
				 bundle.putString("total_info",total_info);
				 bundle.putInt("check_num",check_num);
	             bundle.putInt("myid", my_id);
					
				 Intent intent = new Intent();
				 intent.setClass(Checknomoney.this,Checknomoney.class);
					
				 intent.putExtras(bundle);
					
				 startActivity(intent);
				 Checknomoney.this.finish();   		        
	         }
	         }
	        )
	    .show();
	}
}