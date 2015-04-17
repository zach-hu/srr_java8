package com.tsa.puridiom.invreturn.tasks;

import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class InvReturnRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvReturn as invreturn where 1=1 ");
		if(incomingRequest.containsKey("InvReturn_requisitionNumber"))
		{			
			String requisitionNumber = (String) incomingRequest.get("InvReturn_requisitionNumber");
			queryString.append(" AND invreturn.id.requisitionNumber = '" + requisitionNumber + "'");
		}
		if(incomingRequest.containsKey("InvReturn_lineNo"))
		{			
			String lineNo = (String) incomingRequest.get("InvReturn_lineNo");
			queryString.append(" AND invreturn.id.lineNo = '" + lineNo + "'");
		}
		if(incomingRequest.containsKey("InvReturn_itemNumber"))
		{			
			String itemNumber = (String) incomingRequest.get("InvReturn_itemNumber");
			queryString.append(" AND invreturn.id.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("InvReturn_icReqHeader"))
		{			
			String icReqHeader = (String) incomingRequest.get("InvReturn_icReqHeader");
			queryString.append(" AND invreturn.icReqHeader = '" + icReqHeader + "'");
		}
		if(incomingRequest.containsKey("InvReturn_icReqLine"))
		{			
			String icReqLine = (String) incomingRequest.get("InvReturn_icReqLine");
			queryString.append(" AND invreturn.icReqLine = '" + icReqLine + "'");
		}
		if(incomingRequest.containsKey("InvReturn_icBin"))
		{			
			String icBin = (String) incomingRequest.get("InvReturn_icBin");
			queryString.append(" AND invreturn.icBin = '" + icBin + "'");
		}
		if(incomingRequest.containsKey("InvReturn_recBy"))
		{			
			String recBy = (String) incomingRequest.get("InvReturn_recBy");
			queryString.append(" AND invreturn.recBy = '" + recBy + "'");
		}
		if(incomingRequest.containsKey("InvReturn_recDate"))
		{			
			String recDate = (String) incomingRequest.get("InvReturn_recDate");
			queryString.append(" AND invreturn.recDate = '" + recDate + "'");
		}
		if(incomingRequest.containsKey("InvReturn_recAmount"))
		{			
			String recAmount = (String) incomingRequest.get("InvReturn_recAmount");
			queryString.append(" AND invreturn.recAmount = '" + recAmount + "'");
		}
		if(incomingRequest.containsKey("InvReturn_owner"))
		{			
			String owner = (String) incomingRequest.get("InvReturn_owner");
			queryString.append(" AND invreturn.owner = '" + owner + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}