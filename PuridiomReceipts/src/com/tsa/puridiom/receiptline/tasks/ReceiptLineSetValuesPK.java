package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ReceiptLineSetValuesPK
{
	public void setValues(Map incomingRequest, ReceiptLine receiptline ) throws Exception
	{
		try
		{
			String icRecLineString = (String) incomingRequest.get("ReceiptLine_icRecLine");
			BigDecimal icRecLine = new BigDecimal ( icRecLineString );
			receiptline.setIcRecLine(icRecLine);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}