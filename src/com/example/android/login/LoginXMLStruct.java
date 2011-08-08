package com.example.android.login;

public class LoginXMLStruct 
{
	  private int h_id = 0;
	  private String h_name = null;
	  private String status = null;
	  
	  public int getId()
	  {
	          return h_id;
	  }
	  public void setId(int id)
	  {
	          this.h_id = id;
	  }

	  public String getName()
	  {
	          return h_name;
	  }
	  public void setName(String name)
	  {
	          this.h_name = name;
	  }

	  public String getStatus()
	  {
	          return status;
	  }

	  public void setStatus(String status)
	  {
              this.status = status;
	  }
}
