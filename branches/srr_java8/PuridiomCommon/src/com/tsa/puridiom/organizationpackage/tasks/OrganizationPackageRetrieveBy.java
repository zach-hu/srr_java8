package com.tsa.puridiom.organizationpackage.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class OrganizationPackageRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		boolean closeDBS = false;
		
		if (dbs == null || !dbs.getSessionOrganizationId().equalsIgnoreCase("host")) {
			//	Always use host database configuration for this table
			dbs = new DBSession("host") ;
			closeDBS = true;
		}

		StringBuffer queryString = new StringBuffer("from OrganizationPackage as organizationpackage where 1=1 ");
		if(incomingRequest.containsKey("OrganizationPackage_icOrgPackage"))
		{			
			String icOrgPackage = (String) incomingRequest.get("OrganizationPackage_icOrgPackage");
			queryString.append(" AND organizationpackage.icOrgPackage = '" + icOrgPackage + "'");
		}
		if(incomingRequest.containsKey("OrganizationPackage_organizationId"))
		{			
			String organizationId = (String) incomingRequest.get("OrganizationPackage_organizationId");
			queryString.append(" AND organizationpackage.organizationId = '" + organizationId + "'");
		}
		if(incomingRequest.containsKey("OrganizationPackage_icPackage"))
		{			
			String icPackage = (String) incomingRequest.get("OrganizationPackage_icPackage");
			queryString.append(" AND organizationpackage.icPackage = '" + icPackage + "'");
		}
		if(incomingRequest.containsKey("OrganizationPackage_packageType"))
		{			
			String packageType = (String) incomingRequest.get("OrganizationPackage_packageType");
			queryString.append(" AND organizationpackage.packageType = '" + packageType + "'");
		}
		if(incomingRequest.containsKey("OrganizationPackage_unitPrice"))
		{			
			String unitPrice = (String) incomingRequest.get("OrganizationPackage_unitPrice");
			queryString.append(" AND organizationpackage.unitPrice = '" + unitPrice + "'");
		}
		if(incomingRequest.containsKey("OrganizationPackage_quantity"))
		{			
			String quantity = (String) incomingRequest.get("OrganizationPackage_quantity");
			queryString.append(" AND organizationpackage.quantity = '" + quantity + "'");
		}
		if(incomingRequest.containsKey("OrganizationPackage_total"))
		{			
			String total = (String) incomingRequest.get("OrganizationPackage_total");
			queryString.append(" AND organizationpackage.total = '" + total + "'");
		}
		if(incomingRequest.containsKey("OrganizationPackage_buyerCount"))
		{			
			String buyerCount = (String) incomingRequest.get("OrganizationPackage_buyerCount");
			queryString.append(" AND organizationpackage.buyerCount = '" + buyerCount + "'");
		}
		if(incomingRequest.containsKey("OrganizationPackage_requisitionerCount"))
		{			
			String requisitionerCount = (String) incomingRequest.get("OrganizationPackage_requisitionerCount");
			queryString.append(" AND organizationpackage.requisitionerCount = '" + requisitionerCount + "'");
		}
		if(incomingRequest.containsKey("OrganizationPackage_purchasedBy"))
		{			
			String purchasedBy = (String) incomingRequest.get("OrganizationPackage_purchasedBy");
			queryString.append(" AND organizationpackage.purchasedBy = '" + purchasedBy + "'");
		}
		if(incomingRequest.containsKey("OrganizationPackage_datePurchased"))
		{			
			String datePurchased = (String) incomingRequest.get("OrganizationPackage_datePurchased");
			queryString.append(" AND organizationpackage.datePurchased = '" + datePurchased + "'");
		}
		if(incomingRequest.containsKey("OrganizationPackage_datePaid"))
		{			
			String datePaid = (String) incomingRequest.get("OrganizationPackage_datePaid");
			queryString.append(" AND organizationpackage.datePaid = '" + datePaid + "'");
		}
		if(incomingRequest.containsKey("OrganizationPackage_transactionId"))
		{			
			String transactionId = (String) incomingRequest.get("OrganizationPackage_transactionId");
			queryString.append(" AND organizationpackage.transactionId = '" + transactionId + "'");
		}
		if(incomingRequest.containsKey("OrganizationPackage_status"))
		{			
			String status = (String) incomingRequest.get("OrganizationPackage_status");
			queryString.append(" AND organizationpackage.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("OrganizationPackage_dateActive"))
		{			
			String dateActive = (String) incomingRequest.get("OrganizationPackage_dateActive");
			queryString.append(" AND organizationpackage.dateActive = '" + dateActive + "'");
		}
		if(incomingRequest.containsKey("OrganizationPackage_dateExpires"))
		{			
			String dateExpires = (String) incomingRequest.get("OrganizationPackage_dateExpires");
			queryString.append(" AND organizationpackage.dateExpires = '" + dateExpires + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		
		if (closeDBS) {
			dbs.close();
		}

		return result ;
	}
}
