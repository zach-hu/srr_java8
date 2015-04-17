package com.tsa.puridiom.subcommodity.tasks;

import com.tsa.puridiom.entity.SubCommodity;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class SubCommodityDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
		SubCommodity subCommodity = (SubCommodity) incomingRequest.get("subCommodity");
		if(subCommodity == null)
		{
		    subCommodity = new SubCommodity();
		}
		SubCommoditySetValuesPK setValues = new SubCommoditySetValuesPK();
		setValues.setValues(incomingRequest, subCommodity);
		dbs.delete(subCommodity);
		this.setStatus(dbs.getStatus()) ;
		return subCommodity ;
	}

}