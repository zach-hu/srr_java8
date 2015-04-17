/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

import java.util.*;

/**
 * @author Kelli
 */
public class RfqHeaderRetrieveVendorAddressSetup extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			RfqHeader rfh = (RfqHeader) incomingRequest.get("rfqHeader") ;
			String PdfRfq_vendorId = HiltonUtility.ckNull((String) incomingRequest.get("PdfRfq_vendorId"));
			if (rfh == null) {
				throw new Exception("The RfqHeader entity was not found.");
			}
			String	vendorCode = rfh.getVendorAwarded();
			if(Utility.isEmpty(vendorCode) || PdfRfq_vendorId.equalsIgnoreCase("none"))
			{
				rfh.setVendorAwarded("");				
				String tmpVendorId = (String)incomingRequest.get("PdfRfq_vendorId");
				vendorCode = Utility.ckNull(tmpVendorId);
			}
			else
			{
				incomingRequest.put("PdfRfq_vendorId", vendorCode);
			}

			Log.debug(this, "Vendor: " + vendorCode);

			incomingRequest.put("Address_addressType", vendorCode);
			incomingRequest.put("Address_addressCode", "DEFAULT") ;
			incomingRequest.put("vendorId", vendorCode);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Vendor Id cound not be found! " + e.getMessage(), e);
		}

		return result ;
	}
}
