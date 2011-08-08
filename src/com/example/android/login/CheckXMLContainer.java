package com.example.android.login;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;

public class CheckXMLContainer 
{

	String bookgno;
	String mobile;
	String roomprice;
	
	private List<CheckXMLStruct> room_items;

	public String getCheckItems() 
	{
		ArrayList<HashMap<String, Object>> listitem = new ArrayList<HashMap<String,Object>>();
		String total_info;
		int count=0;
		
		//fetch date
		Date today = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("MM/dd");
        String szToday = df.format(today);     
        
        //Combined, 
		total_info = "房間編號：" + bookgno 
			        + "\n" + "日期：" +  szToday
                    + "\n" + "遊客電話：" + mobile;
		/*
          + "\n房間編號:1212" +  " 房型:雙人房" + " $4500" 
          + "\n房間編號:1213" +  " 房型:雙人房" + " $6500" 
          + "\n房間編號:1214 " + " 房型:四人房" + " $8800" 
          + "\n共     " +  "3間房" +" 19800元 ";*/
	
		for (CheckXMLStruct item : room_items) 
		{			
			total_info = total_info 
	           + "\n" + "房間編號:" + " " + item.getId()
	           + "房型:" + " " + item.getName()
	           + " $" + item.getRoomprice();
			count++;
		}
		total_info = total_info + "\n" + "共" +  " " +  count 
		                        + "間房" + roomprice + "元";
		return total_info;
	}

	public CheckXMLContainer() 
	{
		room_items = new ArrayList<CheckXMLStruct>();
	}

	public void addRoomXMLItem(CheckXMLStruct item) 
	{
		room_items.add(item);
	}
	
	public String getBookingno() {
		return bookgno;
	}

	public void setBookingno(String no) {
		this.bookgno = no;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRoomprice() {
		return roomprice;
	}

	public void setRoomprice(String rp) {
		this.roomprice = rp;
	}
	
}
