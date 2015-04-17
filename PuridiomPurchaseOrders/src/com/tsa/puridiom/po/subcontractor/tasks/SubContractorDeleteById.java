package com.tsa.puridiom.po.subcontractor.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class SubContractorDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession");
		SubContractor subContractor = (SubContractor)incomingRequest.get("subContractor");
		if (subContractor == null)
		{
			subContractor = new SubContractor();
		}
		SubContractorSetValuesPK setValues = new SubContractorSetValuesPK();
		setValues.setValues(incomingRequest, subContractor);
		dbs.delete(subContractor);
		this.setStatus(dbs.getStatus());
		return subContractor;
	}

}