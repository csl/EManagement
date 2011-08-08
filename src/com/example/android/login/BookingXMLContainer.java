package com.example.android.login;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingXMLContainer
{
	String name;
	
	private List<BookingXMLStruct> room_items;


	public ArrayList<HashMap<String, Object>> getBookingItems() 
	{
		int i=0;
		ArrayList<HashMap<String, Object>> listitem = new ArrayList<HashMap<String,Object>>();

		for (BookingXMLStruct item : room_items) 
		{			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemTitle", "\n房間編號：" + " " + item.getId()
					           + " 房型：" + " " + item.getName()
					           + "\n$" + item.getRoomprice() );
			map.put("ItemText", item.getId());
			map.put("ItemMoney", item.getRoomprice());
			listitem.add(map);
		}
		return listitem;
	}
	
	public BookingXMLContainer() 
	{
		room_items = new ArrayList<BookingXMLStruct>();
	}

	public void addBookingXMLItem(BookingXMLStruct item) 
	{
		room_items.add(item);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String no) {
		this.name = no;
	}
}
