/*
 * Created on Sept. 13, 2006
 */
package com.tsa.puridiom.poheader.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsa.puridiom.property.PropertiesManager;
import java.math.BigDecimal;
import java.util.Map;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.utility.Log;
import com.tsa.puridiom.common.utility.HiltonUtility;
/**
 * @author Richard
 */
public class PromisedDateFromVendor extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		Object result = null;

		try {
		    String organizationId = (String) incomingRequest.get("organizationId");
            String userTimeZone = HiltonUtility.ckNull((String) incomingRequest.get("userTimeZone"));
            String userDateFormat = (String) incomingRequest.get("userDateFormat");

            if (HiltonUtility.isEmpty(userDateFormat)) {
                userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
            }
		    String	today = Dates.today(userDateFormat, userTimeZone) ;

		    if(incomingRequest.containsKey("PoHeader_vendorId")){
		    	String vendorId = (String) incomingRequest.get("PoHeader_vendorId");
		    	if(!HiltonUtility.isEmpty(vendorId))
		    	{
		    		BigDecimal leadDays = VendorManager.getInstance().getLeadDays(organizationId, vendorId);
		    		incomingRequest.put("PoHeader_promisedDate", Dates.add(today, leadDays.intValue()));
		    	}
		    }

			this.setStatus(Status.SUCCEEDED) ;
		} catch (Exception e) {
			Log.debug(this, " Error on PromisedDateFromVendor " + e.getMessage());
			this.setStatus(Status.FAILED) ;
			e.printStackTrace();
		    throw e;
		}

		return result ;
	}
}
