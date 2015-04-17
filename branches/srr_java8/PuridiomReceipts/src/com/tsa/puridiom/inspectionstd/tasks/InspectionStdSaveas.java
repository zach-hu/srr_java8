package com.tsa.puridiom.inspectionstd.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InspectionStdSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain inspectionStd */
			InspectionStdPK comp_id = new InspectionStdPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InspectionStd	originalInspectionStd = (InspectionStd) incomingRequest.get("inspectionStd");
			InspectionStd	inspectionStd = new InspectionStd();

			inspectionStd.setIcInspStd(new java.math.BigDecimal(ukg.getUniqueKey())) ;
			inspectionStd.setStandardCode(originalInspectionStd.getStandardCode());
			inspectionStd.setInspectType(originalInspectionStd.getInspectType());
			inspectionStd.setInspectCode(originalInspectionStd.getInspectCode());
			inspectionStd.setCritNo(originalInspectionStd.getCritNo());
			inspectionStd.setDescription(originalInspectionStd.getDescription());
			inspectionStd.setDefaultFlag(originalInspectionStd.getDefaultFlag());
			inspectionStd.setDateEntered(originalInspectionStd.getDateEntered());
			inspectionStd.setStatus(originalInspectionStd.getStatus());
			inspectionStd.setOwner(originalInspectionStd.getOwner());
			inspectionStd.setLastChange(originalInspectionStd.getLastChange());
			inspectionStd.setLastChangeBy(originalInspectionStd.getLastChangeBy());

			incomingRequest.put("inspectionStd", inspectionStd);

			InspectionStdAdd addTask = new InspectionStdAdd();
			inspectionStd = (InspectionStd) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = inspectionStd;
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