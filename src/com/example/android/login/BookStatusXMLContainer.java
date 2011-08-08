package com.example.android.login;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookStatusXMLContainer 
{
	private ArrayList<BookStatusXMLStruct> bookstatus;

	public ArrayList<HashMap<String, Object>> getBookingItems() 
	{

		ArrayList<HashMap<String, Object>> listitem = new ArrayList<HashMap<String,Object>>();

		for (BookStatusXMLStruct item : bookstatus) 
		{			
			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("ItemTitle", "©Ð¶¡½s¸¹: " + item.getId());
			map.put("ItemStatus", item.getStatus());
			listitem.add(map);
		}
		
		return listitem;
	}

	public BookStatusXMLContainer() 
	{
		bookstatus = new ArrayList<BookStatusXMLStruct>();
	}

	
	public void addBookStatusXMLStructItem(BookStatusXMLStruct item) 
	{
		bookstatus.add(item);
	}
}
