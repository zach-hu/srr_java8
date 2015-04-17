/*
 * Created on Sept. 13, 2006
 */
package com.tsa.puridiom.poline.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsa.puridiom.property.PropertiesManager;
import java.math.BigDecimal;
import java.util.Map;
import com.tsa.puridiom.catalog.tasks.CatalogItemManager;
import com.tsa.puridiom.common.utility.HiltonUtility;

import com.tsagate.foundation.utility.Log;
/**
 * @author Richard
 */
public class PromisedDateByCatalogItem extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		Object result = null;

		try {
		    String organizationId = (String) incomingRequest.get("organizationId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");

            if (HiltonUtility.isEmpty(userDateFormat)) {
                userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
            }

		    String	today = Dates.today(userDateFormat, userTimeZone) ;

		    if(incomingRequest.containsKey("PoLine_catalogId") && incomingRequest.containsKey("PoLine_itemNumber")){
		    	String catalogId = (String) incomingRequest.get("PoLine_catalogId");
		    	String itemNumber = (String) incomingRequest.get("PoLine_itemNumber");
		    	if(!HiltonUtility.isEmpty(catalogId) && !HiltonUtility.isEmpty(itemNumber))
		    	{
		    		BigDecimal leadDays = CatalogItemManager.getInstance().getLeadDays(organizationId, catalogId, itemNumber) ;
		    		incomingRequest.put("PoLine_poPromised", Dates.add(today, leadDays.intValue()));
		    	}
		    }

			this.setStatus(Status.SUCCEEDED) ;
		} catch (Exception e) {
			Log.debug(this, e.getMessage());
			e.printStackTrace();
		    throw e;
		}

		return result ;
	}
}
