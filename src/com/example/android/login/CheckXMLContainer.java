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
		total_info = "�ж��s���G" + bookgno 
			        + "\n" + "����G" +  szToday
                    + "\n" + "�C�ȹq�ܡG" + mobile;
		/*
          + "\n�ж��s��:1212" +  " �Ы�:���H��" + " $4500" 
          + "\n�ж��s��:1213" +  " �Ы�:���H��" + " $6500" 
          + "\n�ж��s��:1214 " + " �Ы�:�|�H��" + " $8800" 
          + "\n�@     " +  "3����" +" 19800�� ";*/
	
		for (CheckXMLStruct item : room_items) 
		{			
			total_info = total_info 
	           + "\n" + "�ж��s��:" + " " + item.getId()
	           + "�Ы�:" + " " + item.getName()
	           + " $" + item.getRoomprice();
			count++;
		}
		total_info = total_info + "\n" + "�@" +  " " +  count 
		                        + "����" + roomprice + "��";
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
