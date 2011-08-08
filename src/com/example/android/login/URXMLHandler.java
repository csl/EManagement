package com.example.android.login;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class URXMLHandler extends DefaultHandler
{
	//Rent
	private final static int BOOKINGNO = 1;
	
	//UnRent
	private final static int ROOMNO = 2;
	private final static int RNAME = 3;
	
	private RentXMLStruct rxs;
	private UnRentXMLStruct urxs;
	private URXMLContainer urxc;
	
	private int type;
	private boolean urxs_a;
	private boolean urxs_b;

	public URXMLContainer getContainer() 
	{
		return urxc;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String s = new String(ch, start, length);
		switch (type) 
		{
		case BOOKINGNO:
			rxs.setId(s);
			type = 0;
			break;
		case ROOMNO:
			urxs.setroomno(s);
			type = 0;
			break;
		case RNAME:
			urxs.setName(s);
			type = 0;
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (localName.toUpperCase().equals("HIRE")) 
		{
			urxs_a=false;			
		}

		//end rent
		if (localName.toUpperCase().equals("BOOKINGNO")) 
		{
			urxc.addRXMLItem(rxs);	
		
		}

		//end unrent
		if (localName.toUpperCase().equals("RNAME")) 
		{
			urxc.addURXMLItem(urxs);
		}
		
		if (localName.toUpperCase().equals("HIRING")) 
		{
			urxs_b=false;
		}
}

	@Override
	public void startDocument() throws SAXException 
	{
		urxc = new URXMLContainer();
		rxs = new RentXMLStruct();
		urxs = new UnRentXMLStruct();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException 
	{
		//new item: Rent BOOKINGNO
		if (localName.toUpperCase().equals("BOOKINGNO")) 
		{
			rxs = new RentXMLStruct();
			type = BOOKINGNO;
			return;
		}
		
		//new item: UNRent ROOMNO
		else if (localName.toUpperCase().equals("ROOMNO")) 
		{
			urxs = new UnRentXMLStruct();
			type = ROOMNO;
			return;
		}
		else if (localName.toUpperCase().equals("RNAME")) 
		{
			type = RNAME;
			return;
		}
		type = 0;
	}

}