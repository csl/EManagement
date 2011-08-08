package com.example.android.login;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BookStatusXMLHandler extends DefaultHandler
{
	//Check Data
	private final static int ROOMNO = 1;	
	private final static int STATUS = 2;
	
	private BookStatusXMLContainer bsxsc;
	private BookStatusXMLStruct bsxc;
	
	private int type;

	
	public BookStatusXMLContainer getContainer() 
	{
		return bsxsc;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException 
	{
		String s = new String(ch, start, length);
		
		switch (type) 
		{
		case ROOMNO:
			bsxc.setId(s);
			type = 0;
			break;
		case STATUS:
			bsxc.setStatus(s);
			type = 0;
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		//end RECORD
		if (localName.toUpperCase().equals("RECORD")) 
		{
			bsxsc.addBookStatusXMLStructItem(bsxc);
			return;
		}
	}

	@Override
	public void startDocument() throws SAXException 
	{
		bsxsc = new BookStatusXMLContainer();		
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException 
	{
		//item: ROOMNO
		if (localName.toUpperCase().equals("RECORD"))
		{
			bsxc = new BookStatusXMLStruct();
			type=0;
			return;
		}
		if (localName.toUpperCase().equals("ROOMNO")) 
		{
			type = ROOMNO;
			return;
		}
		//item: STATUS
		else if (localName.toUpperCase().equals("STATUS")) 
		{
			type = STATUS;
			return;
		}
		
		type=0;
	}
}