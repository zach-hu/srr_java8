package com.tsa.puridiom.inspectionheader.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionHeaderSetValues extends Task
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
			InspectionHeaderPK comp_id = new InspectionHeaderPK();
			InspectionHeader inspectionHeader = (InspectionHeader) incomingRequest.get("inspectionHeader");
			if (inspectionHeader == null)
			{
				inspectionHeader = new InspectionHeader();
			}

			if (incomingRequest.containsKey("InspectionHeader_icInspNo"))
			{
				String icInspNoString = (String) incomingRequest.get("InspectionHeader_icInspNo");
				if (Utility.isEmpty(icInspNoString))
				{
					icInspNoString = "0";
				}
				BigDecimal icInspNo = new BigDecimal ( icInspNoString );
				comp_id.setIcInspNo(icInspNo);
			}
			if (incomingRequest.containsKey("InspectionHeader_icMsrLine"))
			{
				String icMsrLineString = (String) incomingRequest.get("InspectionHeader_icMsrLine");
				if (Utility.isEmpty(icMsrLineString))
				{
					icMsrLineString = "0";
				}
				BigDecimal icMsrLine = new BigDecimal ( icMsrLineString );
				comp_id.setIcMsrLine(icMsrLine);
			}
			if (incomingRequest.containsKey("InspectionHeader_inspectType"))
			{
				String inspectType = (String) incomingRequest.get("InspectionHeader_inspectType");
				inspectionHeader.setInspectType(inspectType);
			}
			if (incomingRequest.containsKey("InspectionHeader_inspectorId"))
			{
				String inspectorId = (String) incomingRequest.get("InspectionHeader_inspectorId");
				inspectionHeader.setInspectorId(inspectorId);
			}
			if (incomingRequest.containsKey("InspectionHeader_engineerId"))
			{
				String engineerId = (String) incomingRequest.get("InspectionHeader_engineerId");
				inspectionHeader.setEngineerId(engineerId);
			}
			if (incomingRequest.containsKey("InspectionHeader_remoteInspId"))
			{
				String remoteInspId = (String) incomingRequest.get("InspectionHeader_remoteInspId");
				inspectionHeader.setRemoteInspId(remoteInspId);
			}
			if (incomingRequest.containsKey("InspectionHeader_purchaseSpecs"))
			{
				String purchaseSpecs = (String) incomingRequest.get("InspectionHeader_purchaseSpecs");
				inspectionHeader.setPurchaseSpecs(purchaseSpecs);
			}
			if (incomingRequest.containsKey("InspectionHeader_area"))
			{
				String area = (String) incomingRequest.get("InspectionHeader_area");
				inspectionHeader.setArea(area);
			}
			if (incomingRequest.containsKey("InspectionHeader_storage"))
			{
				String storage = (String) incomingRequest.get("InspectionHeader_storage");
				inspectionHeader.setStorage(storage);
			}
			if (incomingRequest.containsKey("InspectionHeader_location"))
			{
				String location = (String) incomingRequest.get("InspectionHeader_location");
				inspectionHeader.setLocation(location);
			}
			if (incomingRequest.containsKey("InspectionHeader_standardCode"))
			{
				String standardCode = (String) incomingRequest.get("InspectionHeader_standardCode");
				inspectionHeader.setStandardCode(standardCode);
			}
			if (incomingRequest.containsKey("InspectionHeader_udf01"))
			{
				String udf01 = (String) incomingRequest.get("InspectionHeader_udf01");
				inspectionHeader.setUdf01(udf01);
			}
			if (incomingRequest.containsKey("InspectionHeader_udf02"))
			{
				String udf02 = (String) incomingRequest.get("InspectionHeader_udf02");
				inspectionHeader.setUdf02(udf02);
			}
			if (incomingRequest.containsKey("InspectionHeader_udf03"))
			{
				String udf03 = (String) incomingRequest.get("InspectionHeader_udf03");
				inspectionHeader.setUdf03(udf03);
			}
			if (incomingRequest.containsKey("InspectionHeader_udf04"))
			{
				String udf04 = (String) incomingRequest.get("InspectionHeader_udf04");
				inspectionHeader.setUdf04(udf04);
			}
			if (incomingRequest.containsKey("InspectionHeader_udf05"))
			{
				String udf05 = (String) incomingRequest.get("InspectionHeader_udf05");
				inspectionHeader.setUdf05(udf05);
			}
			if (incomingRequest.containsKey("InspectionHeader_inspType"))
			{
				String inspType = (String) incomingRequest.get("InspectionHeader_inspType");
				inspectionHeader.setInspType(inspType);
			}
			if (incomingRequest.containsKey("InspectionHeader_apprDt"))
			{

				String apprDtString = (String) incomingRequest.get("InspectionHeader_apprDt");
				Date apprDt = Dates.getSqlDate(apprDtString, userDateFormat);
				inspectionHeader.setApprDt(apprDt);
			}
			if (incomingRequest.containsKey("InspectionHeader_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("InspectionHeader_dateEntered");
				Date dateEntered = Dates.getSqlDate(dateEnteredString, userDateFormat);

				inspectionHeader.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("InspectionHeader_status"))
			{
				String status = (String) incomingRequest.get("InspectionHeader_status");
				inspectionHeader.setStatus(status);
			}
			if (incomingRequest.containsKey("InspectionHeader_owner"))
			{
				String owner = (String) incomingRequest.get("InspectionHeader_owner");
				inspectionHeader.setOwner(owner);
			}
			if (incomingRequest.containsKey("InspectionHeader_lastChange"))
			{

				String lastChangeString = (String) incomingRequest.get("InspectionHeader_lastChange");
				Date lastChange = Dates.getSqlDate(lastChangeString, userDateFormat);
				inspectionHeader.setLastChange(lastChange);
			}
			if (incomingRequest.containsKey("InspectionHeader_lastChangeBy"))
			{
				String lastChangeBy = (String) incomingRequest.get("InspectionHeader_lastChangeBy");
				inspectionHeader.setLastChangeBy(lastChangeBy);
			}
			if (incomingRequest.containsKey("InspectionHeader_assignedDate"))
			{

				String assignedDateString = (String) incomingRequest.get("InspectionHeader_assignedDate");
				Date assignedDate = Dates.getSqlDate(assignedDateString, userDateFormat);
				inspectionHeader.setAssignedDate(assignedDate);
			}
			if (incomingRequest.containsKey("InspectionHeader_icRecLine"))
			{
				String icRecLineString = (String) incomingRequest.get("InspectionHeader_icRecLine");
				if (Utility.isEmpty(icRecLineString))
				{
					icRecLineString = "0";
				}
				BigDecimal icRecLine = new BigDecimal ( icRecLineString );
				inspectionHeader.setIcRecLine(icRecLine);
			}

			if (incomingRequest.containsKey("InspectionHeader_poNumber"))
			{
				String poNumber = (String) incomingRequest.get("InspectionHeader_poNumber");
				inspectionHeader.setPoNumber(poNumber);
			}

			if (incomingRequest.containsKey("InspectionHeader_icPoLine"))
			{
				String icPoLineString = (String) incomingRequest.get("InspectionHeader_icPoLine");
				if (Utility.isEmpty(icPoLineString))
				{
					icPoLineString = "0";
				}
				BigDecimal icPoLine = new BigDecimal ( icPoLineString );
				inspectionHeader.setIcPoLine(icPoLine);
			}
			if (incomingRequest.containsKey("InspectionHeader_shelfLifeDate"))
			{

				String shelfLifeDateString = (String) incomingRequest.get("InspectionHeader_shelfLifeDate");
				Date shelfLifeDate = Dates.getSqlDate(shelfLifeDateString, userDateFormat);
				inspectionHeader.setShelfLifeDate(shelfLifeDate);
			}
			if (incomingRequest.containsKey("InspectionHeader_cgdNo"))
			{
				String cgdNo = (String) incomingRequest.get("InspectionHeader_cgdNo");
				inspectionHeader.setCgdNo(cgdNo);
			}
			if (incomingRequest.containsKey("InspectionHeader_cgdRev"))
			{
				String cgdRevString = (String) incomingRequest.get("InspectionHeader_cgdRev");
				if (Utility.isEmpty(cgdRevString))
				{
					cgdRevString = "0";
				}
				BigDecimal cgdRev = new BigDecimal (cgdRevString);
				inspectionHeader.setCgdRev(cgdRev);
			}
			if (incomingRequest.containsKey("InspectionHeader_inspectionNumber"))
			{
				String inspectionNumber = (String) incomingRequest.get("InspectionHeader_inspectionNumber");
				inspectionHeader.setInspectionNumber(inspectionNumber);
			}

			if (incomingRequest.containsKey("InspectionHeader_qtySignoff"))
			{
				String qtySignoffString = (String) incomingRequest.get("InspectionHeader_qtySignoff");
				if (Utility.isEmpty(qtySignoffString))
				{
					qtySignoffString = "0";
				}
				BigDecimal qtySignoff = new BigDecimal (qtySignoffString);
				inspectionHeader.setQtySignoff(qtySignoff);
			}

			if (incomingRequest.containsKey("InspectionHeader_qtyAccepted"))
			{
				String qtyAcceptedString = (String) incomingRequest.get("InspectionHeader_qtyAccepted");
				if (Utility.isEmpty(qtyAcceptedString))
				{
					qtyAcceptedString = "0";
				}
				BigDecimal qtyAccepted = new BigDecimal (qtyAcceptedString);
				inspectionHeader.setQtyAccepted(qtyAccepted);
			}

			if (incomingRequest.containsKey("InspectionHeader_qtyRejected"))
			{
				String qtyRejectedString = (String) incomingRequest.get("InspectionHeader_qtyRejected");
				if (Utility.isEmpty(qtyRejectedString))
				{
					qtyRejectedString = "0";
				}
				BigDecimal qtyRejected = new BigDecimal (qtyRejectedString);
				inspectionHeader.setQtyRejected(qtyRejected);
			}

			if (incomingRequest.containsKey("InspectionHeader_internalComments"))
			{
				String internalComments = (String ) incomingRequest.get("InspectionHeader_internalComments");
				inspectionHeader.setInternalComments(internalComments);
			}

			inspectionHeader.setComp_id(comp_id);

			result = inspectionHeader;
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