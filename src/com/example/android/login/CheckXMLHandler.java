package com.example.android.login;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CheckXMLHandler extends DefaultHandler
{
	//Check Data
	private final static int BOOKINGNO = 1;
	private final static int MOBILE = 2;
	private final static int ROOMPRICE = 3;
	//Room Set
	private final static int ROOMNO = 4;
	private final static int RNAME = 5;
	private final static int RROOMPRICE = 6;
	
	private CheckXMLContainer cxc;
	private CheckXMLStruct cxs;
	
	private int type;
	private boolean room_set;

	public CheckXMLContainer getContainer() 
	{
		return cxc;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String s = new String(ch, start, length);
		switch (type) 
		{
		case BOOKINGNO:
			cxc.setBookingno(s);
			type = 0;
			break;
		case MOBILE:
			cxc.setMobile(s);
			type = 0;
			break;
		case ROOMPRICE:
			cxc.setRoomprice(s);
			type = 0;
			break;
		//Room Set
		case ROOMNO:
			cxs.setId(s);
			type = 0;
			break;
		case RNAME:
			cxs.setName(s);
			type = 0;
			break;
		case RROOMPRICE:
			cxs.setRoomprice(s);
			type = 0;
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		//end rent
		if (localName.toUpperCase().equals("PRICE")) 
		{
			//room set
			if (room_set)
			{
				cxc.addRoomXMLItem(cxs);
				room_set=false;
			}
			return;
		}
}

	@Override
	public void startDocument() throws SAXException 
	{
		cxc = new CheckXMLContainer();
		cxs = new CheckXMLStruct();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException 
	{
		
		//new item: BOOKINGNO
		if (localName.toUpperCase().equals("BOOKINGNO")) 
		{
			type = BOOKINGNO;
			return;
		}
		//new item: BOOKINGNO
		else if (localName.toUpperCase().equals("MOBILE")) 
		{
			type = MOBILE;
			return;
		}		
		//new item: BOOKINGNO
		else if (localName.toUpperCase().equals("ROOMPRICE")) 
		{
			//room set
			type = ROOMPRICE;
			return;
		}		
		//new item: RoomSet ROOMNO
		else if (localName.toUpperCase().equals("ROOMNO")) 
		{
			cxs = new CheckXMLStruct();
			room_set=true;
			type = ROOMNO;
			return;
		}
		else if (localName.toUpperCase().equals("RNAME")) 
		{
			type = RNAME;
			return;
		}
		else if (localName.toUpperCase().equals("PRICE")) 
		{
				type = RROOMPRICE;				
			return;
		}
		type = 0;
	}

}