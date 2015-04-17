package com.tsa.puridiom.disbheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class DisbHeaderRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from DisbHeader as disbheader where 1=1 ");
		if(incomingRequest.containsKey("DisbHeader_icDsbHeader"))
		{			
			String icDsbHeader = (String) incomingRequest.get("DisbHeader_icDsbHeader");
			queryString.append(" AND disbheader.id.icDsbHeader = '" + icDsbHeader + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_disbNumber"))
		{			
			String disbNumber = (String) incomingRequest.get("DisbHeader_disbNumber");
			queryString.append(" AND disbheader.disbNumber = '" + disbNumber + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_disbDate"))
		{			
			String disbDate = (String) incomingRequest.get("DisbHeader_disbDate");
			queryString.append(" AND disbheader.disbDate = '" + disbDate + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_status"))
		{			
			String status = (String) incomingRequest.get("DisbHeader_status");
			queryString.append(" AND disbheader.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_internalComments"))
		{			
			String internalComments = (String) incomingRequest.get("DisbHeader_internalComments");
			queryString.append(" AND disbheader.internalComments = '" + internalComments + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_fiscalYear"))
		{			
			String fiscalYear = (String) incomingRequest.get("DisbHeader_fiscalYear");
			queryString.append(" AND disbheader.fiscalYear = '" + fiscalYear + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_owner"))
		{			
			String owner = (String) incomingRequest.get("DisbHeader_owner");
			queryString.append(" AND disbheader.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("DisbHeader_dateEntered");
			queryString.append(" AND disbheader.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_itemLocation"))
		{			
			String itemLocation = (String) incomingRequest.get("DisbHeader_itemLocation");
			queryString.append(" AND disbheader.itemLocation = '" + itemLocation + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_icAccount"))
		{			
			String icAccount = (String) incomingRequest.get("DisbHeader_icAccount");
			queryString.append(" AND disbheader.icAccount = '" + icAccount + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_subtotal"))
		{			
			String subtotal = (String) incomingRequest.get("DisbHeader_subtotal");
			queryString.append(" AND disbheader.subtotal = '" + subtotal + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_approved"))
		{			
			String approved = (String) incomingRequest.get("DisbHeader_approved");
			queryString.append(" AND disbheader.approved = '" + approved + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_appBy"))
		{			
			String appBy = (String) incomingRequest.get("DisbHeader_appBy");
			queryString.append(" AND disbheader.appBy = '" + appBy + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_appDate"))
		{			
			String appDate = (String) incomingRequest.get("DisbHeader_appDate");
			queryString.append(" AND disbheader.appDate = '" + appDate + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_appSigned"))
		{			
			String appSigned = (String) incomingRequest.get("DisbHeader_appSigned");
			queryString.append(" AND disbheader.appSigned = '" + appSigned + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_lastChgBy"))
		{			
			String lastChgBy = (String) incomingRequest.get("DisbHeader_lastChgBy");
			queryString.append(" AND disbheader.lastChgBy = '" + lastChgBy + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_lastChgDate"))
		{			
			String lastChgDate = (String) incomingRequest.get("DisbHeader_lastChgDate");
			queryString.append(" AND disbheader.lastChgDate = '" + lastChgDate + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_disbType"))
		{			
			String disbType = (String) incomingRequest.get("DisbHeader_disbType");
			queryString.append(" AND disbheader.disbType = '" + disbType + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_icReqHeader"))
		{			
			String icReqHeader = (String) incomingRequest.get("DisbHeader_icReqHeader");
			queryString.append(" AND disbheader.icReqHeader = '" + icReqHeader + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_requisitionNumber"))
		{			
			String requisitionNumber = (String) incomingRequest.get("DisbHeader_requisitionNumber");
			queryString.append(" AND disbheader.requisitionNumber = '" + requisitionNumber + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_requisitionerCode"))
		{			
			String requisitionerCode = (String) incomingRequest.get("DisbHeader_requisitionerCode");
			queryString.append(" AND disbheader.requisitionerCode = '" + requisitionerCode + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_printed"))
		{			
			String printed = (String) incomingRequest.get("DisbHeader_printed");
			queryString.append(" AND disbheader.printed = '" + printed + "'");
		}
		if(incomingRequest.containsKey("DisbHeader_icHeaderHistory"))
		{			
			String icHeaderHistory = (String) incomingRequest.get("DisbHeader_icHeaderHistory");
			queryString.append(" AND disbheader.icHeaderHistory = '" + icHeaderHistory + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}