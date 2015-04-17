package com.tsa.puridiom.recentorder.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class RecentOrderRetrieveHeaderBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String oid = (String)incomingRequest.get("organizationId");
		String buyerCode = (String) incomingRequest.get("RecentOrder_buyerCode");
		List	result = new ArrayList();
		
		if(oid.equalsIgnoreCase("hoy08p") && buyerCode.equalsIgnoreCase("SYSADM") )
		{
			StringBuffer queryString = new StringBuffer("from PoHeader as poheader where ");
			if(incomingRequest.containsKey("RecentOrder_buyerCode"))
			{			
				buyerCode = (String) incomingRequest.get("RecentOrder_buyerCode");
				queryString.append(" poheader.buyerCode = '" + buyerCode + "'");
			}
			
			queryString.append(" ORDER BY poheader.poDate DESC");
				
			List list = dbs.query(queryString.toString()) ;
				
			if (list != null && list.size() > 0) {
			    for (int i=0; i < list.size(); i++ ) {
			        PoHeader poHeader = (PoHeader) list.get(i);
			        result.add(poHeader);
			    }
			}
		}
		else
		{
			StringBuffer queryString = new StringBuffer("from RecentOrder as recentorder, PoHeader poheader where 1=1 ");
			if(incomingRequest.containsKey("RecentOrder_buyerCode"))
			{			
				buyerCode = (String) incomingRequest.get("RecentOrder_buyerCode");
				queryString.append(" AND recentorder.id.buyerCode = '" + buyerCode + "'");
			}
			if(incomingRequest.containsKey("RecentOrder_icPoHeader"))
			{			
				String icPoHeader = (String) incomingRequest.get("RecentOrder_icPoHeader");
				queryString.append(" AND recentorder.id.icPoHeader = '" + icPoHeader + "'");
			}
			if(incomingRequest.containsKey("RecentOrder_dateEntered"))
			{			
				String dateEntered = (String) incomingRequest.get("RecentOrder_dateEntered");
				queryString.append(" AND recentorder.dateEntered = '" + dateEntered + "'");
			}
			
			queryString.append(" AND recentorder.id.icPoHeader = poheader.icPoHeader");
			queryString.append(" ORDER BY recentorder.id.icPoHeader DESC");
			
			List list = dbs.query(queryString.toString(), new Object[] {}, 11) ;
			
			if (list != null && list.size() > 0) {
			    for (int i=0; i < list.size(); i++ ) {
			        Object obj[] = (Object[])list.get(i);
			        PoHeader poHeader = (PoHeader) obj[1];
			        result.add(poHeader);
			    }
			}
		}
		
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}