package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class PoLineSetValuesPK
{
	public void setValues(Map incomingRequest, PoLine poline ) throws Exception
	{
		try
		{
			String icPoLineString = (String) incomingRequest.get("PoLine_icPoLine");
			BigDecimal icPoLine = new BigDecimal ( icPoLineString );
			poline.setIcPoLine(icPoLine);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}