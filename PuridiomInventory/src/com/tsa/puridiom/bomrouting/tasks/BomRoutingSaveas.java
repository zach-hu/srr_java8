package com.tsa.puridiom.bomrouting.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class BomRoutingSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain bomRouting */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			BomRouting	originalBomRouting = (BomRouting) incomingRequest.get("bomRouting");
			BomRouting	bomRouting = new BomRouting();

			bomRouting.setIcRouting(originalBomRouting.getIcRouting());
			bomRouting.setParentItem(originalBomRouting.getParentItem());
			bomRouting.setComponentItem(originalBomRouting.getComponentItem());
			bomRouting.setStageId(originalBomRouting.getStageId());
			bomRouting.setStageSeq(originalBomRouting.getStageSeq());
			bomRouting.setDescription(originalBomRouting.getDescription());
			bomRouting.setWorkCenterId(originalBomRouting.getWorkCenterId());
			bomRouting.setMachineId(originalBomRouting.getMachineId());
			bomRouting.setRespons(originalBomRouting.getRespons());
			bomRouting.setUtilization(originalBomRouting.getUtilization());
			bomRouting.setQtyDays(originalBomRouting.getQtyDays());
			bomRouting.setSetupHours(originalBomRouting.getSetupHours());
			bomRouting.setPartsHour(originalBomRouting.getPartsHour());
			bomRouting.setTimePart(originalBomRouting.getTimePart());
			bomRouting.setVendorName(originalBomRouting.getVendorName());
			bomRouting.setLeadTime(originalBomRouting.getLeadTime());
			bomRouting.setOutside(originalBomRouting.getOutside());
			bomRouting.setBackflush(originalBomRouting.getBackflush());
			bomRouting.setPersons(originalBomRouting.getPersons());
			bomRouting.setCcost(originalBomRouting.getCcost());
			bomRouting.setUnitOfMeasure(originalBomRouting.getUnitOfMeasure());
			bomRouting.setPoNotes(originalBomRouting.getPoNotes());
			bomRouting.setNotes(originalBomRouting.getNotes());
			bomRouting.setDateEntered(originalBomRouting.getDateEntered());
			bomRouting.setOwner(originalBomRouting.getOwner());

			incomingRequest.put("bomRouting", bomRouting);

			BomRoutingAdd addTask = new BomRoutingAdd();
			bomRouting = (BomRouting) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = bomRouting;
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