/*
 * Created on September 13, 2006
 */
package com.tsa.puridiom.common.tasks;

import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author renzo
 */
public class ItemLookupCurrencyConversion extends Task 
{
	/**
	 * gets itemLookup and converts the orderCost to the currencyCode specified
	 * executeTask
	 * @param object
	 * @return ItemLookup
	 */
	public  Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try {
		    String organizationId = (String) incomingRequest.get("organizationId");
		    String currencyCode = (String) incomingRequest.get("currencyCode");
		    ItemLookup item = (ItemLookup) incomingRequest.get("itemLookup");
		    
		    if (item == null) {
		        throw new Exception ("ItemLookup cannot be null.");
		    }
		    
		    if (!Utility.isEmpty(currencyCode)) {
		        BigDecimal convertedCost = CurrencyManager.getInstance(organizationId).convertPriceFromBaseCurrency(item.getOrderCost(), currencyCode);

		        item.setOrderCost(convertedCost);
		    }

		    incomingRequest.put("itemLookup", item);
		    result = item;
		    this.setStatus(Status.SUCCEEDED) ;
		} catch (Exception e) {
		    throw e;
		} finally {
		    return result;
		}
	}
}
