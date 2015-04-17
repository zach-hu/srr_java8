package com.tsa.puridiom.inspectioncrit.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InspectionCritSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain inspectionCrit */
			InspectionCritPK comp_id = new InspectionCritPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InspectionCrit	originalInspectionCrit = (InspectionCrit) incomingRequest.get("inspectionCrit");
			InspectionCrit	inspectionCrit = new InspectionCrit();

			comp_id.setInspectCode(originalInspectionCrit.getComp_id().getInspectCode());
			comp_id.setCritNo(originalInspectionCrit.getComp_id().getCritNo());
			inspectionCrit.setDescription(originalInspectionCrit.getDescription());
			inspectionCrit.setDefaultFlag(originalInspectionCrit.getDefaultFlag());
			inspectionCrit.setStatus(originalInspectionCrit.getStatus());
			inspectionCrit.setDateEntered(originalInspectionCrit.getDateEntered());
			inspectionCrit.setOwner(originalInspectionCrit.getOwner());
			inspectionCrit.setComp_id(comp_id);

			incomingRequest.put("inspectionCrit", inspectionCrit);

			InspectionCritAdd addTask = new InspectionCritAdd();
			inspectionCrit = (InspectionCrit) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = inspectionCrit;
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