package com.example.android.login;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BookingXMLHandler extends DefaultHandler
{
	//Check Data
	private final static int H_NAME = 1;	
	private final static int ROOMNO = 2;
	private final static int RNAME = 3;
	private final static int ROOMPRICE = 4;
	
	private BookingXMLContainer bmxc;
	private BookingXMLStruct bmxs;
	
	private int type;

	public BookingXMLContainer getContainer() 
	{
		return bmxc;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException 
	{
		String s = new String(ch, start, length);
		
		switch (type) 
		{
		case H_NAME:
			bmxc.setName(s);
			type = 0;			
		case ROOMNO:
			bmxs.setId(s);
			type = 0;
			break;
		case RNAME:
			bmxs.setName(s);
			type = 0;
			break;
		case ROOMPRICE:
			bmxs.setRoomprice(s);
			type = 0;
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		//end ROOMPRICE
		if (localName.toUpperCase().equals("ROOMPRICE")) 
		{
			bmxc.addBookingXMLItem(bmxs);
			return;
		}
}

	@Override
	public void startDocument() throws SAXException 
	{
		bmxc = new BookingXMLContainer();
		bmxs = new BookingXMLStruct();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException 
	{
		//new item: BOOKINGNO
		if (localName.toUpperCase().equals("H_NAME")) 
		{
			//bmxs = new BookingXMLStruct();
			type = H_NAME;
			return;
		}
		//new item: BOOKINGNO
		else if (localName.toUpperCase().equals("ROOMNO")) 
		{
			bmxs = new BookingXMLStruct();
			type = ROOMNO;
			return;
		}
		//new item: BOOKINGNO
		else if (localName.toUpperCase().equals("RNAME")) 
		{
			type = RNAME;
			return;
		}		
		//new item: BOOKINGNO
		else if (localName.toUpperCase().equals("ROOMPRICE")) 
		{
			type = ROOMPRICE;
			return;
		}
		
		type=0;
	}
}