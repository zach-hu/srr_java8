package com.tsa.puridiom.inspectionmte.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InspectionMteSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain inspectionMte */
			InspectionMtePK comp_id = new InspectionMtePK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InspectionMte	originalInspectionMte = (InspectionMte) incomingRequest.get("inspectionMte");
			InspectionMte	inspectionMte = new InspectionMte();

			comp_id.setIcRecHeader(originalInspectionMte.getComp_id().getIcRecHeader());
			comp_id.setIcRecLine(originalInspectionMte.getComp_id().getIcRecLine());
			comp_id.setKeySequence(originalInspectionMte.getComp_id().getKeySequence());
			comp_id.setIcInspNo(originalInspectionMte.getComp_id().getIcInspNo());
			inspectionMte.setIcMsrLine(originalInspectionMte.getIcMsrLine());
			inspectionMte.setMteNo(originalInspectionMte.getMteNo());
			inspectionMte.setDateUsed(originalInspectionMte.getDateUsed());
			inspectionMte.setDescription(originalInspectionMte.getDescription());
			inspectionMte.setLastChange(originalInspectionMte.getLastChange());
			inspectionMte.setLastChangeBy(originalInspectionMte.getLastChangeBy());
			inspectionMte.setComp_id(comp_id);

			incomingRequest.put("inspectionMte", inspectionMte);

			InspectionMteAdd addTask = new InspectionMteAdd();
			inspectionMte = (InspectionMte) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = inspectionMte;
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