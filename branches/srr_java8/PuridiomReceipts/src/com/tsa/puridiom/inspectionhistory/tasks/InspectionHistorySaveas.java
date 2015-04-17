package com.tsa.puridiom.inspectionhistory.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InspectionHistorySaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain inspectionHistory */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InspectionHistory	originalInspectionHistory = (InspectionHistory) incomingRequest.get("inspectionHistory");
			InspectionHistory	inspectionHistory = new InspectionHistory();

			inspectionHistory.setIcHistory(originalInspectionHistory.getIcHistory());
			inspectionHistory.setIcInspNo(originalInspectionHistory.getIcInspNo());
			inspectionHistory.setIcMsrLine(originalInspectionHistory.getIcMsrLine());
			inspectionHistory.setRecType(originalInspectionHistory.getRecType());
			inspectionHistory.setStatusDate(originalInspectionHistory.getStatusDate());
			inspectionHistory.setQuantity(originalInspectionHistory.getQuantity());
			inspectionHistory.setArea(originalInspectionHistory.getArea());
			inspectionHistory.setStorage(originalInspectionHistory.getStorage());
			inspectionHistory.setLocation(originalInspectionHistory.getLocation());
			inspectionHistory.setStatus(originalInspectionHistory.getStatus());
			inspectionHistory.setOwner(originalInspectionHistory.getOwner());
			inspectionHistory.setHistoryText(originalInspectionHistory.getHistoryText());

			incomingRequest.put("inspectionHistory", inspectionHistory);

			InspectionHistoryAdd addTask = new InspectionHistoryAdd();
			inspectionHistory = (InspectionHistory) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = inspectionHistory;
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