package com.tsa.puridiom.recentrequisition.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecentRequisitionRetrieveHeaderBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from RecentRequisition as recentrequisition, RequisitionHeader as requisitionheader where 1=1 ");
		if(incomingRequest.containsKey("RecentRequisition_requisitionerCode"))
		{
			String requisitionerCode = (String) incomingRequest.get("RecentRequisition_requisitionerCode");
			queryString.append(" AND recentrequisition.id.requisitionerCode = '" + requisitionerCode + "'");
		}
		if(incomingRequest.containsKey("RecentRequisition_icReqHeader"))
		{
			String icReqHeader = (String) incomingRequest.get("RecentRequisition_icReqHeader");
			queryString.append(" AND recentrequisition.id.icReqHeader = '" + icReqHeader + "'");
		}
		if(incomingRequest.containsKey("RecentRequisition_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("RecentRequisition_dateEntered");
			queryString.append(" AND recentrequisition.dateEntered = '" + dateEntered + "'");
		}
		if (incomingRequest.containsKey("msrOnly")) {

			String msrOnly = (String) incomingRequest.get("msrOnly");
			if (msrOnly == null) msrOnly = "N" ;
			if (msrOnly.equalsIgnoreCase("Y")) {
				queryString.append(" AND requisitionheader.requisitionType = 'M'");
			} else {
				queryString.append(" AND requisitionheader.requisitionType <> 'M'");
			}
		} else {
			queryString.append(" AND requisitionheader.requisitionType <> 'M'");
		}

		queryString.append(" AND recentrequisition.id.icReqHeader = requisitionheader.icReqHeader");
		queryString.append(" ORDER BY recentrequisition.id.icReqHeader DESC");

		List list = dbs.query(queryString.toString(), new Object[] {}, 11) ;
		List	result = new ArrayList();

		if (list != null && list.size() > 0) {
		    for (int i=0; i < list.size(); i++ ) {
		        Object obj[] = (Object[])list.get(i);
		        RequisitionHeader requisitionHeader = (RequisitionHeader) obj[1];
		        result.add(requisitionHeader);
		    }
		}

		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}