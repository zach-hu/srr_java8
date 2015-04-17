package com.tsa.puridiom.invreturn.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvReturn;
import com.tsa.puridiom.entity.InvReturnPK;

public class InvReturnSetValuesPK
{
	public void setValues(Map incomingRequest, InvReturn invreturn ) throws Exception
	{
		try
		{
			String requisitionNumber = (String ) incomingRequest.get("InvReturn_requisitionNumber");
			String lineNoString = (String) incomingRequest.get("InvReturn_lineNo");
			BigDecimal lineNo = new BigDecimal ( lineNoString );
			String itemNumber = (String ) incomingRequest.get("InvReturn_itemNumber");
			InvReturnPK comp_id = new InvReturnPK();
			comp_id.setItemNumber(itemNumber);
			comp_id.setLineNo(lineNo);
			comp_id.setRequisitionNumber(requisitionNumber);
			//invreturn.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}