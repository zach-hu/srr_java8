package com.tsa.puridiom.invbinlochistory.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InvBinLocHistoryRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvBinLocHistory as invbinlochistory where 1=1 ");
		if(incomingRequest.containsKey("InvBinLocHistory_itemNumber"))
		{			
			String itemNumber = (String) incomingRequest.get("InvBinLocHistory_itemNumber");
			queryString.append(" AND invbinlochistory.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_itemLocation"))
		{			
			String itemLocation = (String) incomingRequest.get("InvBinLocHistory_itemLocation");
			queryString.append(" AND invbinlochistory.itemLocation = '" + itemLocation + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_vendorId"))
		{			
			String vendorId = (String) incomingRequest.get("InvBinLocHistory_vendorId");
			queryString.append(" AND invbinlochistory.vendorId = '" + vendorId + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_manufactNo"))
		{			
			String manufactNo = (String) incomingRequest.get("InvBinLocHistory_manufactNo");
			queryString.append(" AND invbinlochistory.manufactNo = '" + manufactNo + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_lot"))
		{			
			String lot = (String) incomingRequest.get("InvBinLocHistory_lot");
			queryString.append(" AND invbinlochistory.lot = '" + lot + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_cost"))
		{			
			String cost = (String) incomingRequest.get("InvBinLocHistory_cost");
			queryString.append(" AND invbinlochistory.cost = '" + cost + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_itemDate"))
		{			
			String itemDate = (String) incomingRequest.get("InvBinLocHistory_itemDate");
			queryString.append(" AND invbinlochistory.itemDate = '" + itemDate + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_aisle"))
		{			
			String aisle = (String) incomingRequest.get("InvBinLocHistory_aisle");
			queryString.append(" AND invbinlochistory.aisle = '" + aisle + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_locrow"))
		{			
			String locrow = (String) incomingRequest.get("InvBinLocHistory_locrow");
			queryString.append(" AND invbinlochistory.locrow = '" + locrow + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_tier"))
		{			
			String tier = (String) incomingRequest.get("InvBinLocHistory_tier");
			queryString.append(" AND invbinlochistory.tier = '" + tier + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_bin"))
		{			
			String bin = (String) incomingRequest.get("InvBinLocHistory_bin");
			queryString.append(" AND invbinlochistory.bin = '" + bin + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_udf1Code"))
		{			
			String udf1Code = (String) incomingRequest.get("InvBinLocHistory_udf1Code");
			queryString.append(" AND invbinlochistory.udf1Code = '" + udf1Code + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_udf2Code"))
		{			
			String udf2Code = (String) incomingRequest.get("InvBinLocHistory_udf2Code");
			queryString.append(" AND invbinlochistory.udf2Code = '" + udf2Code + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_udf3Code"))
		{			
			String udf3Code = (String) incomingRequest.get("InvBinLocHistory_udf3Code");
			queryString.append(" AND invbinlochistory.udf3Code = '" + udf3Code + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_udf4Code"))
		{			
			String udf4Code = (String) incomingRequest.get("InvBinLocHistory_udf4Code");
			queryString.append(" AND invbinlochistory.udf4Code = '" + udf4Code + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_udf5Code"))
		{			
			String udf5Code = (String) incomingRequest.get("InvBinLocHistory_udf5Code");
			queryString.append(" AND invbinlochistory.udf5Code = '" + udf5Code + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_qtyOnHand"))
		{			
			String qtyOnHand = (String) incomingRequest.get("InvBinLocHistory_qtyOnHand");
			queryString.append(" AND invbinlochistory.qtyOnHand = '" + qtyOnHand + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_qtyAlloc"))
		{			
			String qtyAlloc = (String) incomingRequest.get("InvBinLocHistory_qtyAlloc");
			queryString.append(" AND invbinlochistory.qtyAlloc = '" + qtyAlloc + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_actionCode"))
		{			
			String actionCode = (String) incomingRequest.get("InvBinLocHistory_actionCode");
			queryString.append(" AND invbinlochistory.actionCode = '" + actionCode + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_qtyMoved"))
		{			
			String qtyMoved = (String) incomingRequest.get("InvBinLocHistory_qtyMoved");
			queryString.append(" AND invbinlochistory.qtyMoved = '" + qtyMoved + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_histText"))
		{			
			String histText = (String) incomingRequest.get("InvBinLocHistory_histText");
			queryString.append(" AND invbinlochistory.histText = '" + histText + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_transactionDate"))
		{			
			String transactionDate = (String) incomingRequest.get("InvBinLocHistory_transactionDate");
			queryString.append(" AND invbinlochistory.transactionDate = '" + transactionDate + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_icCode"))
		{			
			String icCode = (String) incomingRequest.get("InvBinLocHistory_icCode");
			queryString.append(" AND invbinlochistory.id.icCode = '" + icCode + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_transactionTime"))
		{			
			String transactionTime = (String) incomingRequest.get("InvBinLocHistory_transactionTime");
			queryString.append(" AND invbinlochistory.transactionTime = '" + transactionTime + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_userId"))
		{			
			String userId = (String) incomingRequest.get("InvBinLocHistory_userId");
			queryString.append(" AND invbinlochistory.userId = '" + userId + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_reasonCode"))
		{			
			String reasonCode = (String) incomingRequest.get("InvBinLocHistory_reasonCode");
			queryString.append(" AND invbinlochistory.reasonCode = '" + reasonCode + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_icPoHeader"))
		{			
			String icPoHeader = (String) incomingRequest.get("InvBinLocHistory_icPoHeader");
			queryString.append(" AND invbinlochistory.icPoHeader = '" + icPoHeader + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_icDsbHeader"))
		{			
			String icDsbHeader = (String) incomingRequest.get("InvBinLocHistory_icDsbHeader");
			queryString.append(" AND invbinlochistory.icDsbHeader = '" + icDsbHeader + "'");
		}
		if(incomingRequest.containsKey("InvBinLocHistory_icDsbLine"))
		{			
			String icDsbLine = (String) incomingRequest.get("InvBinLocHistory_icDsbLine");
			queryString.append(" AND invbinlochistory.icDsbLine = '" + icDsbLine + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}