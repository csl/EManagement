package com.example.android.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CheckMoneyContainer 
{

	private List<CheckMoneyXMLStruct> checkmoney_items;

	//rent
	public ArrayList<HashMap<String, Object>> getCheckMoneyItems() 
	{
		int i=0;
		ArrayList<HashMap<String, Object>> listitem = new ArrayList<HashMap<String,Object>>();
		
        for (CheckMoneyXMLStruct item : checkmoney_items) 
		{	
        	if (item == null) continue;
        	
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemTitle", "訂單編號：" + item.getId() + " 日期："+ item.getDate() + "\n房間價格："+ item.getPrice() );
			map.put("ItemText", item.getId());

			listitem.add(map);
		}
		return listitem;
	}
	
	public CheckMoneyContainer() 
	{
		checkmoney_items = new ArrayList<CheckMoneyXMLStruct>();
	}

	public void addRXMLItem(CheckMoneyXMLStruct item) 
	{
		checkmoney_items.add(item);
	}
}
