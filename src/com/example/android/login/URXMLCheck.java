package com.example.android.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class URXMLCheck {

	private List<RentXMLStruct> rent_items;
	private List<UnRentXMLStruct> urent_items;

	public ArrayList<HashMap<String, Object>> getRentItems() 
	{
		int i=0;
		ArrayList<HashMap<String, Object>> listitem = new ArrayList<HashMap<String,Object>>();

		for (RentXMLStruct item : rent_items) 
		{			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemTitle", "�q��s���G " + item.getId());
			map.put("ItemText", item.getId());

			listitem.add(map);
		}
		return listitem;
	}
	//��饼�X�����ж��s��/�Ы�
	public ArrayList<HashMap<String, Object>> getURentItems() 
	{
		ArrayList<HashMap<String, Object>> listitem = new ArrayList<HashMap<String,Object>>();

		for (UnRentXMLStruct item : urent_items) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemTitle", "�ж��s���G " + item.getroomno() + " �Ы��G"+ item.getName());
			map.put("ItemText", item.getroomno());
			listitem.add(map);
		}
		return listitem;
	}


	public void URXMLContainer() 
	{
		rent_items = new ArrayList<RentXMLStruct>();
		urent_items = new ArrayList<UnRentXMLStruct>();
	}

	public void addRXMLItem(RentXMLStruct item) 
	{
		rent_items.add(item);
	}
	
	public void addURXMLItem(UnRentXMLStruct uritem) 
	{
		urent_items.add(uritem);
	}
	
}