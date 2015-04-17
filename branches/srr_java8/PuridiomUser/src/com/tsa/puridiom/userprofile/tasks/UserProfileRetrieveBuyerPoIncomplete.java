package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import java.util.List;
import java.util.Map;

public class UserProfileRetrieveBuyerPoIncomplete extends Task
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
			String poNumber = "N/A";

			String queryString = "select UserProfile.userId, count(PoHeader.icPoHeader) " +
				"from UserProfile as UserProfile, PoHeader as PoHeader " +
				"where UserProfile.buyer = ? and UserProfile.status <> ? and PoHeader.poNumber <> ? " +
				"and PoHeader.status = ? and (PoHeader.owner = UserProfile.userId or PoHeader.buyerCode = UserProfile.userId) " +
				"group by UserProfile.userId order by UserProfile.userId";
			List resultList = dbs.query(queryString,
				new Object[] {buyer, status, poNumber, DocumentStatus.PO_INPROGRESS},
				new Type[] {Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING});

			incomingRequest.put("UserPreference_property", "POINCOMPLETECNT");
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
