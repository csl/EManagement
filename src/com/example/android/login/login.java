package com.example.android.login;

import java.io.IOException;
import java.net.URL;
import javax.xml.parsers.*;

import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.content.Intent;
import android.widget.EditText;

import android.util.Xml.Encoding;

import android.widget.Toast;

public class login extends Activity {
	
	//login use
    private EditText id_account;
    private EditText id_password;
    private LoginXMLStruct data;
    private String error_login;
    private String error_login_1;
    
    public ProgressDialog myDialog = null;
    
    //verify use
    String account;
	String password;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        error_login  = (String) this.getResources().getText(R.string.ps_error);
        error_login_1 = (String) this.getResources().getText(R.string.ps_null);
        
        data = new LoginXMLStruct();

        Button b2=(Button) findViewById(R.id.widget40);
        b2.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v)
        	{        		
        	  
        	  final CharSequence strDialogTitle = getString(R.string.login_1);
      	      final CharSequence strDialogBody = getString(R.string.logining);

        		
        	  id_account = (EditText) findViewById(R.id.id_account);
        	  id_password = (EditText) findViewById(R.id.id_password);
        		
        	  account = id_account.getText().toString();
        	  password = id_password.getText().toString();
        	  
        	  //account or password is NULL
              if (account.equals("") || password.equals(""))
              {
            	  openOptionsDialog(error_login_1);
                  
                  return;
              }
    	      
    	      //Progress
              
    	      myDialog = ProgressDialog.show
    	                 (
    	                   login.this,
    	                   strDialogTitle,
    	                   strDialogBody, 
    	                   true
    	                 );
    	      
    	      new Thread()
    	      { 
    	        public void run()
    	        { 
    	          try
    	          { 
    	        	  /*
    	        		//Create url
    	                String uriAPI = "http://60.251.49.27/service/get_customer01.jsp?id=" + account + "&pw=" + password;
    	        	  
    	                //String uriAPI = "http://140.116.39.114:44441/login.xml";
    	               
    	                URL url = null;
    	                try{
    		                url = new URL(uriAPI);
    		                SAXParserFactory spf = SAXParserFactory.newInstance();
    		                SAXParser sp = spf.newSAXParser();
    			                  
    		                XMLReader xr = sp.getXMLReader();
    			                  
    		                //Using login handler for xml
    		                LoginXMLHandler myHandler = new LoginXMLHandler();
    		                xr.setContentHandler(myHandler);
    		                
    		                
    		                //open connection
    		                xr.parse(new InputSource(url.openStream()));
    		        		//verify OK
    		      	        data = myHandler.getParsedData();
    	                }
    	                catch(Exception e){
    	                	openOptionsDialog("login fail:"+e);
    	                	openOptionsDialog(error_login_1);
    	    	            Toast.makeText(login.this, getString(R.string.link_error),
    	    	                    Toast.LENGTH_SHORT).show();
    	    	            return;
    	                }
    	                */
        	}
    	    catch (Exception e)
    	          {
    	             e.printStackTrace();
    	             Toast.makeText(login.this, getString(R.string.link_error),
    	                    Toast.LENGTH_SHORT).show();
    	          }
    	          finally
    	          {
    	        	 try
    	        	  {
	    	             // if (data.getStatus().equals("Y"))
	    	              {
	    	              	
	    	                  Bundle bundle = new Bundle();
	    	                  bundle.putInt("myid", data.getId());
	
	    	          		Intent intent = new Intent();
	    	          		intent.setClass(login.this, Function.class);
	
	    	          		/*Bundle assign Intent*/
	    	                 	intent.putExtras(bundle);
	    	          		
	    	          		startActivity(intent);
	    	          		login.this.finish();
	    	              }
	    	            //  else
	    	              //{
	    	              //	openOptionsDialog(error_login_1);
	    	              	//return;
	    	              //}
    	        	  }
    	        	 catch (Exception err)
    	        	 {
    	    	            err.printStackTrace();
    	    	            Toast.makeText(login.this, getString(R.string.link_error),
    	    	                    Toast.LENGTH_SHORT).show();   	        		  
    	        	 }
    	        	  
    	        	 myDialog.dismiss();
    	          }      	          
    	        }
    	      }.start();        		
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
