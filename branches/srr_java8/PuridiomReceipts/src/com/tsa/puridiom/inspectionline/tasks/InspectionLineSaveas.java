package com.tsa.puridiom.inspectionline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.math.BigDecimal;
import java.util.*;

public class InspectionLineSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain inspectionLine */
			InspectionHeader inspHeader = (InspectionHeader) incomingRequest.get("inspectionHeader");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            Date d_today = Dates.getCurrentDate(userTimeZone);
			String	userId = (String) incomingRequest.get("userId");

			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InspectionLine	originalInspectionLine = (InspectionLine) incomingRequest.get("inspectionLine");
			InspectionLine	inspectionLine = new InspectionLine();

			inspectionLine.setIcInspNo(inspHeader.getComp_id().getIcInspNo());
			inspectionLine.setIcInspLine(new BigDecimal(ukg.getUniqueKey()));
			inspectionLine.setInspectCode(originalInspectionLine.getInspectCode());
			inspectionLine.setSeqNo(originalInspectionLine.getSeqNo());
			inspectionLine.setCritNo(originalInspectionLine.getCritNo());
			inspectionLine.setStandardCode(originalInspectionLine.getStandardCode());
			inspectionLine.setLockFlag(originalInspectionLine.getLockFlag());
			inspectionLine.setUdf01(originalInspectionLine.getUdf01());
			inspectionLine.setUdf02(originalInspectionLine.getUdf02());
			inspectionLine.setUdf03(originalInspectionLine.getUdf03());
			inspectionLine.setUdf04(originalInspectionLine.getUdf04());
			inspectionLine.setUdf05(originalInspectionLine.getUdf05());
			inspectionLine.setDateEntered(originalInspectionLine.getDateEntered());
			inspectionLine.setStatus(DocumentStatus.INSP_PENDING);
			inspectionLine.setOwner(userId);
			inspectionLine.setLastChange(d_today);
			inspectionLine.setLastChangeBy(userId);
			inspectionLine.setCritDescription(originalInspectionLine.getCritDescription()) ;

			incomingRequest.put("inspectionLine", inspectionLine);

			InspectionLineAdd addTask = new InspectionLineAdd();
			inspectionLine = (InspectionLine) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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