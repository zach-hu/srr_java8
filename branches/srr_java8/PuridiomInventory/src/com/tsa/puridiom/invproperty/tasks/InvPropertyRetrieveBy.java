package com.tsa.puridiom.invproperty.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InvPropertyRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvProperty as invproperty where 1=1 ");
		if(incomingRequest.containsKey("InvProperty_icProperty"))
		{
			String icProperty = (String) incomingRequest.get("InvProperty_icProperty");
			queryString.append(" AND invproperty.id.icProperty = '" + icProperty + "'");
		}
		if(incomingRequest.containsKey("InvProperty_propertyType"))
		{
			String propertyType = (String) incomingRequest.get("InvProperty_propertyType");
			queryString.append(" AND invproperty.propertyType = '" + propertyType + "'");
		}
		if(incomingRequest.containsKey("InvProperty_tagNumber"))
		{
			String tagNumber = (String) incomingRequest.get("InvProperty_tagNumber");
			queryString.append(" AND invproperty.tagNumber = '" + tagNumber + "'");
		}
		if(incomingRequest.containsKey("InvProperty_icRc"))
		{
			String icRc = (String) incomingRequest.get("InvProperty_icRc");
			queryString.append(" AND invproperty.icRc = '" + icRc + "'");
		}
		if(incomingRequest.containsKey("InvProperty_itemNumber"))
		{
			String itemNumber = (String) incomingRequest.get("InvProperty_itemNumber");
			queryString.append(" AND invproperty.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("InvProperty_serialNumber"))
		{
			String serialNumber = (String) incomingRequest.get("InvProperty_serialNumber");
			queryString.append(" AND invproperty.serialNumber = '" + serialNumber + "'");
		}
		if(incomingRequest.containsKey("InvProperty_poNumber"))
		{
			String poNumber = (String) incomingRequest.get("InvProperty_poNumber");
			queryString.append(" AND invproperty.poNumber = '" + poNumber + "'");
		}
		if(incomingRequest.containsKey("InvProperty_contract"))
		{
			String contract = (String) incomingRequest.get("InvProperty_contract");
			queryString.append(" AND invproperty.contract = '" + contract + "'");
		}
		if(incomingRequest.containsKey("InvProperty_receiptNumber"))
		{
			String receiptNumber = (String) incomingRequest.get("InvProperty_receiptNumber");
			queryString.append(" AND invproperty.receiptNumber = '" + receiptNumber + "'");
		}
		if(incomingRequest.containsKey("InvProperty_shipNumber"))
		{
			String shipNumber = (String) incomingRequest.get("InvProperty_shipNumber");
			queryString.append(" AND invproperty.shipNumber = '" + shipNumber + "'");
		}
		if(incomingRequest.containsKey("InvProperty_remarks"))
		{
			String remarks = (String) incomingRequest.get("InvProperty_remarks");
			queryString.append(" AND invproperty.remarks = '" + remarks + "'");
		}
		if(incomingRequest.containsKey("InvProperty_dateIn"))
		{
			String dateIn = (String) incomingRequest.get("InvProperty_dateIn");
			queryString.append(" AND invproperty.dateIn = '" + dateIn + "'");
		}
		if(incomingRequest.containsKey("InvProperty_dateOut"))
		{
			String dateOut = (String) incomingRequest.get("InvProperty_dateOut");
			queryString.append(" AND invproperty.dateOut = '" + dateOut + "'");
		}
		if(incomingRequest.containsKey("InvProperty_status"))
		{
			String status = (String) incomingRequest.get("InvProperty_status");
			queryString.append(" AND invproperty.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("InvProperty_owner"))
		{
			String owner = (String) incomingRequest.get("InvProperty_owner");
			queryString.append(" AND invproperty.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("InvProperty_cblOutNumber"))
		{
			String cblOutNumber = (String) incomingRequest.get("InvProperty_cblOutNumber");
			queryString.append(" AND invproperty.cblOutNumber = '" + cblOutNumber + "'");
		}
		if(incomingRequest.containsKey("InvProperty_armyNumber"))
		{
			String armyNumber = (String) incomingRequest.get("InvProperty_armyNumber");
			queryString.append(" AND invproperty.armyNumber = '" + armyNumber + "'");
		}
		if(incomingRequest.containsKey("InvProperty_icRecLine"))
		{
			String icRecLine = (String) incomingRequest.get("InvProperty_icRecLine");
			queryString.append(" AND invproperty.icRecLine = '" + icRecLine + "'");
		}
		if(incomingRequest.containsKey("InvProperty_icPoLine"))
		{
			String icPoLine = (String) incomingRequest.get("InvProperty_icPoLine");
			queryString.append(" AND invproperty.icPoLine = '" + icPoLine + "'");
		}
		if(incomingRequest.containsKey("InvProperty_assetNumber"))
		{
			String assetNumber = (String) incomingRequest.get("InvProperty_assetNumber");
			queryString.append(" AND invproperty.assetNumber = '" + assetNumber + "'");
		}
		if(incomingRequest.containsKey("InvProperty_source"))
		{
			String source = (String) incomingRequest.get("InvProperty_source");
			queryString.append(" AND invproperty.source = '" + source + "'");
		}

		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}