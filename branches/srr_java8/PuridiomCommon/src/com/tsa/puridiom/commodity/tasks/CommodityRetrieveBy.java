package com.tsa.puridiom.commodity.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class CommodityRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from Commodity as commodity where 1=1 ");
		if(incomingRequest.containsKey("Commodity_commodity"))
		{			
			String commodity = (String) incomingRequest.get("Commodity_commodity");
			queryString.append(" AND commodity.id.commodity = '" + commodity + "'");
		}
		if(incomingRequest.containsKey("Commodity_variance"))
		{			
			String variance = (String) incomingRequest.get("Commodity_variance");
			queryString.append(" AND commodity.variance = '" + variance + "'");
		}
		if(incomingRequest.containsKey("Commodity_failsafe"))
		{			
			String failsafe = (String) incomingRequest.get("Commodity_failsafe");
			queryString.append(" AND commodity.failsafe = '" + failsafe + "'");
		}
		if(incomingRequest.containsKey("Commodity_description"))
		{			
			String description = (String) incomingRequest.get("Commodity_description");
			queryString.append(" AND commodity.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("Commodity_buyerCode"))
		{			
			String buyerCode = (String) incomingRequest.get("Commodity_buyerCode");
			queryString.append(" AND commodity.buyerCode = '" + buyerCode + "'");
		}
		if(incomingRequest.containsKey("Commodity_status"))
		{			
			String status = (String) incomingRequest.get("Commodity_status");
			queryString.append(" AND commodity.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("Commodity_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("Commodity_dateEntered");
			queryString.append(" AND commodity.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("Commodity_dateExpires"))
		{			
			String dateExpires = (String) incomingRequest.get("Commodity_dateExpires");
			queryString.append(" AND commodity.dateExpires = '" + dateExpires + "'");
		}
		if(incomingRequest.containsKey("Commodity_owner"))
		{			
			String owner = (String) incomingRequest.get("Commodity_owner");
			queryString.append(" AND commodity.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("Commodity_lastChgBy"))
		{			
			String lastChgBy = (String) incomingRequest.get("Commodity_lastChgBy");
			queryString.append(" AND commodity.lastChgBy = '" + lastChgBy + "'");
		}
		if(incomingRequest.containsKey("Commodity_lastChgDate"))
		{			
			String lastChgDate = (String) incomingRequest.get("Commodity_lastChgDate");
			queryString.append(" AND commodity.lastChgDate = '" + lastChgDate + "'");
		}
		if(incomingRequest.containsKey("Commodity_vchVariance"))
		{			
			String vchVariance = (String) incomingRequest.get("Commodity_vchVariance");
			queryString.append(" AND commodity.vchVariance = '" + vchVariance + "'");
		}
		if(incomingRequest.containsKey("Commodity_vchFailsafe"))
		{			
			String vchFailsafe = (String) incomingRequest.get("Commodity_vchFailsafe");
			queryString.append(" AND commodity.vchFailsafe = '" + vchFailsafe + "'");
		}
		if(incomingRequest.containsKey("Commodity_asset"))
		{			
			String asset = (String) incomingRequest.get("Commodity_asset");
			queryString.append(" AND commodity.asset = '" + asset + "'");
		}
		if(incomingRequest.containsKey("Commodity_depreciationTerm"))
		{			
			String depreciationTerm = (String) incomingRequest.get("Commodity_depreciationTerm");
			queryString.append(" AND commodity.depreciationTerm = '" + depreciationTerm + "'");
		}
		if(incomingRequest.containsKey("Commodity_icAccount"))
		{			
			String icAccount = (String) incomingRequest.get("Commodity_icAccount");
			queryString.append(" AND commodity.icAccount = '" + icAccount + "'");
		}
		if(incomingRequest.containsKey("Commodity_nigplevel"))
		{			
			String nigplevel = (String) incomingRequest.get("Commodity_nigplevel");
			queryString.append(" AND commodity.nigplevel = '" + nigplevel + "'");
		}
		if(incomingRequest.containsKey("Commodity_level1"))
		{			
			String level1 = (String) incomingRequest.get("Commodity_level1");
			queryString.append(" AND commodity.level1 = '" + level1 + "'");
		}
		if(incomingRequest.containsKey("Commodity_level2"))
		{			
			String level2 = (String) incomingRequest.get("Commodity_level2");
			queryString.append(" AND commodity.level2 = '" + level2 + "'");
		}
		if(incomingRequest.containsKey("Commodity_level3"))
		{			
			String level3 = (String) incomingRequest.get("Commodity_level3");
			queryString.append(" AND commodity.level3 = '" + level3 + "'");
		}
		if(incomingRequest.containsKey("Commodity_taxCode"))
		{			
			String taxCode = (String) incomingRequest.get("Commodity_taxCode");
			queryString.append(" AND commodity.taxCode = '" + taxCode + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}