package com.tsa.puridiom.invformdata.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvFormData;

public class InvFormDataSetValuesPK
{
	public void setValues(Map incomingRequest, InvFormData invformdata ) throws Exception
	{
		try
		{
			String itemNumber = (String ) incomingRequest.get("InvFormData_itemNumber");
			invformdata.setItemNumber(itemNumber);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}