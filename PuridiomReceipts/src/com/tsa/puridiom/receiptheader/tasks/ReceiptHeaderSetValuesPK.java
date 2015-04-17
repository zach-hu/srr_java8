package com.tsa.puridiom.receiptheader.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ReceiptHeaderSetValuesPK
{
	public void setValues(Map incomingRequest, ReceiptHeader receiptheader ) throws Exception
	{
		try
		{
			String icRecHeaderString = (String) incomingRequest.get("ReceiptHeader_icRecHeader");
			BigDecimal icRecHeader = new BigDecimal ( icRecHeaderString );
			receiptheader.setIcRecHeader(icRecHeader);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}