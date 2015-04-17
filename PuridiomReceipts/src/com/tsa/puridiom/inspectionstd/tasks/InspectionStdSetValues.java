package com.tsa.puridiom.inspectionstd.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionStdSetValues extends Task
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
			InspectionStd inspectionStd = (InspectionStd) incomingRequest.get("inspectionStd");
			if (inspectionStd == null)
			{
				inspectionStd = new InspectionStd();
			}
			if (incomingRequest.containsKey("InspectionStd_icInspStd"))
			{
				String icInspStdString = (String) incomingRequest.get("InspectionStd_icInspStd");
				if (Utility.isEmpty(icInspStdString))
				{
			        UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
					icInspStdString = ukg.getUniqueKey().toString();
				}
				BigDecimal icInspStd = new BigDecimal ( icInspStdString );
				inspectionStd.setIcInspStd(icInspStd);
			}

			if (incomingRequest.containsKey("InspectionStd_standardCode"))
			{
				String standardCode = (String) incomingRequest.get("InspectionStd_standardCode");
				inspectionStd.setStandardCode(standardCode);
			}
			if (incomingRequest.containsKey("InspectionStd_inspectType"))
			{
				String inspectType = (String) incomingRequest.get("InspectionStd_inspectType");
				inspectionStd.setInspectType(inspectType);
			}
			if (incomingRequest.containsKey("InspectionStd_inspectCode"))
			{
				String inspectCode = (String) incomingRequest.get("InspectionStd_inspectCode");
				inspectionStd.setInspectCode(inspectCode);
			}
			if (incomingRequest.containsKey("InspectionStd_critNo"))
			{
				String critNo = (String) incomingRequest.get("InspectionStd_critNo");
				inspectionStd.setCritNo(critNo);
			}
			if (incomingRequest.containsKey("InspectionStd_description"))
			{
				String description = (String) incomingRequest.get("InspectionStd_description");
				inspectionStd.setDescription(description);
			}
			if (incomingRequest.containsKey("InspectionStd_defaultFlag"))
			{

				String defaultFlag = (String) incomingRequest.get("InspectionStd_defaultFlag");
				if (defaultFlag == null)
				{
					defaultFlag = "N";
				}
				inspectionStd.setDefaultFlag(defaultFlag);
			}
			if (incomingRequest.containsKey("InspectionStd_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("InspectionStd_dateEntered");
				Date dateEntered = Dates.getSqlDate(dateEnteredString, userDateFormat);

				inspectionStd.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("InspectionStd_status"))
			{
				String status = (String) incomingRequest.get("InspectionStd_status");
				inspectionStd.setStatus(status);
			}
			if (incomingRequest.containsKey("InspectionStd_owner"))
			{
				String owner = (String) incomingRequest.get("InspectionStd_owner");
				inspectionStd.setOwner(owner);
			}
			if (incomingRequest.containsKey("InspectionStd_lastChange"))
			{
				String lastChangeString = (String) incomingRequest.get("InspectionStd_lastChange");
				Date lastChange = Dates.getSqlDate(lastChangeString, userDateFormat);

				inspectionStd.setLastChange(lastChange);

				inspectionStd.setLastChange(lastChange);
			}
			if (incomingRequest.containsKey("InspectionStd_lastChangeBy"))
			{
				String lastChangeBy = (String) incomingRequest.get("InspectionStd_lastChangeBy");
				inspectionStd.setLastChangeBy(lastChangeBy);
			}
			if (incomingRequest.containsKey("InspectionStd_cgdNo"))
			{
				String cgdNo = (String) incomingRequest.get("InspectionStd_cgdNo");
				inspectionStd.setCgdNo(cgdNo);
			}

			if (incomingRequest.containsKey("InspectionStd_cgdRev"))
			{
				String cgdRevString = (String) incomingRequest.get("InspectionStd_cgdRev");
				if (Utility.isEmpty(cgdRevString))
				{
					cgdRevString = "0";
				}
				BigDecimal cgdRev = new BigDecimal ( cgdRevString );
				inspectionStd.setCgdRev(cgdRev);
			}

			if (incomingRequest.containsKey("InspectionStd_critText"))
			{
				String critText = (String) incomingRequest.get("InspectionStd_critText");
				inspectionStd.setCritText(critText);
			}


			result = inspectionStd;
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