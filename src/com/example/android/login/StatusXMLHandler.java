package com.example.android.login;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class StatusXMLHandler extends DefaultHandler
{
	//STATUS Data
	private final static int STATUS = 1;
	
	private StatusXMLStruct sxh;

	private int type;

	
	public StatusXMLStruct getContainer() 
	{
		return sxh;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String s = new String(ch, start, length);
		switch (type) 
		{
		case STATUS:
			sxh.setStatus(s);
			type = 0;
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		//end rent
		if (localName.toUpperCase().equals("STATUS")) 
		{
			
			return;
		}
}

	@Override
	public void startDocument() throws SAXException 
	{
		sxh = new StatusXMLStruct();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException 
	{
		
		//new item: STATUS
		if (localName.toUpperCase().equals("STATUS")) 
		{
			type = STATUS;
			return;
		}
		type = 0;
	}

}