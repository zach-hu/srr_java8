/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.rfqheader.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RfqHeaderUpdateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;
		try
		{
		    String	icRfqHeader = (String) incomingRequest.get("RfqHeader_icRfqHeader");
		    
			incomingRequest.put("RfqLine_icRfqHeader", icRfqHeader);
			incomingRequest.put("Account_icHeader", icRfqHeader);
			incomingRequest.put("Account_icLine", "0");
			incomingRequest.put("Default_referenceType", "RFQH");
			incomingRequest.put("RfqQuestion_icRfqHeader", icRfqHeader);
			incomingRequest.put("RfqVendor_icRfqHeader", icRfqHeader);
			incomingRequest.put("VendorResponse_icRfqHeader", icRfqHeader);
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED) ;
			throw new TsaException(this.getName(), e);
		}
		return null ;
	}
}
