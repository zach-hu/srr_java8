package com.tsa.puridiom.billto.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.BillTo;
import com.tsa.puridiom.entity.BillToPK;

public class BillToSetValuesPK
{
	public void setValues(Map incomingRequest, BillTo billto ) throws Exception
	{
		try
		{
			String icHeaderString = (String) incomingRequest.get("BillTo_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String icLineString = (String) incomingRequest.get("BillTo_icLine");
			BigDecimal icLine = new BigDecimal ( icLineString );
			String billToCode = (String ) incomingRequest.get("BillTo_billToCode");
			BillToPK comp_id = new BillToPK();
			comp_id.setBillToCode(billToCode);
			comp_id.setIcHeader(icHeader);
			comp_id.setIcLine(icLine);
			billto.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}