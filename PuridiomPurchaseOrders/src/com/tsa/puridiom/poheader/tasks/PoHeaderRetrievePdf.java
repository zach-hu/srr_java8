package com.tsa.puridiom.poheader.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;
import java.util.Map;
import com.tsa.puridiom.entity.*;

public class PoHeaderRetrievePdf extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
 		Object ret = null;
		try
		{
			InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
			BigDecimal icPoHeader = invoiceHeader.getIcPoHeader();
			if (icPoHeader != null) {
				ret = icPoHeader;
			}

			this.setStatus(Status.SUCCEEDED);
		}
		 catch (Exception e)
	    {
	        this.setStatus(Status.FAILED);
	        throw new TsaException(this.getName(), e);
	    }
		return ret.toString();
	}
}