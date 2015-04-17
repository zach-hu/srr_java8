package com.tsa.puridiom.disbheader.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class DisbHeaderSetValuesPK
{
	public void setValues(Map incomingRequest, DisbHeader disbheader ) throws Exception
	{
		try
		{
			String icDsbHeaderString = (String) incomingRequest.get("DisbHeader_icDsbHeader");
			BigDecimal icDsbHeader = new BigDecimal ( icDsbHeaderString );
			disbheader.setIcDsbHeader(icDsbHeader);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}