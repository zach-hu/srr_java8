package com.tsa.puridiom.inspectionmfr.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InspectionMfrSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain inspectionMfr */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InspectionMfr	originalInspectionMfr = (InspectionMfr) incomingRequest.get("inspectionMfr");
			InspectionMfr	inspectionMfr = new InspectionMfr();

			inspectionMfr.setIcMfrNo(originalInspectionMfr.getIcMfrNo());
			inspectionMfr.setIcInspNo(originalInspectionMfr.getIcInspNo());
			inspectionMfr.setIcMsrLine(originalInspectionMfr.getIcMsrLine());
			inspectionMfr.setDocumentType(originalInspectionMfr.getDocumentType());
			inspectionMfr.setMfrName(originalInspectionMfr.getMfrName());
			inspectionMfr.setMfrNo(originalInspectionMfr.getMfrNo());
			inspectionMfr.setMfrHeatLot(originalInspectionMfr.getMfrHeatLot());
			inspectionMfr.setShelfLifeDate(originalInspectionMfr.getShelfLifeDate());

			incomingRequest.put("inspectionMfr", inspectionMfr);

			InspectionMfrAdd addTask = new InspectionMfrAdd();
			inspectionMfr = (InspectionMfr) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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