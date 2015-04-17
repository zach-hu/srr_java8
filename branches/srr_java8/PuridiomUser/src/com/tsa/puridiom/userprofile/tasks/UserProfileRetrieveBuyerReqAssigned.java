package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import java.util.List;
import java.util.Map;

public class UserProfileRetrieveBuyerReqAssigned extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String buyer = "Y";
			String status = "03";
			String requisitionNumber = "N/A";

			String queryString = "select UserProfile.userId, count(RequisitionHeader.icReqHeader) " +
				"from UserProfile as UserProfile, RequisitionHeader as RequisitionHeader " +
				"where UserProfile.buyer = ? and UserProfile.status <> ? and RequisitionHeader.requisitionNumber <> ? " +
				"and (RequisitionHeader.requisitionType = 'H' or RequisitionHeader.requisitionType = 'P' or RequisitionHeader.requisitionType = 'R' or RequisitionHeader.requisitionType = 'C' or RequisitionHeader.requisitionType = 'A' or RequisitionHeader.requisitionType = 'E')" +
				"and RequisitionHeader.status >= ? and RequisitionHeader.status <= ? " +
				"and (RequisitionHeader.assignedBuyer = UserProfile.userId or RequisitionHeader.icReqHeader in (select RequisitionLine.icReqHeader from RequisitionLine as RequisitionLine " +
				"where RequisitionLine.assignedBuyer = UserProfile.userId)) " +
				"group by UserProfile.userId order by UserProfile.userId";
			List resultList = dbs.query(queryString,
				new Object[] {buyer, status, requisitionNumber, DocumentStatus.REQ_APPROVED, DocumentStatus.PO_INPROGRESS},
				new Type[] {Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING});
			
			incomingRequest.put("UserPreference_property", "REQASSIGNEDCNT");
			result = resultList;

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
