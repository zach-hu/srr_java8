package com.tsa.puridiom.inspectionmfr.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionMfrSetValues extends Task
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
			InspectionMfr inspectionMfr = (InspectionMfr) incomingRequest.get("inspectionMfr");
			if (inspectionMfr == null)
			{
				inspectionMfr = new InspectionMfr();
			}

			if (incomingRequest.containsKey("InspectionMfr_icMfrNo"))
			{
				String icMfrNoString = (String) incomingRequest.get("InspectionMfr_icMfrNo");
				if (Utility.isEmpty(icMfrNoString))
				{
					icMfrNoString = ukg.getUniqueKey().toString();
				}
				BigDecimal icMfrNo = new BigDecimal ( icMfrNoString );
				inspectionMfr.setIcMfrNo(icMfrNo);
			}
			if (incomingRequest.containsKey("InspectionMfr_icInspNo"))
			{
				String icInspNoString = (String) incomingRequest.get("InspectionMfr_icInspNo");
				if (Utility.isEmpty(icInspNoString))
				{
					icInspNoString = "0";
				}
				BigDecimal icInspNo = new BigDecimal ( icInspNoString );
				inspectionMfr.setIcInspNo(icInspNo);
			}
			if (incomingRequest.containsKey("InspectionMfr_icMsrLine"))
			{
				String icMsrLineString = (String) incomingRequest.get("InspectionMfr_icMsrLine");
				if (Utility.isEmpty(icMsrLineString))
				{
					icMsrLineString = "0";
				}
				BigDecimal icMsrLine = new BigDecimal ( icMsrLineString );
				inspectionMfr.setIcMsrLine(icMsrLine);
			}
			if (incomingRequest.containsKey("InspectionMfr_documentType"))
			{
				String documentType = (String) incomingRequest.get("InspectionMfr_documentType");
				inspectionMfr.setDocumentType(documentType);
			}
			if (incomingRequest.containsKey("InspectionMfr_mfrName"))
			{
				String mfrName = (String) incomingRequest.get("InspectionMfr_mfrName");
				inspectionMfr.setMfrName(mfrName);
			}
			if (incomingRequest.containsKey("InspectionMfr_mfrNo"))
			{
				String mfrNo = (String) incomingRequest.get("InspectionMfr_mfrNo");
				inspectionMfr.setMfrNo(mfrNo);
			}
			if (incomingRequest.containsKey("InspectionMfr_mfrHeatLot"))
			{
				String mfrHeatLot = (String) incomingRequest.get("InspectionMfr_mfrHeatLot");
				inspectionMfr.setMfrHeatLot(mfrHeatLot);
			}
			if (incomingRequest.containsKey("InspectionMfr_shelfLifeDate"))
			{
				String shelfLifeDateString = (String) incomingRequest.get("InspectionMfr_shelfLifeDate");
				Date shelfLifeDate = Dates.getSqlDate(shelfLifeDateString, userDateFormat);
				inspectionMfr.setShelfLifeDate(shelfLifeDate);
			}

			result = inspectionMfr;
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