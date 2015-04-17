package com.tsa.puridiom.posecurity.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoSecurity;
import com.tsa.puridiom.entity.PoSecurityPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class PoSecuritySaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain poSecurity */
			PoSecurityPK comp_id = new PoSecurityPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			PoSecurity	originalPoSecurity = (PoSecurity) incomingRequest.get("poSecurity");
			PoSecurity	poSecurity = new PoSecurity();

			comp_id.setPoNumber(originalPoSecurity.getComp_id().getPoNumber());
			comp_id.setAccessType(originalPoSecurity.getComp_id().getAccessType());
			comp_id.setAccessId(originalPoSecurity.getComp_id().getAccessId());
			poSecurity.setOwner(originalPoSecurity.getOwner());
			poSecurity.setDateEntered(originalPoSecurity.getDateEntered());
			poSecurity.setDateChanged(originalPoSecurity.getDateChanged());
			poSecurity.setLastChangedBy(originalPoSecurity.getLastChangedBy());
			poSecurity.setTimeZone(originalPoSecurity.getTimeZone());
			poSecurity.setComp_id(comp_id);

			incomingRequest.put("poSecurity", poSecurity);

			PoSecurityAdd addTask = new PoSecurityAdd();
			poSecurity = (PoSecurity) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = poSecurity;
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
