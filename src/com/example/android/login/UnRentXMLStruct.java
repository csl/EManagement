package com.example.android.login;

public class UnRentXMLStruct 
{
	  private String h_roomno;
	  private String h_rname = null;
	  
	  public String getroomno()
	  {
	          return h_roomno;
	  }
	  public void setroomno(String id)
	  {
	          this.h_roomno = id;
	  }
	  public String getName()
	  {
	          return h_rname;
	  }
	  public void setName(String name)
	  {
	          this.h_rname = name;
	  }
}
