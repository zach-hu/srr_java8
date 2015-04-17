/*
 * Created on Sept. 13, 2006
 */
package com.tsa.puridiom.rfqbid.tasks;

import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.entity.RfqBid;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

/**
 * @author Kelli 
 */
public class RfqBidGetCurrentCurrencyFactor extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		Object result = null;
		
		try {
		    RfqBid rfqBid = (RfqBid) incomingRequest.get("rfqBid");
		    if (rfqBid != null) {
			    String organizationId = (String) incomingRequest.get("organizationId");
			    String currencyCode = (String) incomingRequest.get("RfqBid_bidCurrency");
			    CurrencyManager currencyManager = CurrencyManager.getInstance(organizationId);
			    RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
	
	
			    if (Utility.isEmpty(currencyCode)) {
			        currencyCode = rfqBid.getBidCurrency();
			    }
			    if (Utility.isEmpty(currencyCode) && rfqHeader != null) {
			        currencyCode = rfqHeader.getCurrencyCode();
			        
			        incomingRequest.put("RfqBid_bidCurrency", currencyCode);
			    }
			    if (Utility.isEmpty(currencyCode)) {
			        currencyCode = currencyManager.getBaseCurrencyCode();
			        
					incomingRequest.put("RfqBid_bidCurrency", currencyCode);
			    }
			    
			    String currencyFactorString = String.valueOf(currencyManager.getCurrencyFactor(currencyCode));
			    
				incomingRequest.put("RfqBid_currencyFactor", currencyFactorString);
				
				result = currencyFactorString;
		    }
		    
			this.setStatus(Status.SUCCEEDED) ;
		} catch (Exception e) {
		    throw e;
		}
		
		return result ;
	}
}
