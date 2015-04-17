/*
 * Created on Sept. 13, 2006
 */
package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

/**
 * @author Kelli 
 */
public class RfqHeaderGetCurrentCurrencyFactor extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		Object result;
		
		try {
		    String organizationId = (String) incomingRequest.get("organizationId");
		    CurrencyManager currencyManager = CurrencyManager.getInstance(organizationId);
		    RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
		    String currencyCode = (String) incomingRequest.get("RfqHeader_currencyCode");
		    
		    if (Utility.isEmpty(currencyCode)) {
		        currencyCode = rfqHeader.getCurrencyCode();
		    }
		    if (Utility.isEmpty(currencyCode)) {
		        currencyCode = currencyManager.getBaseCurrencyCode();
		        
				incomingRequest.put("RfqHeader_currencyCode", currencyCode);
		    }
		    
		    String currencyFactorString = String.valueOf(currencyManager.getCurrencyFactor(currencyCode));
		    
			incomingRequest.put("RfqHeader_currencyFactor", currencyFactorString);
			
			result = currencyFactorString;
			this.setStatus(Status.SUCCEEDED) ;
		} catch (Exception e) {
		    throw e;
		}
		
		return result ;
	}
}
