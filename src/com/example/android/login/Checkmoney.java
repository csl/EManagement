package com.example.android.login;

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

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class Checkmoney extends Activity 
{
	//pass use
	private Bundle bunde;
	private Intent intent;
	private int my_id;
	private String string_id;
	private int check_num;

	//today
	String szToday;
	
	//XML use
	private CheckMoneyContainer cmc;
	
	private ArrayList<HashMap<String, Object>> checkmoney_list;

	private ListView pay_view;
	private String total_info;
	
	private CheckXMLContainer check_id;	
    private StatusXMLStruct status;
    private String handler_1;
    private String handler_2;
    private String handler_3;
	
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkmoney);
        
        //this is check: 3
        check_num = 3;
        
        //fetch data form Inquire    
        intent=this.getIntent();
        bunde = intent.getExtras();
        
        // 取得Bundle物件中的資料  (傳參數用)
        my_id = bunde.getInt("myid");  
        
        //get current data
    	Date today = Calendar.getInstance().getTime();
    	DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        szToday = df.format(today);      

        string_id = Integer.toString(my_id);

        //reading XML
		//Create url: id=1&odate=2010/09/27
        //String uriAPI = "http://60.251.49.27/service/get_customer06.jsp?id=" + string_id
        																	//+ "&odate=" + szToday;

        String uriAPI = "http://140.116.39.114:44441/checkmoney.xml";
        //openOptionsDialog(uriAPI);
        //XML Parser
        URL url = null;
        try{
            url = new URL(uriAPI);
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
                  
            XMLReader xr = sp.getXMLReader();
                  
            //Using login handler for xml
            CheckMoneyHandler myHandler = new CheckMoneyHandler();
            xr.setContentHandler(myHandler);
            //open connection
            xr.parse(new InputSource(url.openStream()));
            cmc = myHandler.getContainer();
            
        }
        catch(Exception e){
        	openOptionsDialog("login fail:"+e);
        }

        //Display: create ListView class
        pay_view=(ListView)findViewById(R.id.listview);
        
        checkmoney_list = cmc.getCheckMoneyItems();
        
        SimpleAdapter listitemAdapter=new SimpleAdapter(this,  
        										checkmoney_list, 
        										R.layout.no_listview_style,
        										new String[]{"ItemTitle","ItemText"}, 
        										new int[]{R.id.topTextView,R.id.bottomTextView}  
        										);  
        
        pay_view.setAdapter(listitemAdapter);              
        pay_view.setOnItemClickListener(new OnItemClickListener() 
        {          
        	   @Override  
        	   public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
        	     long arg3) {
        		   
	           		//Check info       		   
	           	    //fetch who click
	               	Map<String, Object> user1 =  checkmoney_list.get(arg2);
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
					 intent.setClass(Checkmoney.this ,Check.class);
					 
					 intent.putExtras(bundle);
						
					 startActivity(intent);
					 Checkmoney.this.finish(); 
        	   }  
        });

        Button b3=(Button) findViewById(R.id.widget444);
        b3.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
        		Intent intent = new Intent();
        		intent.setClass(Checkmoney.this,Checknomoney.class);
        		
        		startActivity(intent);
        		Checkmoney.this.finish();
        	}
        });
        
        
        Button b2=(Button) findViewById(R.id.widget4441);
        b2.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
 	           Bundle bundle = new Bundle();
	           bundle.putInt("myid", my_id);
        		        		
        	   Intent intent = new Intent();
        	   intent.setClass(Checkmoney.this,Checknomoney.class);
        		
        	   intent.putExtras(bundle);
        		
        	   startActivity(intent);
        	   Checkmoney.this.finish();
        		
        	}
        });
        
        //retrutn main menu
        Button b1=(Button) findViewById(R.id.widget4442);
        b1.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
    	           Bundle bundle = new Bundle();
    	           bundle.putInt("myid", my_id);
            		        		
            	   Intent intent = new Intent();
            	   intent.setClass(Checkmoney.this,Function.class);
            		
            	   intent.putExtras(bundle);
            		
            	   startActivity(intent);
            	   Checkmoney.this.finish();
        	}
        }); 
    }
    
    //error message
    private void openOptionsDialog(String info)
	{
	    new AlertDialog.Builder(this)
	    .setTitle("Inquire")
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