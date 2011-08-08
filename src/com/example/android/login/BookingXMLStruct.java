package com.example.android.login;

public class BookingXMLStruct 
{
	  private String h_roomno = null;
	  private String h_rname = null;
	  private String h_roomprice = null;
	  
	  public String getId()
	  {
	          return h_roomno;
	  }
	  public void setId(String id)
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

	  public String getRoomprice()
	  {
	          return h_roomprice;
	  }
	  public void setRoomprice(String roomprice)
	  {
	          this.h_roomprice = roomprice;
	  }
}
