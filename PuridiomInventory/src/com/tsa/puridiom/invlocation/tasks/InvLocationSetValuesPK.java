package com.tsa.puridiom.invlocation.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;

public class InvLocationSetValuesPK
{
	public void setValues(Map incomingRequest, InvLocation invlocation ) throws Exception
	{
		try
		{
			String itemNumber = (String ) incomingRequest.get("InvLocation_itemNumber");
			String itemLocation = (String ) incomingRequest.get("InvLocation_itemLocation");
			InvLocationPK comp_id = new InvLocationPK();
			comp_id.setItemLocation(itemLocation);
			comp_id.setItemNumber(itemNumber);
			invlocation.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}