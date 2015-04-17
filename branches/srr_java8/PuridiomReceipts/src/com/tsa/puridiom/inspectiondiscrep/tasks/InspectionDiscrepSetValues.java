package com.tsa.puridiom.inspectiondiscrep.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionDiscrepSetValues extends Task
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
			InspectionDiscrepPK comp_id = new InspectionDiscrepPK();
			InspectionDiscrep inspectionDiscrep = (InspectionDiscrep) incomingRequest.get("inspectionDiscrep");
			if (inspectionDiscrep == null)
			{
				inspectionDiscrep = new InspectionDiscrep();
			}

			if (incomingRequest.containsKey("InspectionDiscrep_icRecHeader"))
			{
				String icRecHeaderString = (String) incomingRequest.get("InspectionDiscrep_icRecHeader");
				if (Utility.isEmpty(icRecHeaderString))
				{
					icRecHeaderString = "0";
				}
				BigDecimal icRecHeader = new BigDecimal ( icRecHeaderString );
				comp_id.setIcRecHeader(icRecHeader);
			}
			if (incomingRequest.containsKey("InspectionDiscrep_icRecLine"))
			{
				String icRecLineString = (String) incomingRequest.get("InspectionDiscrep_icRecLine");
				if (Utility.isEmpty(icRecLineString))
				{
					icRecLineString = "0";
				}
				BigDecimal icRecLine = new BigDecimal ( icRecLineString );
				comp_id.setIcRecLine(icRecLine);
			}
			if (incomingRequest.containsKey("InspectionDiscrep_icInspNo"))
			{
				String icInspNoString = (String) incomingRequest.get("InspectionDiscrep_icInspNo");
				if (Utility.isEmpty(icInspNoString))
				{
					icInspNoString = "0";
				}
				BigDecimal icInspNo = new BigDecimal ( icInspNoString );
				comp_id.setIcInspNo(icInspNo);
			}
			if (incomingRequest.containsKey("InspectionDiscrep_keySequence"))
			{
				String keySequenceString = (String) incomingRequest.get("InspectionDiscrep_keySequence");
				if (Utility.isEmpty(keySequenceString))
				{
					keySequenceString = "0";
				}
				BigDecimal keySequence = new BigDecimal ( keySequenceString );
				comp_id.setKeySequence(keySequence);
			}
			if (incomingRequest.containsKey("InspectionDiscrep_icInspDiscrep"))
			{
				String icInspDiscrepString = (String) incomingRequest.get("InspectionDiscrep_icInspDiscrep");
				if (Utility.isEmpty(icInspDiscrepString))
				{
					icInspDiscrepString = "0";
				}
				BigDecimal icInspDiscrep = new BigDecimal ( icInspDiscrepString );
				inspectionDiscrep.setIcInspDiscrep(icInspDiscrep);
			}
			if (incomingRequest.containsKey("InspectionDiscrep_inspectCode"))
			{
				String inspectCode = (String) incomingRequest.get("InspectionDiscrep_inspectCode");
				inspectionDiscrep.setInspectCode(inspectCode);
			}
			if (incomingRequest.containsKey("InspectionDiscrep_inspRequirements"))
			{
				String inspRequirements = (String) incomingRequest.get("InspectionDiscrep_inspRequirements");
				inspectionDiscrep.setInspRequirements(inspRequirements);
			}
			if (incomingRequest.containsKey("InspectionDiscrep_inspDescription"))
			{
				String inspDescription = (String) incomingRequest.get("InspectionDiscrep_inspDescription");
				inspectionDiscrep.setInspDescription(inspDescription);
			}
			if (incomingRequest.containsKey("InspectionDiscrep_inspQuantity"))
			{
				String inspQuantityString = (String) incomingRequest.get("InspectionDiscrep_inspQuantity");
				if (Utility.isEmpty(inspQuantityString))
				{
					inspQuantityString = "0";
				}
				BigDecimal inspQuantity = new BigDecimal ( inspQuantityString );
				inspectionDiscrep.setInspQuantity(inspQuantity);
			}
			if (incomingRequest.containsKey("InspectionDiscrep_status"))
			{
				String status = (String) incomingRequest.get("InspectionDiscrep_status");
				inspectionDiscrep.setStatus(status);
			}
			if (incomingRequest.containsKey("InspectionDiscrep_inspStartDate"))
			{
				String inspStartDateString = (String) incomingRequest.get("InspectionDiscrep_inspStartDate");
				Date inspStartDate = Dates.getSqlDate(inspStartDateString, userDateFormat);
				inspectionDiscrep.setInspStartDate(inspStartDate);
			}
			if (incomingRequest.containsKey("InspectionDiscrep_lastChange"))
			{
				String lastChangeString = (String) incomingRequest.get("InspectionDiscrep_lastChange");
				Date lastChange = Dates.getSqlDate(lastChangeString, userDateFormat);
				inspectionDiscrep.setLastChange(lastChange);
			}
			if (incomingRequest.containsKey("InspectionDiscrep_lastChangeBy"))
			{
				String lastChangeBy = (String) incomingRequest.get("InspectionDiscrep_lastChangeBy");
				inspectionDiscrep.setLastChangeBy(lastChangeBy);
			}
			inspectionDiscrep.setComp_id(comp_id);

			result = inspectionDiscrep;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			Log.error(this, e);
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}