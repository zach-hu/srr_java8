package com.tsa.puridiom.invalternate.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvAlternateSetValuesPK
{
	public void setValues(Map incomingRequest, InvAlternate invalternate ) throws Exception
	{
		try
		{
			String icAlternateString = (String) incomingRequest.get("InvAlternate_icAlternate");
			BigDecimal icAlternate = new BigDecimal ( icAlternateString );
			invalternate.setIcAlternate(icAlternate);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}