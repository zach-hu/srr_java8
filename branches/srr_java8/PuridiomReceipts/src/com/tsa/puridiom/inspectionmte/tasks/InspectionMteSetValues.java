package com.tsa.puridiom.inspectionmte.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionMteSetValues extends Task
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
			InspectionMtePK comp_id = new InspectionMtePK();
			InspectionMte inspectionMte = (InspectionMte) incomingRequest.get("inspectionMte");
			if (inspectionMte == null)
			{
				inspectionMte = new InspectionMte();
			}

			if (incomingRequest.containsKey("InspectionMte_icRecHeader"))
			{
				String icRecHeaderString = (String) incomingRequest.get("InspectionMte_icRecHeader");
				if (Utility.isEmpty(icRecHeaderString))
				{
					icRecHeaderString = "0";
				}
				BigDecimal icRecHeader = new BigDecimal ( icRecHeaderString );
				comp_id.setIcRecHeader(icRecHeader);
			}
			if (incomingRequest.containsKey("InspectionMte_icRecLine"))
			{
				String icRecLineString = (String) incomingRequest.get("InspectionMte_icRecLine");
				if (Utility.isEmpty(icRecLineString))
				{
					icRecLineString = "0";
				}
				BigDecimal icRecLine = new BigDecimal ( icRecLineString );
				comp_id.setIcRecLine(icRecLine);
			}
			if (incomingRequest.containsKey("InspectionMte_keySequence"))
			{
				String keySequenceString = (String) incomingRequest.get("InspectionMte_keySequence");
				if (Utility.isEmpty(keySequenceString))
				{
					keySequenceString = "0";
				}
				BigDecimal keySequence = new BigDecimal ( keySequenceString );
				comp_id.setKeySequence(keySequence);
			}
			if (incomingRequest.containsKey("InspectionMte_icInspNo"))
			{
				String icInspNoString = (String) incomingRequest.get("InspectionMte_icInspNo");
				if (Utility.isEmpty(icInspNoString))
				{
					icInspNoString = "0";
				}
				BigDecimal icInspNo = new BigDecimal ( icInspNoString );
				comp_id.setIcInspNo(icInspNo);
			}

			if (incomingRequest.containsKey("InspectionMte_icMsrLine"))
			{
				String icMsrLineString = (String) incomingRequest.get("InspectionMte_icMsrLine");
				if (Utility.isEmpty(icMsrLineString))
				{
					icMsrLineString = "0";
				}
				BigDecimal icMsrLine = new BigDecimal ( icMsrLineString );
				inspectionMte.setIcMsrLine(icMsrLine);
			}
			if (incomingRequest.containsKey("InspectionMte_mteNo"))
			{
				String mteNo = (String) incomingRequest.get("InspectionMte_mteNo");
				inspectionMte.setMteNo(mteNo);
			}
			if (incomingRequest.containsKey("InspectionMte_dateUsed"))
			{
				String dateUsedString = (String) incomingRequest.get("InspectionMte_dateUsed");
				Date dateUsed = Dates.getSqlDate(dateUsedString, userDateFormat);
				inspectionMte.setDateUsed(dateUsed);
			}
			if (incomingRequest.containsKey("InspectionMte_description"))
			{
				String description = (String) incomingRequest.get("InspectionMte_description");
				inspectionMte.setDescription(description);
			}
			if (incomingRequest.containsKey("InspectionMte_lastChange"))
			{
				String lastChangeString = (String) incomingRequest.get("InspectionMte_lastChange");
				Date lastChange = Dates.getSqlDate(lastChangeString, userDateFormat);
				inspectionMte.setLastChange(lastChange) ;
			}
			if (incomingRequest.containsKey("InspectionMte_lastChangeBy"))
			{
				String lastChangeBy = (String) incomingRequest.get("InspectionMte_lastChangeBy");
				inspectionMte.setLastChangeBy(lastChangeBy);
			}
			if (incomingRequest.containsKey("InspectionMte_calDate"))
			{
				String calDateString = (String) incomingRequest.get("InspectionMte_calDate");
				Date calDate = Dates.getSqlDate(calDateString, userDateFormat);
				inspectionMte.setCalDate(calDate) ;
			}
			inspectionMte.setComp_id(comp_id);

			result = inspectionMte;
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