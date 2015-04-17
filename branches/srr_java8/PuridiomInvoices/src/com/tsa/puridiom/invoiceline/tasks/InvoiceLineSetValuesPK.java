package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvoiceLineSetValuesPK
{
	public void setValues(Map incomingRequest, InvoiceLine invoiceline ) throws Exception
	{
		try
		{
			String icIvcLineString = (String) incomingRequest.get("InvoiceLine_icIvcLine");
			BigDecimal icIvcLine = new BigDecimal ( icIvcLineString );
			invoiceline.setIcIvcLine(icIvcLine);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}