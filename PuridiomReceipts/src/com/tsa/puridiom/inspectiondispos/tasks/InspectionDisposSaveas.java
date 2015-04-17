package com.tsa.puridiom.inspectiondispos.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InspectionDisposSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain inspectionDispos */
			InspectionDisposPK comp_id = new InspectionDisposPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InspectionDispos	originalInspectionDispos = (InspectionDispos) incomingRequest.get("inspectionDispos");
			InspectionDispos	inspectionDispos = new InspectionDispos();

			comp_id.setIcRecHeader(originalInspectionDispos.getComp_id().getIcRecHeader());
			comp_id.setIcRecLine(originalInspectionDispos.getComp_id().getIcRecLine());
			comp_id.setKeySequence(originalInspectionDispos.getComp_id().getKeySequence());
			inspectionDispos.setIcInspNo(originalInspectionDispos.getIcInspNo());
			inspectionDispos.setIcMsrLine(originalInspectionDispos.getIcMsrLine());
			inspectionDispos.setDisposition(originalInspectionDispos.getDisposition());
			inspectionDispos.setRespGroup(originalInspectionDispos.getRespGroup());
			inspectionDispos.setDispType(originalInspectionDispos.getDispType());
			inspectionDispos.setDispQuantity(originalInspectionDispos.getDispQuantity());
			inspectionDispos.setDispClosed(originalInspectionDispos.getDispClosed());
			inspectionDispos.setLastChange(originalInspectionDispos.getLastChange());
			inspectionDispos.setLastChangeBy(originalInspectionDispos.getLastChangeBy());
			inspectionDispos.setComp_id(comp_id);

			incomingRequest.put("inspectionDispos", inspectionDispos);

			InspectionDisposAdd addTask = new InspectionDisposAdd();
			inspectionDispos = (InspectionDispos) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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