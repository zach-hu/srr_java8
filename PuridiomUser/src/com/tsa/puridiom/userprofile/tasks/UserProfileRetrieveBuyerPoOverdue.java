package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserProfileRetrieveBuyerPoOverdue extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String organizationId = (String)incomingRequest.get("organizationId");

			String buyer = "Y";
			String status = "03";
			String poNumber = "N/A";

			String queryString = "select UserProfile.userId, count(PoHeader.icPoHeader) " +
				"from UserProfile as UserProfile, PoHeader as PoHeader " +
				"where UserProfile.buyer = ? and UserProfile.status <> ? and PoHeader.poNumber <> ? " +
				"and PoHeader.status > ? and PoHeader.status < ? and PoHeader.requiredDate < ? ";
			if (PropertiesManager.getInstance(organizationId).getProperty("PO OPTIONS", "FILTER DIFFERENT AMENDED","N").equalsIgnoreCase("Y")) {
				queryString = queryString + "and PoHeader.status <> ? ";
			}
			queryString = queryString + "and (PoHeader.owner = UserProfile.userId or PoHeader.buyerCode = UserProfile.userId) " +
				"group by UserProfile.userId order by UserProfile.userId";

			List resultList = new ArrayList();
			if (PropertiesManager.getInstance(organizationId).getProperty("PO OPTIONS", "FILTER DIFFERENT AMENDED","N").equalsIgnoreCase("Y"))
			{
				resultList = dbs.query(queryString,
					new Object[] {buyer, status, poNumber, DocumentStatus.PO_APPROVING, DocumentStatus.RCV_RECEIVED, new Date(), DocumentStatus.PO_AMENDED},
					new Type[] {Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.DATE, Hibernate.STRING});
			}
			else
			{
				resultList = dbs.query(queryString,
					new Object[] {buyer, status, poNumber, DocumentStatus.PO_APPROVING, DocumentStatus.RCV_RECEIVED, new Date()},
					new Type[] {Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.DATE});
			}

			incomingRequest.put("UserPreference_property", "POOVERDUECNT");
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
