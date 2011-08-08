package com.example.android.login;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ChecknoMoneyXMLHandler extends DefaultHandler
{
	//Check Data
	private final static int BOOKINGNO = 1;
	private final static int BDATE = 2;
	private final static int ROOMPRICE = 3;
	
	private ChecknoMoneyXMLContainer cmxc;
	private ChecknoMoneyXMLStruct cmxs;
	
	private int type;

	public ChecknoMoneyXMLContainer getContainer() 
	{
		return cmxc;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException 
	{
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
			cmxs.setRoomprice(s);
			type = 0;
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		//end rent
		if (localName.toUpperCase().equals("ROOMPRICE")) 
		{
			cmxc.addCMXSItem(cmxs);
			return;
		}
}

	@Override
	public void startDocument() throws SAXException 
	{
		cmxc = new ChecknoMoneyXMLContainer();
		cmxs = new ChecknoMoneyXMLStruct();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException 
	{
		//new item: BOOKINGNO
		if (localName.toUpperCase().equals("BOOKINGNO")) 
		{
			cmxs = new ChecknoMoneyXMLStruct();
			type = BOOKINGNO;
			return;
		}
		//new item: BOOKINGNO
		else if (localName.toUpperCase().equals("BDATE")) 
		{
			type = BDATE;
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