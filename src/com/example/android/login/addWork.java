package com.example.android.login;

import java.util.ArrayList;
import java.util.HashMap;

import java.io.IOException; 
import java.net.URL;
import javax.xml.parsers.*;
import java.text.DateFormat;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
	
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.content.DialogInterface;
import android.content.Intent;


public class addWork extends Activity {
	
	private TextView today_date;
	private Spinner work_id;
	private EditText today_num;
	private TextView unit;

	//today
	String szToday;
	
	//XML use
	private URXMLContainer ur;
	private CheckXMLContainer checkd;
	
	private ArrayList<HashMap<String, Object>> Rent_Info;
	private ArrayList<HashMap<String, Object>> UnRent_Info;
	
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inquire);


        //get current data
    	Date today = Calendar.getInstance().getTime();
    	DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        szToday = df.format(today);
        //display
        today_date = (TextView) findViewById(R.id.today_date);
        today_date.setText(szToday);
        work_id = (Spinner) findViewById(R.id.work_id);
        today_num = (EditText) findViewById(R.id.today_num);
        unit = (TextView) findViewById(R.id.unit);

       /*
        string_id = Integer.toString(my_id);

        //reading XML
		//Create url: id=1&odate=2010/09/27
        String uriAPI = "http://60.251.49.27/service/get_customer02.jsp?id=" + string_id
        														  + "&odate=" + szToday;
        //openOptionsDialog(uriAPI);
        //填入測試IP
        //String uriAPI = "http://140.116.39.114:44441/inquire.xml";
        
        URL url = null;
        try{
            url = new URL(uriAPI);
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
                  
            XMLReader xr = sp.getXMLReader();
                  
            //Using login handler for xml
            URXMLHandler myHandler = new URXMLHandler();
            xr.setContentHandler(myHandler);
            //open connection
            xr.parse(new InputSource(url.openStream()));
            ur = myHandler.getContainer();
        }
        catch(Exception e){
        	openOptionsDialog("Inquire fail:"+e);
        	return;
        }
        */
        
        //check function
        Button b1=(Button) findViewById(R.id.ADD);
        b1.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
        	}
        });
        //check function
        Button b2=(Button) findViewById(R.id.EXIT);
        b2.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
	           Intent intent = new Intent();
        	   intent.setClass(addWork.this, Function.class);
        		
        	   startActivity(intent);
        	   addWork.this.finish();
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

