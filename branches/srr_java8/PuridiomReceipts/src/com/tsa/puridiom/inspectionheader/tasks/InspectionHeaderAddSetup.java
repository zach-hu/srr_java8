/**
 * Created on Jan 6, 2004
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.poPOCreateSetup.java
 */

package com.tsa.puridiom.inspectionheader.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

public class InspectionHeaderAddSetup extends Task
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

			incomingRequest.put("InspectionHeader_icInspNo", ukg.getUniqueKey().toString());
			incomingRequest.put("InspectionHeader_lastChangeBy", userId);
			incomingRequest.put("InspectionHeader_dateEntered", today);
			incomingRequest.put("InspectionHeader_lastChange", today);
			incomingRequest.put("InspectionHeader_status", DocumentStatus.INSP_PENDING);
			incomingRequest.put("InspectionHeader_icRecLine", "0") ;
			incomingRequest.put("InspectionHeader_icPoLine", "0") ;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
