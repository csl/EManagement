package com.example.android.login;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import java.lang.Integer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
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
import android.view.ViewGroup;

public class Booking extends Activity implements OnItemClickListener
{
	
	//pass use
	private Bundle bunde;
	private Intent intent;
	private int my_id;
	private String string_id;

	//date use
	String szToday;
	
	//display use
	BookingXMLContainer bnm;
	private ArrayList<HashMap<String, Object>> Booking_list;
	private ListView booking_view;
	private MyAdapter mSimpleAdapter;
	
	//String use
	private String date_r;
	private String text_1;
	private String text_2;
	private String text_3;
	private String error_booking;
	
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking);

        //Fetch String
        date_r = (String) this.getResources().getText(R.string.date_r);
        text_1 = (String) this.getResources().getText(R.string.total_1);
        text_2 = (String) this.getResources().getText(R.string.total_2);
        text_3 = (String) this.getResources().getText(R.string.total_3);
        error_booking = (String) this.getResources().getText(R.string.error_booking);
        
        //Fetch data form Inquire    
        intent=this.getIntent();
        bunde = intent.getExtras();
        my_id = bunde.getInt("myid"); 
        
        //Date
    	Date today = Calendar.getInstance().getTime();
    	DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        szToday = df.format(today);

        string_id = Integer.toString(my_id);
        
        String uriAPI = "http://60.251.49.27/service/get_customer05.jsp?id="
        	             +  string_id + "&odate=" + szToday;

        //String uriAPI = "http://140.116.39.114:44441/bl.xml";

        //XML Parser
        URL url = null;
        try{
            url = new URL(uriAPI);
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
                  
            XMLReader xr = sp.getXMLReader();
                  
            //Using login handler for xml
            BookingXMLHandler myHandler = new BookingXMLHandler();
            xr.setContentHandler(myHandler);
            //open connection
            xr.parse(new InputSource(url.openStream()));
            bnm = myHandler.getContainer();
            
        }
        catch(Exception e){
        	openOptionsDialog("login fail:"+e);
        }

        //Display
        //Update name, date
        String HotelName = bnm.getName();
        Button display_name=(Button) findViewById(R.id.Name_Display);
        display_name.setText("                   "+HotelName);
        Button display_date=(Button) findViewById(R.id.today);
        display_date.setText(szToday);

        
        Booking_list = bnm.getBookingItems();
                
        booking_view=(ListView)findViewById(R.id.bookview);
        
        mSimpleAdapter = new MyAdapter(this, Booking_list, R.layout.listview_style_1, 
        		new String[]{"ItemTitle","ItemText"}, 
				new int[]{R.id.topTextView,R.id.bottomTextView});  
        
        booking_view.setAdapter(mSimpleAdapter);  
        booking_view.setOnItemClickListener(this);          
/*        
        SimpleAdapter listitemAdapter=new SimpleAdapter(this,  
        										Booking_list, 
        										R.layout.listview_style_1,
        										new String[]{"ItemTitle","ItemText"}, 
        										new int[]{R.id.topTextView,R.id.bottomTextView}  
        										);  
     
        booking_view.setAdapter(listitemAdapter);   
  */ 
        
        Button b6=(Button) findViewById(R.id.Name_Display);
        b6.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
        	}
        });
