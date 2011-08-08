package com.example.android.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.DialogInterface;
import android.content.Intent;


public class Function extends Activity {

	private Bundle bunde;
	private Intent intent;
	private int my_id;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function);
        
        Button b2=(Button) findViewById(R.id.widget69);
        b2.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
                Bundle bundle_next = new Bundle();                
                bundle_next.putInt("myid", my_id);

                Intent intent_nexts = new Intent();
        		intent_nexts.setClass(Function.this,addWork.class);

               	intent_nexts.putExtras(bundle_next);
        		startActivity(intent_nexts);
        	}
        });

        Button b1=(Button) findViewById(R.id.widget70);
        b1.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
                Bundle bundle_next = new Bundle();                
                bundle_next.putInt("myid", my_id);

                Intent intent_nexts = new Intent();
        		intent_nexts.setClass(Function.this,Checknomoney.class);

        		/*將Bundle物件assign給Intent*/
               	intent_nexts.putExtras(bundle_next);

        		startActivity(intent_nexts);
        		Function.this.finish();

        	}
        });
    
        Button b3=(Button) findViewById(R.id.widget72);
        b3.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
        		Intent intent = new Intent();
        		intent.setClass(Function.this,login.class);
        		
        		startActivity(intent);
        		Function.this.finish();
        	}
        });
        
        Button b4=(Button) findViewById(R.id.widget71);
        b4.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{
                Bundle bundle_next = new Bundle();                
                bundle_next.putInt("myid", my_id);

                Intent intent_nexts = new Intent();
        		intent_nexts.setClass(Function.this,Booking.class);

        		/*將Bundle物件assign給Intent*/
               	intent_nexts.putExtras(bundle_next);

        		startActivity(intent_nexts);
        		Function.this.finish();
        	}
        });
        
    }
    //error message
    private void openOptionsDialog(String info)
	{
	    new AlertDialog.Builder(this)
	    .setTitle("function")
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