package com.example.android.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChecknoMoneyXMLContainer {

	private List<ChecknoMoneyXMLStruct> cmxs_items;

	public ArrayList<HashMap<String, Object>> getCMXSItems() 
	{

		ArrayList<HashMap<String, Object>> listitem = new ArrayList<HashMap<String,Object>>();

		//show last 5's data
		int sizes = cmxs_items.size();
		int counts = 1;
		
		for (ChecknoMoneyXMLStruct item : cmxs_items) 
		{			
			if (counts <= sizes - 5)
			{
				counts++;
				continue;
			}
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemTitle", "訂單編號: " + item.getId() 
					           + "\n日期: " + item.getDate() 
					           + " $" + item.getRoomprice() );
			
			map.put("ItemText", item.getId());

			listitem.add(map);
			
			counts++;
		}
		
		return listitem;
	}
	

	public ChecknoMoneyXMLContainer() 
	{
		cmxs_items = new ArrayList<ChecknoMoneyXMLStruct>();
	}

	public void addCMXSItem(ChecknoMoneyXMLStruct item) 
	{
		cmxs_items.add(item);
	}
		
}
