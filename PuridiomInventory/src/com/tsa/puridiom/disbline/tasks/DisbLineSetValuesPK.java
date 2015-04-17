package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;

public class DisbLineSetValuesPK
{
	public void setValues(Map incomingRequest, DisbLine disbline ) throws Exception
	{
		try
		{
			String icDsbLineString = (String) incomingRequest.get("DisbLine_icDsbLine");
			BigDecimal icDsbLine = new BigDecimal ( icDsbLineString );
			disbline.setIcDsbLine(icDsbLine);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}