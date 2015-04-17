package com.tsa.puridiom.invformstate.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;

public class InvFormStateSetValuesPK
{
	public void setValues(Map incomingRequest, InvFormState invformstate ) throws Exception
	{
		try
		{
			String itemNumber = (String ) incomingRequest.get("InvFormState_itemNumber");
			String stateId = (String ) incomingRequest.get("InvFormState_stateId");
			InvFormStatePK comp_id = new InvFormStatePK();
			comp_id.setItemNumber(itemNumber);
			comp_id.setStateId(stateId);
			invformstate.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}