package com.example.android.login;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler
{
	
	  private boolean h_id = false;
	  private boolean h_name = false;
	  private boolean address = false;
	  private boolean photo_name = false;
	  private boolean h_phone1 = false;
	  private boolean h_fax1 = false;
	  
	  private XMLDataSet myParsedExampleDataSet = new XMLDataSet();
	  
	  public XMLDataSet getParsedData() {
		    
	       return this.myParsedExampleDataSet;
	  }
	
	  
	  public void startDocument() throws SAXException {
	       this.myParsedExampleDataSet = new XMLDataSet();
	       
	  }

	  @Override
	  public void endDocument() throws SAXException {
	       // Nothing to do
	  }


	  @Override
	  public void startElement(String namespaceURI, String localName,
	            String qName, Attributes atts) throws SAXException {
	    if (localName.equals("RECORD")) {
		    if (localName.equals("H_ID")) {
		      //this.myParsedExampleDataSet.setId(11);
		      this.h_id = true;
		       }else if (localName.toUpperCase().equals("H_NAME")) {
		            this.h_name = true;
		       }else if (localName.toUpperCase().equals("ADDRESS")) {
		            this.address = true;
		       }else if (localName.toUpperCase().equals("PHOTO_NAME")) {
		            this.photo_name = true;
		       }else if (localName.toUpperCase().equals("H_PHONE1")) {
		           this.h_phone1 = true;
		       }else if (localName.toUpperCase().equals("H_FAX1")) {
		           this.h_fax1 = true;
		       }
	    }
	    
	  }
	  

	  @Override
	  public void endElement(String namespaceURI, String localName, String qName)
	           throws SAXException {
	    if (localName.equals("H_ID")) {
	      this.h_id = false;
	 }else if (localName.toUpperCase().equals("H_NAME")) {
	      this.h_name = false;
	 }else if (localName.toUpperCase().equals("ADDRESS")) {
	      this.address = false;
	 }else if (localName.toUpperCase().equals("PHOTO_NAME")) {
	      this.photo_name = false;
	 }else if (localName.toUpperCase().equals("H_PHONE1")) {
	     this.h_phone1 = false;
	 }else if (localName.toUpperCase().equals("H_FAX1")) {
	     this.h_fax1 = false;
	 }
	}
	  

	  @Override
	 public void characters(char ch[], int start, int length) {
	    if(this.h_id){
	      myParsedExampleDataSet.setId(Integer.parseInt(new String(ch,start,length)));
	 }
	       if(this.h_name){
	       myParsedExampleDataSet.setName(new String(ch,start,length));
	  }
	       if(this.address){
	           myParsedExampleDataSet.setAddress( new String(ch,start,length));
	      }
	       if(this.photo_name){
	           myParsedExampleDataSet.setPhoto_name( new String(ch,start,length));
	      }
	       if(this.h_phone1){
	           myParsedExampleDataSet.setH_phone1( new String(ch,start,length));
	      }
	       if(this.h_fax1){
	           myParsedExampleDataSet.setH_fax1( new String(ch,start,length));
	      }
	 }

}
