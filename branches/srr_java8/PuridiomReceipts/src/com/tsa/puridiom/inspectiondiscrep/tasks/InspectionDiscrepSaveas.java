package com.tsa.puridiom.inspectiondiscrep.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InspectionDiscrepSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain inspectionDiscrep */
			InspectionDiscrepPK comp_id = new InspectionDiscrepPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InspectionDiscrep	originalInspectionDiscrep = (InspectionDiscrep) incomingRequest.get("inspectionDiscrep");
			InspectionDiscrep	inspectionDiscrep = new InspectionDiscrep();

			comp_id.setIcRecHeader(originalInspectionDiscrep.getComp_id().getIcRecHeader());
			comp_id.setIcRecLine(originalInspectionDiscrep.getComp_id().getIcRecLine());
			comp_id.setKeySequence(originalInspectionDiscrep.getComp_id().getKeySequence());
			comp_id.setIcInspNo(originalInspectionDiscrep.getComp_id().getIcInspNo());
			inspectionDiscrep.setIcMsrLine(originalInspectionDiscrep.getIcMsrLine());
			inspectionDiscrep.setInspectCode(originalInspectionDiscrep.getInspectCode());
			inspectionDiscrep.setInspRequirements(originalInspectionDiscrep.getInspRequirements());
			inspectionDiscrep.setInspDescription(originalInspectionDiscrep.getInspDescription());
			inspectionDiscrep.setInspQuantity(originalInspectionDiscrep.getInspQuantity());
			inspectionDiscrep.setStatus(originalInspectionDiscrep.getStatus());
			inspectionDiscrep.setInspStartDate(originalInspectionDiscrep.getInspStartDate());
			inspectionDiscrep.setLastChange(originalInspectionDiscrep.getLastChange());
			inspectionDiscrep.setLastChangeBy(originalInspectionDiscrep.getLastChangeBy());
			inspectionDiscrep.setComp_id(comp_id);

			incomingRequest.put("inspectionDiscrep", inspectionDiscrep);

			InspectionDiscrepAdd addTask = new InspectionDiscrepAdd();
			inspectionDiscrep = (InspectionDiscrep) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = inspectionDiscrep;
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