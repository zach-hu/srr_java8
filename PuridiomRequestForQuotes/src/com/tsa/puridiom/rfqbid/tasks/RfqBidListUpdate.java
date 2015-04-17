package com.tsa.puridiom.rfqbid.tasks;

import com.tsa.puridiom.entity.RfqBid;
import com.tsa.puridiom.rfqbid.tasks.RfqBidUpdateById;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;

import java.util.*;

public class RfqBidListUpdate extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
		    List rfqBidList = (List) incomingRequest.get("rfqBidList");
			if (rfqBidList != null) {
			    RfqBidUpdateById updateTask = new RfqBidUpdateById();
				String organizationId = (String)incomingRequest.get("organizationId");
				String userId = (String)incomingRequest.get("userId");
				DBSession dbsession = (DBSession) incomingRequest.get("dbsession");
				
				updateTask.setApplicationName(this.getApplicationName());

				for (int i=0; i < rfqBidList.size(); i++)
				{
					Map updateParameters = new HashMap();
					updateParameters.put("userId", userId);
					updateParameters.put("organizationId", organizationId);
					updateParameters.put("dbsession", dbsession);
					updateParameters.put("rfqBid", (RfqBid) rfqBidList.get(i)) ;
					
					updateTask.executeTask(updateParameters);
					if(updateTask.getStatus() != Status.SUCCEEDED)
					{
						this.setStatus(Status.FAILED);
						throw new TsaException("Error ocurred updating rfq bids!");
					}
				}
			}
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}