package com.example.android.login;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LoginXMLHandler extends DefaultHandler
{
	  private boolean h_id = false;
	  private boolean h_name = false;
	  private boolean status = false;
	  
	  private LoginXMLStruct myParsedExampleDataSet = new LoginXMLStruct();
	  
	  public LoginXMLStruct getParsedData() 
	  {
		    
	       return this.myParsedExampleDataSet;
	  }
	  
	  public void startDocument() throws SAXException 
	  {
	       this.myParsedExampleDataSet = new LoginXMLStruct();
	       
	  }

	  @Override
	  public void endDocument() throws SAXException {
	       // Nothing to do
	  }

	  @Override
	  public void startElement(String namespaceURI, String localName,
	            String qName, Attributes atts) throws SAXException 
	  {
	       if (localName.equals("H_ID")) {
	    		this.h_id = true;	    		
	       }else if (localName.toUpperCase().equals("H_NAME")) {
	           this.h_name = true;
	       }
	       if (localName.toUpperCase().equals("STATUS")) {
	            this.status = true;
	       }
	  }
	  @Override
	  public void endElement(String namespaceURI, String localName, String qName)
	           throws SAXException {
	    if (localName.equals("H_ID")) {
	      this.h_id = false;
		 } 
	    if (localName.toUpperCase().equals("H_NAME")) {
		      this.h_name = false;
	    }
		      if (localName.toUpperCase().equals("STATUS")) {
		      this.status = false;
		}
	  }
	  

	  @Override
	 public void characters(char ch[], int start, int length) {
	    if(this.h_id){
	      myParsedExampleDataSet.setId(Integer.parseInt(new String(ch,start,length)));
	    }
	    else if(this.h_name){
	       myParsedExampleDataSet.setName(new String(ch,start,length));
	    }
	    else if(this.status){
	       myParsedExampleDataSet.setStatus( new String(ch,start,length));
	    }
	 }

}
