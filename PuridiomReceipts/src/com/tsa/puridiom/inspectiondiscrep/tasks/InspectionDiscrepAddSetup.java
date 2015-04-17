/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.inspectiondiscrep.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

public class InspectionDiscrepAddSetup extends Task
{
	/**
	 * Method executeTask.
	 * @author EDSAC
	 * inserts values generated only by the system
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			String organizationId = (String)incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");

			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DateFormat", "");
            }

			String	today = Dates.today(userDateFormat, userTimeZone) ;
			String icInspDiscrepStr = (String) incomingRequest.get("InspectionDiscrep_icInspDiscrep") ;
			if(icInspDiscrepStr==null ) icInspDiscrepStr ="0" ;
			BigDecimal icLineDiscrep = new  BigDecimal(icInspDiscrepStr);
			if (icLineDiscrep.compareTo(new BigDecimal(0)) <= 0) {
				icInspDiscrepStr = ukg.getUniqueKey().toString() ;
			}
			incomingRequest.put("InspectionDiscrep_icInspDiscrep", icInspDiscrepStr );
			incomingRequest.put("InspectionDispos_icInspDiscrep", icInspDiscrepStr );
			incomingRequest.put("InspectionDiscrep_lastChangeBy", userId);
			incomingRequest.put("InspectionDiscrep_lastChange", today);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e);
			this.setStatus(Status.FAILED);
			throw e;
		}
		return ret;
	}
}
