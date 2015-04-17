package com.tsa.puridiom.invstage.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InvStageSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain invStage */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InvStage	originalInvStage = (InvStage) incomingRequest.get("invStage");
			InvStage	invStage = new InvStage();

			invStage.setStageId(originalInvStage.getStageId());
			invStage.setDescription(originalInvStage.getDescription());
			invStage.setRespons(originalInvStage.getRespons());
			invStage.setNotes(originalInvStage.getNotes());
			invStage.setWorkCenterId(originalInvStage.getWorkCenterId());
			invStage.setUtilization(originalInvStage.getUtilization());
			invStage.setQtyDays(originalInvStage.getQtyDays());
			invStage.setSetupHours(originalInvStage.getSetupHours());
			invStage.setPartsHour(originalInvStage.getPartsHour());
			invStage.setTimePart(originalInvStage.getTimePart());
			invStage.setVendorName(originalInvStage.getVendorName());
			invStage.setLeadTime(originalInvStage.getLeadTime());
			invStage.setOutside(originalInvStage.getOutside());
			invStage.setDescriptor(originalInvStage.getDescriptor());
			invStage.setMachineId(originalInvStage.getMachineId());
			invStage.setBackflush(originalInvStage.getBackflush());
			invStage.setPersons(originalInvStage.getPersons()) ;
			invStage.setCcost(originalInvStage.getCcost()) ;
			invStage.setUnitOfMeasure(originalInvStage.getUnitOfMeasure()) ;
			invStage.setDateEntered(originalInvStage.getDateEntered());
			invStage.setDateExpires(originalInvStage.getDateExpires());
			invStage.setOwner(originalInvStage.getOwner());
			invStage.setStatus(originalInvStage.getStatus());

			incomingRequest.put("invStage", invStage);

			InvStageAdd addTask = new InvStageAdd();
			invStage = (InvStage) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = invStage;
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