package com.tsa.puridiom.inspectioncrit.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionCritSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		String organizationId = (String) incomingRequest.get("organizationId");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

        if (HiltonUtility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
        }

		try
		{
			InspectionCritPK comp_id = new InspectionCritPK();
			InspectionCrit inspectionCrit = (InspectionCrit) incomingRequest.get("inspectionCrit");
			if (inspectionCrit == null)
			{
				inspectionCrit = new InspectionCrit();
			}

			if (incomingRequest.containsKey("InspectionCrit_inspectCode"))
			{
				String inspectCode = (String) incomingRequest.get("InspectionCrit_inspectCode");
				comp_id.setInspectCode(inspectCode);
			}
			if (incomingRequest.containsKey("InspectionCrit_critNo"))
			{
				String critNo = (String) incomingRequest.get("InspectionCrit_critNo");
				comp_id.setCritNo(critNo);
			}
			if (incomingRequest.containsKey("InspectionCrit_description"))
			{
				String description = (String) incomingRequest.get("InspectionCrit_description");
				inspectionCrit.setDescription(description);
			}
			if (incomingRequest.containsKey("InspectionCrit_defaultFlag"))
			{
				String defaultFlag = (String) incomingRequest.get("InspectionCrit_defaultFlag");
				inspectionCrit.setDefaultFlag(defaultFlag);
			}
			if (incomingRequest.containsKey("InspectionCrit_status"))
			{
				String status = (String) incomingRequest.get("InspectionCrit_status");
				inspectionCrit.setStatus(status);
			}
			if (incomingRequest.containsKey("InspectionCrit_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("InspectionCrit_dateEntered");
				Date dateEntered = Dates.getSqlDate(dateEnteredString, userDateFormat);

				inspectionCrit.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("InspectionCrit_owner"))
			{
				String owner = (String) incomingRequest.get("InspectionCrit_owner");
				inspectionCrit.setOwner(owner);
			}
			inspectionCrit.setComp_id(comp_id);

			result = inspectionCrit;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}