
/*
 * Created on October 4, 2004
 */
package com.tsa.puridiom.rfqvendor.tasks;

import com.tsa.puridiom.entity.RfqVendor;
import com.tsa.puridiom.entity.RfqVendorPK;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kathleen
 */
public class RfqVendorRetrieveBidList extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
	        PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
	        processLoader.setApplicationName(this.getApplicationName());
			PuridiomProcess process = processLoader.loadProcess("rfqbid-retrieve-by-vendor.xml");
			        
			List rfqVendorList = (List) incomingRequest.get("rfqVendorList");
	        for (Iterator it = rfqVendorList.iterator(); it.hasNext(); )
	        {
	        	RfqVendor rfqVendor = (RfqVendor) it.next();
	        	RfqVendorPK rfqVendorPK = rfqVendor.getComp_id();
	        	incomingRequest.put("rfqVendor", rfqVendor);
				incomingRequest.put("RfqBid_icRfqHeader", rfqVendorPK.getIcRfqHeader().toString());
				incomingRequest.put("RfqBid_vendorId", rfqVendorPK.getVendorId());
				process.executeProcess(incomingRequest);
				this.setStatus(process.getStatus());
				if (process.getStatus() == Status.FAILED)
				{
					break ;
				}
	        }
	        
	        incomingRequest.remove("rfqVendor");
	        
	        result = rfqVendorList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}
	
}
