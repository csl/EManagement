package com.example.android.login;

public class ChecknoMoneyXMLStruct 
{
	  private String b_no = null;
	  private String b_date = null;
	  private String b_roomprice = null;
	  
	  public String getId()
	  {
	          return b_no;
	  }
	  public void setId(String id)
	  {
	          this.b_no = id;
	  }
	  public String getDate()
	  {
	          return b_date;
	  }
	  public void setDate(String date)
	  {
	          this.b_date = date;
	  }

	  public String getRoomprice()
	  {
	          return b_roomprice;
	  }
	  public void setRoomprice(String roomprice)
	  {
	          this.b_roomprice = roomprice;
	  }
}
