package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class RfqLineSetValuesPK
{
	public void setValues(Map incomingRequest, RfqLine rfqline ) throws Exception
	{
		try
		{
			String icRfqLineString = (String) incomingRequest.get("RfqLine_icRfqLine");
			BigDecimal icRfqLine = new BigDecimal ( icRfqLineString );
			rfqline.setIcRfqLine(icRfqLine);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}