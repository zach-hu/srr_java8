/*
 * Created on Sept. 13, 2006
 */
package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;
import com.tsa.puridiom.property.PropertiesManager;
import java.math.BigDecimal;
import java.util.Map;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.utility.Log;
/**
 * @author Richard
 */
public class PromisedDateByVendor extends Task {
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

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
            }

		    String	today = Dates.today(userDateFormat, userTimeZone) ;

		    if(incomingRequest.containsKey("RequisitionHeader_vendorId")){
		    	String vendorId = (String) incomingRequest.get("requisitionHeader_vendorId");
		    	BigDecimal leadDays = VendorManager.getInstance().getLeadDays(organizationId, vendorId);
		    	incomingRequest.put("RequisitionerHeader_promisedDate", Dates.add(today, leadDays.intValue()));
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
