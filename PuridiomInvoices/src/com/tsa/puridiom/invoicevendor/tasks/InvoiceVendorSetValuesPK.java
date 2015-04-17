package com.tsa.puridiom.invoicevendor.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvoiceVendorSetValuesPK
{
	public void setValues(Map incomingRequest, InvoiceVendor invoicevendor ) throws Exception
	{
		try
		{
			String vendorId = (String ) incomingRequest.get("InvoiceVendor_vendorId");
			invoicevendor.setVendorId(vendorId);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}