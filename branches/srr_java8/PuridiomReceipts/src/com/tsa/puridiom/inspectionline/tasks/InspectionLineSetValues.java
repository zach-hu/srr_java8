package com.tsa.puridiom.inspectionline.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionLineSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		String organizationId = (String) incomingRequest.get("organizationId");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");
        UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

        if (HiltonUtility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
        }

		try
		{
			InspectionLine inspectionLine = (InspectionLine) incomingRequest.get("inspectionLine");
			if (inspectionLine == null)
			{
				inspectionLine = new InspectionLine();
			}

			if (incomingRequest.containsKey("InspectionLine_icInspNo"))
			{
				String icInspNoString = (String) incomingRequest.get("InspectionLine_icInspNo");
				if (Utility.isEmpty(icInspNoString))
				{
					icInspNoString = "0";
				}
				BigDecimal icInspNo = new BigDecimal ( icInspNoString );
				inspectionLine.setIcInspNo(icInspNo);
			}
			if (incomingRequest.containsKey("InspectionLine_icInspLine"))
			{
				String icInspLineString = (String) incomingRequest.get("InspectionLine_icInspLine");
				if (Utility.isEmpty(icInspLineString))
				{
					icInspLineString = ukg.getUniqueKey().toString();
				}
				BigDecimal icInspLine = new BigDecimal ( icInspLineString );
				inspectionLine.setIcInspLine(icInspLine);
			}
			if (incomingRequest.containsKey("InspectionLine_inspectCode"))
			{
				String inspectCode = (String) incomingRequest.get("InspectionLine_inspectCode");
				inspectionLine.setInspectCode(inspectCode);
			}
			if (incomingRequest.containsKey("InspectionLine_seqNo"))
			{
				String seqNoString = (String) incomingRequest.get("InspectionLine_seqNo");
				if (Utility.isEmpty(seqNoString))
				{
					seqNoString = "0";
				}
				BigDecimal seqNo = new BigDecimal ( seqNoString );
				inspectionLine.setSeqNo(seqNo);
			}
			if (incomingRequest.containsKey("InspectionLine_critNo"))
			{
				String critNo = (String) incomingRequest.get("InspectionLine_critNo");
				inspectionLine.setCritNo(critNo);
			}
			if (incomingRequest.containsKey("InspectionLine_standardCode"))
			{
				String standardCode = (String) incomingRequest.get("InspectionLine_standardCode");
				inspectionLine.setStandardCode(standardCode);
			}
			if (incomingRequest.containsKey("InspectionLine_lockFlag"))
			{
				String lockFlag = (String) incomingRequest.get("InspectionLine_lockFlag");
				inspectionLine.setLockFlag(lockFlag);
			}
			if (incomingRequest.containsKey("InspectionLine_udf01"))
			{
				String udf01 = (String) incomingRequest.get("InspectionLine_udf01");
				inspectionLine.setUdf01(udf01);
			}
			if (incomingRequest.containsKey("InspectionLine_udf02"))
			{
				String udf02 = (String) incomingRequest.get("InspectionLine_udf02");
				inspectionLine.setUdf02(udf02);
			}
			if (incomingRequest.containsKey("InspectionLine_udf03"))
			{
				String udf03 = (String) incomingRequest.get("InspectionLine_udf03");
				inspectionLine.setUdf03(udf03);
			}
			if (incomingRequest.containsKey("InspectionLine_udf04"))
			{
				String udf04 = (String) incomingRequest.get("InspectionLine_udf04");
				inspectionLine.setUdf04(udf04);
			}
			if (incomingRequest.containsKey("InspectionLine_udf05"))
			{
				String udf05 = (String) incomingRequest.get("InspectionLine_udf05");
				inspectionLine.setUdf05(udf05);
			}
			if (incomingRequest.containsKey("InspectionLine_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("InspectionLine_dateEntered");
				Date dateEntered = Dates.getSqlDate(dateEnteredString, userDateFormat);

				inspectionLine.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("InspectionLine_status"))
			{
				String status = (String) incomingRequest.get("InspectionLine_status");
				inspectionLine.setStatus(status);
			}
			if (incomingRequest.containsKey("InspectionLine_owner"))
			{
				String owner = (String) incomingRequest.get("InspectionLine_owner");
				inspectionLine.setOwner(owner);
			}
			if (incomingRequest.containsKey("InspectionLine_lastChange"))
			{
				String lastChangeString = (String) incomingRequest.get("InspectionLine_lastChange");
				Date lastChange = Dates.getSqlDate(lastChangeString, userDateFormat);

				inspectionLine.setLastChange(lastChange);
			}
			if (incomingRequest.containsKey("InspectionLine_lastChangeBy"))
			{
				String lastChangeBy = (String) incomingRequest.get("InspectionLine_lastChangeBy");
				inspectionLine.setLastChangeBy(lastChangeBy);
			}
			if (incomingRequest.containsKey("InspectionLine_critDescription"))
			{
				String critDescription = (String) incomingRequest.get("InspectionLine_critDescription");
				inspectionLine.setCritDescription(critDescription);
			}

			result = inspectionLine;
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