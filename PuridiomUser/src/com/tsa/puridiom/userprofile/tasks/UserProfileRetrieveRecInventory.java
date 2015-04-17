package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import java.util.List;
import java.util.Map;

public class UserProfileRetrieveRecInventory extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String status = "03";
			String recStatus = DocumentStatus.RCV_PARTIALLYRECEIVED;

			String queryString = "select UserProfile.userId, count(ReceiptLine.icRecHeader) " +
				"from ReceiptLine as ReceiptLine, PoHeader as PoHeader, PoLine as PoLine, UserProfile as UserProfile " +
				"where UserProfile.status <> ? " +
				"and ReceiptLine.icPoHeader = PoHeader.icPoHeader and PoHeader.icPoHeader = PoLine.icPoHeader " +
				"and ReceiptLine.inventoryStatus = ? " +
				"group by UserProfile.userId order by UserProfile.userId ";

			List resultList = dbs.query(queryString,
				new Object[] {status, recStatus},
				new Type[] {Hibernate.STRING, Hibernate.STRING});

			incomingRequest.put("UserPreference_property", "RECPENDINGONINVENTORYCNT");
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
