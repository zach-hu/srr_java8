package com.tsa.puridiom.accountqxref.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class AccountQxrefSetValuesPK
{
	public void setValues(Map incomingRequest, AccountQxref accountqxref ) throws Exception
	{
		try
		{
			String location = (String ) incomingRequest.get("AccountQxref_location");
			String afe = (String ) incomingRequest.get("AccountQxref_afe");
			AccountQxrefPK comp_id = new AccountQxrefPK();
			comp_id.setAfe(afe);
			comp_id.setLocation(location);
			accountqxref.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}