/*        
        Button b2=(Button) findViewById(R.id.Name_Display);
        b2.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
        		Intent intent = new Intent();
        		intent.setClass(Booking.this,Inquiremonth.class);
        		
        		startActivity(intent);
        		Booking.this.finish();
        	}
        });
 */
        
        //Return main menu
        Button b3=(Button) findViewById(R.id.widget49);
        b3.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
              Bundle bundle = new Bundle();
	           bundle.putInt("myid", my_id);

        		
        		Intent intent = new Intent();
        		intent.setClass(Booking.this,Function.class);

        		/*Bundle, assign Intent*/
        		intent.putExtras(bundle);

        		
        		startActivity(intent);
        		Booking.this.finish();
        	}
        });
        
        //booking
        Button b4=(Button) findViewById(R.id.widget48);
        b4.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
              String today_data=szToday;
              String bookingdata= date_r + today_data + "\n";
              int money=0, count=0;
              ArrayList<String> mybooking_list = new ArrayList<String>();
              
              //mSimpleAdapter.map.get(position)
              //checkBox.setChecked(map.get(position))
              for(int i = 0; i < mSimpleAdapter.map.size(); i++)
              {
                    View view = booking_view.getChildAt(i);
                    
                    if (mSimpleAdapter.map.get(i) == true)
                    {
                    	//be checked info: data
	                	Map<String, Object> data =  Booking_list.get(i);
                    	//openOptionsDialog((String) data.get("ItemTitle"));
	                	bookingdata = bookingdata + data.get("ItemTitle") + "\n";
	                	String m = (String) data.get("ItemMoney");
	                	money = money + Integer.parseInt(m);
	                	mybooking_list.add((String) data.get("ItemText"));
	                	count++;
                    }
              }         
              
              //Error handler: user no choice
              if (count == 0)
              {
            	  openOptionsDialog(error_booking);
            	  return;
              }
              
              //combine booking info
              bookingdata = bookingdata + "\n\n" + text_1 + " " + count 
              							+ " " + text_2;
              
              bookingdata = bookingdata + money + "   " + text_3;
              
             //booking data, for BookingData
              Bundle bundle = new Bundle();
              bundle.putString("bookingdata",bookingdata);
              bundle.putInt("myid", my_id);
              bundle.putString("today_data",szToday);  
              bundle.putStringArrayList("mybooking_list",mybooking_list);

              Intent intent = new Intent();
              intent.setClass(Booking.this,BookingData.class);
          	  
              //Bundle, assign Intent
           	  intent.putExtras(bundle);

        	  startActivity(intent);
        	  Booking.this.finish();
        	}
        });     
	}

    @Override  
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
    {  
           CheckBox checkBox = (CheckBox) view.findViewById(R.id.CheckBox02);  
              
            checkBox.toggle();  
            
     	   //openOptionsDialog(Integer.toString(position));
              
            mSimpleAdapter.map.put(position, checkBox.isChecked());  
              
    }  
    public class MyAdapter extends SimpleAdapter {
        
        Map<Integer, Boolean> map;
       
        LayoutInflater mInflater;
       
        private List<? extends Map<String, ?>> mList;
       
        public MyAdapter(Context context, ArrayList<HashMap<String, Object>> data,
                        int resource, String[] from, int[] to) 
{
                super(context, data, resource, from, to);

                map = new HashMap<Integer, Boolean>();
                mInflater = LayoutInflater.from(context);
                mList = data;
                for(int i = 0; i < data.size(); i++) 
                {
                        map.put(i, false);
                }
        }
       
        @Override
        public int getCount() {
                return mList.size();
        }

        @Override
        public Object getItem(int position) {
                return position;
        }

        @Override
        public long getItemId(int position) {
                return position;
        }
       
        @Override
        public View getView(int position, View convertView, ViewGroup parent) 
{
                if(convertView == null) {
                        convertView = mInflater.inflate(R.layout.listview_style_1, null);
                }
                TextView tN = (TextView) convertView.findViewById(R.id.topTextView);
                tN.setText((String)mList.get(position).get("ItemTitle"));
               
                TextView tP = (TextView) convertView.findViewById(R.id.bottomTextView);
                tP.setText((String)mList.get(position).get("ItemText"));
               
                CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.CheckBox02);
               
                checkBox.setChecked(map.get(position));
               
                return convertView;
        }
       
}

	
	
    //error message
    private void openOptionsDialog(String info)
	{
	    new AlertDialog.Builder(this)
	    .setTitle(R.string.booking)
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
