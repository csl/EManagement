package com.example.android.login;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CheckMoneyHandler extends DefaultHandler
{
	//tag
	private final static int BOOKINGNO = 1;
	private final static int BDATE = 2;
	private final static int ROOMPRICE = 3;
	
	private CheckMoneyXMLStruct cmxs;
	private CheckMoneyContainer cmcs;
	
	private int type;

	public CheckMoneyContainer getContainer() 
	{
		return cmcs;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String s = new String(ch, start, length);
		switch (type) 
		{
		case BOOKINGNO:
			cmxs.setId(s);
			type = 0;
			break;
		case BDATE:
			cmxs.setDate(s);
			type = 0;
			break;
		case ROOMPRICE:
			cmxs.setPrice(s);
			type = 0;
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (localName.toUpperCase().equals("ROOMPRICE")) 
		{
			cmcs.addRXMLItem(cmxs);	
		}
	}

	@Override
	public void startDocument() throws SAXException 
	{
		cmcs = new CheckMoneyContainer();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException 
	{
		//new item: Rent BOOKINGNO
		if (localName.toUpperCase().equals("BOOKINGNO")) 
		{
			cmxs = new CheckMoneyXMLStruct();
			type = BOOKINGNO;
			return;
		}
		
		//new item: UNRent ROOMNO
		else if (localName.toUpperCase().equals("BDATE")) 
		{
			type = BDATE;
			return;
		}
		else if (localName.toUpperCase().equals("ROOMPRICE")) 
		{
			type = ROOMPRICE;
			return;
		}
		type = 0;
	}

}