package com.tsa.puridiom.invbinlochistory.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvBinLocHistorySetValuesPK
{
	public void setValues(Map incomingRequest, InvBinLocHistory invbinlochistory ) throws Exception
	{
		try
		{
			String icCodeString = (String) incomingRequest.get("InvBinLocHistory_icCode");
			BigDecimal icCode = new BigDecimal ( icCodeString );
			invbinlochistory.setIcCode(icCode);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}