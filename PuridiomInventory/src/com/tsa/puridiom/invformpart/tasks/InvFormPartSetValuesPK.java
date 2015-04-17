package com.tsa.puridiom.invformpart.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvFormPart;
import com.tsa.puridiom.entity.InvFormPartPK;

public class InvFormPartSetValuesPK
{
	public void setValues(Map incomingRequest, InvFormPart invformpart ) throws Exception
	{
		try
		{
			String itemNumber = (String ) incomingRequest.get("InvFormPart_itemNumber");
			String formPartString = (String) incomingRequest.get("InvFormPart_formPart");
			BigDecimal formPart = new BigDecimal ( formPartString );
			InvFormPartPK comp_id = new InvFormPartPK();
			comp_id.setFormPart(formPart);
			comp_id.setItemNumber(itemNumber);
			invformpart.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}