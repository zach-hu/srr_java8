package com.tsa.puridiom.invlocation.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InvLocationRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvLocation as invlocation where 1=1 ");
		if(incomingRequest.containsKey("InvLocation_itemNumber"))
		{			
			String itemNumber = (String) incomingRequest.get("InvLocation_itemNumber");
			queryString.append(" AND invlocation.id.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("InvLocation_itemLocation"))
		{			
			String itemLocation = (String) incomingRequest.get("InvLocation_itemLocation");
			queryString.append(" AND invlocation.id.itemLocation = '" + itemLocation + "'");
		}
		if(incomingRequest.containsKey("InvLocation_qtyOnHand"))
		{			
			String qtyOnHand = (String) incomingRequest.get("InvLocation_qtyOnHand");
			queryString.append(" AND invlocation.qtyOnHand = '" + qtyOnHand + "'");
		}
		if(incomingRequest.containsKey("InvLocation_qtyOnOrder"))
		{			
			String qtyOnOrder = (String) incomingRequest.get("InvLocation_qtyOnOrder");
			queryString.append(" AND invlocation.qtyOnOrder = '" + qtyOnOrder + "'");
		}
		if(incomingRequest.containsKey("InvLocation_minOnHand"))
		{			
			String minOnHand = (String) incomingRequest.get("InvLocation_minOnHand");
			queryString.append(" AND invlocation.minOnHand = '" + minOnHand + "'");
		}
		if(incomingRequest.containsKey("InvLocation_maxOnHand"))
		{			
			String maxOnHand = (String) incomingRequest.get("InvLocation_maxOnHand");
			queryString.append(" AND invlocation.maxOnHand = '" + maxOnHand + "'");
		}
		if(incomingRequest.containsKey("InvLocation_qtyEoq"))
		{			
			String qtyEoq = (String) incomingRequest.get("InvLocation_qtyEoq");
			queryString.append(" AND invlocation.qtyEoq = '" + qtyEoq + "'");
		}
		if(incomingRequest.containsKey("InvLocation_qtyEsq"))
		{			
			String qtyEsq = (String) incomingRequest.get("InvLocation_qtyEsq");
			queryString.append(" AND invlocation.qtyEsq = '" + qtyEsq + "'");
		}
		if(incomingRequest.containsKey("InvLocation_averageCost"))
		{			
			String averageCost = (String) incomingRequest.get("InvLocation_averageCost");
			queryString.append(" AND invlocation.averageCost = '" + averageCost + "'");
		}
		if(incomingRequest.containsKey("InvLocation_udf1Code"))
		{			
			String udf1Code = (String) incomingRequest.get("InvLocation_udf1Code");
			queryString.append(" AND invlocation.udf1Code = '" + udf1Code + "'");
		}
		if(incomingRequest.containsKey("InvLocation_udf2Code"))
		{			
			String udf2Code = (String) incomingRequest.get("InvLocation_udf2Code");
			queryString.append(" AND invlocation.udf2Code = '" + udf2Code + "'");
		}
		if(incomingRequest.containsKey("InvLocation_udf3Code"))
		{			
			String udf3Code = (String) incomingRequest.get("InvLocation_udf3Code");
			queryString.append(" AND invlocation.udf3Code = '" + udf3Code + "'");
		}
		if(incomingRequest.containsKey("InvLocation_udf4Code"))
		{			
			String udf4Code = (String) incomingRequest.get("InvLocation_udf4Code");
			queryString.append(" AND invlocation.udf4Code = '" + udf4Code + "'");
		}
		if(incomingRequest.containsKey("InvLocation_udf5Code"))
		{			
			String udf5Code = (String) incomingRequest.get("InvLocation_udf5Code");
			queryString.append(" AND invlocation.udf5Code = '" + udf5Code + "'");
		}
		if(incomingRequest.containsKey("InvLocation_qtyAlloc"))
		{			
			String qtyAlloc = (String) incomingRequest.get("InvLocation_qtyAlloc");
			queryString.append(" AND invlocation.qtyAlloc = '" + qtyAlloc + "'");
		}
		if(incomingRequest.containsKey("InvLocation_icInvAccount"))
		{			
			String icInvAccount = (String) incomingRequest.get("InvLocation_icInvAccount");
			queryString.append(" AND invlocation.icInvAccount = '" + icInvAccount + "'");
		}
		if(incomingRequest.containsKey("InvLocation_icInvHeader"))
		{			
			String icInvHeader = (String) incomingRequest.get("InvLocation_icInvHeader");
			queryString.append(" AND invlocation.icInvHeader = '" + icInvHeader + "'");
		}
		if(incomingRequest.containsKey("InvLocation_qtyRequested"))
		{			
			String qtyRequested = (String) incomingRequest.get("InvLocation_qtyRequested");
			queryString.append(" AND invlocation.qtyRequested = '" + qtyRequested + "'");
		}
		if(incomingRequest.containsKey("InvLocation_autoReplenish"))
		{			
			String autoReplenish = (String) incomingRequest.get("InvLocation_autoReplenish");
			queryString.append(" AND invlocation.autoReplenish = '" + autoReplenish + "'");
		}
		if(incomingRequest.containsKey("InvLocation_physActual"))
		{			
			String physActual = (String) incomingRequest.get("InvLocation_physActual");
			queryString.append(" AND invlocation.physActual = '" + physActual + "'");
		}
		if(incomingRequest.containsKey("InvLocation_physOriginal"))
		{			
			String physOriginal = (String) incomingRequest.get("InvLocation_physOriginal");
			queryString.append(" AND invlocation.physOriginal = '" + physOriginal + "'");
		}
		if(incomingRequest.containsKey("InvLocation_primeLocation"))
		{			
			String primeLocation = (String) incomingRequest.get("InvLocation_primeLocation");
			queryString.append(" AND invlocation.primeLocation = '" + primeLocation + "'");
		}
		if(incomingRequest.containsKey("InvLocation_physAlloc"))
		{			
			String physAlloc = (String) incomingRequest.get("InvLocation_physAlloc");
			queryString.append(" AND invlocation.physAlloc = '" + physAlloc + "'");
		}
		if(incomingRequest.containsKey("InvLocation_originalAlloc"))
		{			
			String originalAlloc = (String) incomingRequest.get("InvLocation_originalAlloc");
			queryString.append(" AND invlocation.originalAlloc = '" + originalAlloc + "'");
		}
		if(incomingRequest.containsKey("InvLocation_qtyPendOrder"))
		{			
			String qtyPendOrder = (String) incomingRequest.get("InvLocation_qtyPendOrder");
			queryString.append(" AND invlocation.qtyPendOrder = '" + qtyPendOrder + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}