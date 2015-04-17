package com.tsa.puridiom.inspectionheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.math.BigDecimal;
import java.util.*;

public class InspectionHeaderSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RequisitionLine reqLine = (RequisitionLine) incomingRequest.get("requisitionLine") ;
			String	userId = (String) incomingRequest.get("userId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            Date d_today = Dates.getCurrentDate(userTimeZone);

			/* Expects incoming request to contain inspectionHeader */
			InspectionHeaderPK comp_id = new InspectionHeaderPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InspectionHeader	originalInspectionHeader = (InspectionHeader) incomingRequest.get("inspectionHeader");
			InspectionHeader	inspectionHeader = new InspectionHeader();

			comp_id.setIcInspNo(new BigDecimal(ukg.getUniqueKey()));
			comp_id.setIcMsrLine(reqLine.getIcLineHistory());
			inspectionHeader.setInspectType(originalInspectionHeader.getInspectType());
			inspectionHeader.setInspectorId(originalInspectionHeader.getInspectorId());
			inspectionHeader.setEngineerId(originalInspectionHeader.getEngineerId());
			inspectionHeader.setRemoteInspId(originalInspectionHeader.getRemoteInspId());
			inspectionHeader.setPurchaseSpecs(originalInspectionHeader.getPurchaseSpecs());
			inspectionHeader.setArea(originalInspectionHeader.getArea());
			inspectionHeader.setStorage(originalInspectionHeader.getStorage());
			inspectionHeader.setLocation(originalInspectionHeader.getLocation());
			inspectionHeader.setStandardCode(originalInspectionHeader.getStandardCode());
			inspectionHeader.setUdf01(originalInspectionHeader.getUdf01());
			inspectionHeader.setUdf02(originalInspectionHeader.getUdf02());
			inspectionHeader.setUdf03(originalInspectionHeader.getUdf03());
			inspectionHeader.setUdf04(originalInspectionHeader.getUdf04());
			inspectionHeader.setUdf05(originalInspectionHeader.getUdf05());
			inspectionHeader.setInspType(originalInspectionHeader.getInspType());
//			inspectionHeader.setApprDt(originalInspectionHeader.getApprDt());
			inspectionHeader.setInspectionNumber(originalInspectionHeader.getInspectionNumber());
			inspectionHeader.setDateEntered(d_today);
			inspectionHeader.setStatus(DocumentStatus.INSP_PENDING);
			inspectionHeader.setOwner(userId);
			inspectionHeader.setLastChange(d_today);
			inspectionHeader.setLastChangeBy(userId);
			inspectionHeader.setIcRecLine(new BigDecimal(0)) ;
			inspectionHeader.setPoNumber("") ;
			inspectionHeader.setIcPoLine(new BigDecimal(0)) ;

			inspectionHeader.setComp_id(comp_id);


			incomingRequest.put("inspectionHeader", inspectionHeader);

			InspectionHeaderAdd addTask = new InspectionHeaderAdd();
			inspectionHeader = (InspectionHeader) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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