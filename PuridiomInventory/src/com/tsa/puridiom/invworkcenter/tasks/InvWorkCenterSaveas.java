package com.tsa.puridiom.invworkcenter.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InvWorkCenterSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain invWorkCenter */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InvWorkCenter	originalInvWorkCenter = (InvWorkCenter) incomingRequest.get("invWorkCenter");
			InvWorkCenter	invWorkCenter = new InvWorkCenter();

			invWorkCenter.setWorkCenterId(originalInvWorkCenter.getWorkCenterId());
			invWorkCenter.setDescription(originalInvWorkCenter.getDescription());
			invWorkCenter.setDepartmentCode(originalInvWorkCenter.getDepartmentCode());
			invWorkCenter.setSubcontract(originalInvWorkCenter.getSubcontract());
			invWorkCenter.setLaborRate(originalInvWorkCenter.getLaborRate());
			invWorkCenter.setSetupRate(originalInvWorkCenter.getSetupRate());
			invWorkCenter.setFixOverHead(originalInvWorkCenter.getFixOverHead());
			invWorkCenter.setVarOverHead(originalInvWorkCenter.getVarOverHead());
			invWorkCenter.setSetuphours(originalInvWorkCenter.getSetuphours());
			invWorkCenter.setPerDayHours(originalInvWorkCenter.getPerDayHours());
			invWorkCenter.setPerJobHours(originalInvWorkCenter.getPerJobHours());
			invWorkCenter.setProcessTime(originalInvWorkCenter.getProcessTime());
			invWorkCenter.setProcessItems(originalInvWorkCenter.getProcessItems());
			invWorkCenter.setBufferDays(originalInvWorkCenter.getBufferDays());
			invWorkCenter.setVendorName(originalInvWorkCenter.getVendorName());
			invWorkCenter.setVendorId(originalInvWorkCenter.getVendorId());
			invWorkCenter.setCost(originalInvWorkCenter.getCost());
			invWorkCenter.setUnitOfMeasure(originalInvWorkCenter.getUnitOfMeasure());
			invWorkCenter.setLeadTime(originalInvWorkCenter.getLeadTime()) ;
			invWorkCenter.setDateEntered(originalInvWorkCenter.getDateEntered());
			invWorkCenter.setDateExpires(originalInvWorkCenter.getDateExpires());
			invWorkCenter.setOwner(originalInvWorkCenter.getOwner());
			invWorkCenter.setStatus(originalInvWorkCenter.getStatus());

			incomingRequest.put("invWorkCenter", invWorkCenter);

			InvWorkCenterAdd addTask = new InvWorkCenterAdd();
			invWorkCenter = (InvWorkCenter) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = invWorkCenter;
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