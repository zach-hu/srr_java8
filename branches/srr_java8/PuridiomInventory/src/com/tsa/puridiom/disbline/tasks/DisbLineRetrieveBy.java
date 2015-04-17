package com.tsa.puridiom.disbline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class DisbLineRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from DisbLine as disbline where 1=1 ");
		if(incomingRequest.containsKey("DisbLine_icDsbLine"))
		{			
			String icDsbLine = (String) incomingRequest.get("DisbLine_icDsbLine");
			queryString.append(" AND disbline.id.icDsbLine = '" + icDsbLine + "'");
		}
		if(incomingRequest.containsKey("DisbLine_lineNumber"))
		{			
			String lineNumber = (String) incomingRequest.get("DisbLine_lineNumber");
			queryString.append(" AND disbline.lineNumber = '" + lineNumber + "'");
		}
		if(incomingRequest.containsKey("DisbLine_disbNumber"))
		{			
			String disbNumber = (String) incomingRequest.get("DisbLine_disbNumber");
			queryString.append(" AND disbline.disbNumber = '" + disbNumber + "'");
		}
		if(incomingRequest.containsKey("DisbLine_icDsbHeader"))
		{			
			String icDsbHeader = (String) incomingRequest.get("DisbLine_icDsbHeader");
			queryString.append(" AND disbline.icDsbHeader = '" + icDsbHeader + "'");
		}
		if(incomingRequest.containsKey("DisbLine_itemNumber"))
		{			
			String itemNumber = (String) incomingRequest.get("DisbLine_itemNumber");
			queryString.append(" AND disbline.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("DisbLine_itemSource"))
		{			
			String itemSource = (String) incomingRequest.get("DisbLine_itemSource");
			queryString.append(" AND disbline.itemSource = '" + itemSource + "'");
		}
		if(incomingRequest.containsKey("DisbLine_umCode"))
		{			
			String umCode = (String) incomingRequest.get("DisbLine_umCode");
			queryString.append(" AND disbline.umCode = '" + umCode + "'");
		}
		if(incomingRequest.containsKey("DisbLine_quantity"))
		{			
			String quantity = (String) incomingRequest.get("DisbLine_quantity");
			queryString.append(" AND disbline.quantity = '" + quantity + "'");
		}
		if(incomingRequest.containsKey("DisbLine_unitPrice"))
		{			
			String unitPrice = (String) incomingRequest.get("DisbLine_unitPrice");
			queryString.append(" AND disbline.unitPrice = '" + unitPrice + "'");
		}
		if(incomingRequest.containsKey("DisbLine_commodityCode"))
		{			
			String commodityCode = (String) incomingRequest.get("DisbLine_commodityCode");
			queryString.append(" AND disbline.commodityCode = '" + commodityCode + "'");
		}
		if(incomingRequest.containsKey("DisbLine_icReqLine"))
		{			
			String icReqLine = (String) incomingRequest.get("DisbLine_icReqLine");
			queryString.append(" AND disbline.icReqLine = '" + icReqLine + "'");
		}
		if(incomingRequest.containsKey("DisbLine_status"))
		{			
			String status = (String) incomingRequest.get("DisbLine_status");
			queryString.append(" AND disbline.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("DisbLine_commentFlag"))
		{			
			String commentFlag = (String) incomingRequest.get("DisbLine_commentFlag");
			queryString.append(" AND disbline.commentFlag = '" + commentFlag + "'");
		}
		if(incomingRequest.containsKey("DisbLine_itemLocation"))
		{			
			String itemLocation = (String) incomingRequest.get("DisbLine_itemLocation");
			queryString.append(" AND disbline.itemLocation = '" + itemLocation + "'");
		}
		if(incomingRequest.containsKey("DisbLine_icDsbAccount"))
		{			
			String icDsbAccount = (String) incomingRequest.get("DisbLine_icDsbAccount");
			queryString.append(" AND disbline.icDsbAccount = '" + icDsbAccount + "'");
		}
		if(incomingRequest.containsKey("DisbLine_umFactor"))
		{			
			String umFactor = (String) incomingRequest.get("DisbLine_umFactor");
			queryString.append(" AND disbline.umFactor = '" + umFactor + "'");
		}
		if(incomingRequest.containsKey("DisbLine_lineTotal"))
		{			
			String lineTotal = (String) incomingRequest.get("DisbLine_lineTotal");
			queryString.append(" AND disbline.lineTotal = '" + lineTotal + "'");
		}
		if(incomingRequest.containsKey("DisbLine_shiptoFlag"))
		{			
			String shiptoFlag = (String) incomingRequest.get("DisbLine_shiptoFlag");
			queryString.append(" AND disbline.shiptoFlag = '" + shiptoFlag + "'");
		}
		if(incomingRequest.containsKey("DisbLine_aisle"))
		{			
			String aisle = (String) incomingRequest.get("DisbLine_aisle");
			queryString.append(" AND disbline.aisle = '" + aisle + "'");
		}
		if(incomingRequest.containsKey("DisbLine_locrow"))
		{			
			String locrow = (String) incomingRequest.get("DisbLine_locrow");
			queryString.append(" AND disbline.locrow = '" + locrow + "'");
		}
		if(incomingRequest.containsKey("DisbLine_tier"))
		{			
			String tier = (String) incomingRequest.get("DisbLine_tier");
			queryString.append(" AND disbline.tier = '" + tier + "'");
		}
		if(incomingRequest.containsKey("DisbLine_bin"))
		{			
			String bin = (String) incomingRequest.get("DisbLine_bin");
			queryString.append(" AND disbline.bin = '" + bin + "'");
		}
		if(incomingRequest.containsKey("DisbLine_vendorId"))
		{			
			String vendorId = (String) incomingRequest.get("DisbLine_vendorId");
			queryString.append(" AND disbline.vendorId = '" + vendorId + "'");
		}
		if(incomingRequest.containsKey("DisbLine_manufactNo"))
		{			
			String manufactNo = (String) incomingRequest.get("DisbLine_manufactNo");
			queryString.append(" AND disbline.manufactNo = '" + manufactNo + "'");
		}
		if(incomingRequest.containsKey("DisbLine_lot"))
		{			
			String lot = (String) incomingRequest.get("DisbLine_lot");
			queryString.append(" AND disbline.lot = '" + lot + "'");
		}
		if(incomingRequest.containsKey("DisbLine_locIc"))
		{			
			String locIc = (String) incomingRequest.get("DisbLine_locIc");
			queryString.append(" AND disbline.locIc = '" + locIc + "'");
		}
		if(incomingRequest.containsKey("DisbLine_qtyBkord"))
		{			
			String qtyBkord = (String) incomingRequest.get("DisbLine_qtyBkord");
			queryString.append(" AND disbline.qtyBkord = '" + qtyBkord + "'");
		}
		if(incomingRequest.containsKey("DisbLine_disbDate"))
		{			
			String disbDate = (String) incomingRequest.get("DisbLine_disbDate");
			queryString.append(" AND disbline.disbDate = '" + disbDate + "'");
		}
		if(incomingRequest.containsKey("DisbLine_assetCode"))
		{			
			String assetCode = (String) incomingRequest.get("DisbLine_assetCode");
			queryString.append(" AND disbline.assetCode = '" + assetCode + "'");
		}
		if(incomingRequest.containsKey("DisbLine_icLineHistory"))
		{			
			String icLineHistory = (String) incomingRequest.get("DisbLine_icLineHistory");
			queryString.append(" AND disbline.icLineHistory = '" + icLineHistory + "'");
		}
		if(incomingRequest.containsKey("DisbLine_description"))
		{			
			String description = (String) incomingRequest.get("DisbLine_description");
			queryString.append(" AND disbline.description = '" + description + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}