/*
 * Created on Sept. 13,2006
 */
package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Kelli 
 */
public class RequisitionHeaderGetCurrentCurrencyFactor extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		Object result;
		
		try {
		    String organizationId = (String) incomingRequest.get("organizationId");
		    CurrencyManager currencyManager = CurrencyManager.getInstance(organizationId);
		    RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
		    String currencyCode = (String) incomingRequest.get("RequisitionHeader_currencyCode");
		    
		    if (Utility.isEmpty(currencyCode)) {
		        currencyCode = requisitionHeader.getCurrencyCode();
		    }
		    if (Utility.isEmpty(currencyCode)) {
		        currencyCode = currencyManager.getBaseCurrencyCode();
		        
				incomingRequest.put("RequisitionHeader_currencyCode", currencyCode);
		    }
		    
		    String currencyFactorString = String.valueOf(currencyManager.getCurrencyFactor(currencyCode));
		    
			incomingRequest.put("RequisitionHeader_currencyFactor", currencyFactorString);
			
			result = currencyFactorString;
			this.setStatus(Status.SUCCEEDED) ;
		} catch (Exception e) {
		    throw e;
		}
		
		return result ;
	}
}
