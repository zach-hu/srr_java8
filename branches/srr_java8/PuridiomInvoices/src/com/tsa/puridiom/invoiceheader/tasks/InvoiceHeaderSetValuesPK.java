package com.tsa.puridiom.invoiceheader.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvoiceHeaderSetValuesPK
{
	public void setValues(Map incomingRequest, InvoiceHeader invoiceheader ) throws Exception
	{
		try
		{
			String icIvcHeaderString = (String) incomingRequest.get("InvoiceHeader_icIvcHeader");
			BigDecimal icIvcHeader = new BigDecimal ( icIvcHeaderString );
			invoiceheader.setIcIvcHeader(icIvcHeader);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}