package com.tsa.puridiom.inspectiondispos.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionDisposSetValues extends Task
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
			InspectionDisposPK comp_id = new InspectionDisposPK();
			InspectionDispos inspectionDispos = (InspectionDispos) incomingRequest.get("inspectionDispos");
			if (inspectionDispos == null)
			{
				inspectionDispos = new InspectionDispos();
			}

			if (incomingRequest.containsKey("InspectionDispos_icRecHeader"))
			{
				String icRecHeaderString = (String) incomingRequest.get("InspectionDispos_icRecHeader");
				if (Utility.isEmpty(icRecHeaderString))
				{
					icRecHeaderString = "0";
				}
				BigDecimal icRecHeader = new BigDecimal ( icRecHeaderString );
				comp_id.setIcRecHeader(icRecHeader);
			}
			if (incomingRequest.containsKey("InspectionDispos_icRecLine"))
			{
				String icRecLineString = (String) incomingRequest.get("InspectionDispos_icRecLine");
				if (Utility.isEmpty(icRecLineString))
				{
					icRecLineString = "0";
				}
				BigDecimal icRecLine = new BigDecimal ( icRecLineString );
				comp_id.setIcRecLine(icRecLine);
			}
			if (incomingRequest.containsKey("InspectionDispos_icInspDiscrep"))
			{
				String icInspDiscrepString = (String) incomingRequest.get("InspectionDispos_icInspDiscrep");
				if (Utility.isEmpty(icInspDiscrepString))
				{
					icInspDiscrepString = "0";
				}
				BigDecimal icInspDiscrep = new BigDecimal ( icInspDiscrepString );
				comp_id.setIcInspDiscrep(icInspDiscrep);
			}
			if (incomingRequest.containsKey("InspectionDispos_keySequence"))
			{
				String keySequenceString = (String) incomingRequest.get("InspectionDispos_keySequence");
				if (Utility.isEmpty(keySequenceString))
				{
					keySequenceString = "0";
				}
				BigDecimal keySequence = new BigDecimal ( keySequenceString );
				comp_id.setKeySequence(keySequence);
			}
			if (incomingRequest.containsKey("InspectionDispos_icInspNo"))
			{
				String icInspNoString = (String) incomingRequest.get("InspectionDispos_icInspNo");
				if (Utility.isEmpty(icInspNoString))
				{
					icInspNoString = "0";
				}
				BigDecimal icInspNo = new BigDecimal ( icInspNoString );
				inspectionDispos.setIcInspNo(icInspNo);
			}
			if (incomingRequest.containsKey("InspectionDispos_icMsrLine"))
			{
				String icMsrLineString = (String) incomingRequest.get("InspectionDispos_icMsrLine");
				if (Utility.isEmpty(icMsrLineString))
				{
					icMsrLineString = "0";
				}
				BigDecimal icMsrLine = new BigDecimal ( icMsrLineString );
				inspectionDispos.setIcMsrLine(icMsrLine);
			}
			if (incomingRequest.containsKey("InspectionDispos_disposition"))
			{
				String disposition = (String) incomingRequest.get("InspectionDispos_disposition");
				inspectionDispos.setDisposition(disposition);
			}
			if (incomingRequest.containsKey("InspectionDispos_respGroup"))
			{
				String respGroup = (String) incomingRequest.get("InspectionDispos_respGroup");
				inspectionDispos.setRespGroup(respGroup);
			}
			if (incomingRequest.containsKey("InspectionDispos_dispType"))
			{
				String dispType = (String) incomingRequest.get("InspectionDispos_dispType");
				inspectionDispos.setDispType(dispType);
			}
			if (incomingRequest.containsKey("InspectionDispos_dispQuantity"))
			{
				String dispQuantityString = (String) incomingRequest.get("InspectionDispos_dispQuantity");
				if (Utility.isEmpty(dispQuantityString))
				{
					dispQuantityString = "0";
				}
				BigDecimal dispQuantity = new BigDecimal ( dispQuantityString );
				inspectionDispos.setDispQuantity(dispQuantity);
			}
			if (incomingRequest.containsKey("InspectionDispos_dispClosed"))
			{

				String dispClosedString = (String) incomingRequest.get("InspectionDispos_dispClosed");
				Date dispClosed = Dates.getSqlDate(dispClosedString, userDateFormat);
				inspectionDispos.setDispClosed(dispClosed);
			}
			if (incomingRequest.containsKey("InspectionDispos_lastChange"))
			{
				String lastChangeString = (String) incomingRequest.get("InspectionDispos_lastChange");
				Date lastChange = Dates.getSqlDate(lastChangeString, userDateFormat);
				inspectionDispos.setLastChange(lastChange);
			}
			if (incomingRequest.containsKey("InspectionDispos_lastChangeBy"))
			{
				String lastChangeBy = (String) incomingRequest.get("InspectionDispos_lastChangeBy");
				inspectionDispos.setLastChangeBy(lastChangeBy);
			}
			if (incomingRequest.containsKey("InspectionDispos_description"))
			{
				String description = (String) incomingRequest.get("InspectionDispos_description");
				inspectionDispos.setDescription(description);
			}
			inspectionDispos.setComp_id(comp_id);

			result = inspectionDispos;
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