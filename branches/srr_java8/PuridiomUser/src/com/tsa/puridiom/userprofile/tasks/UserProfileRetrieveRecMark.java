package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import java.util.List;
import java.util.Map;

public class UserProfileRetrieveRecMark extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String status = "03";
			String recNumber = "N/A";
			String recStatus = DocumentStatus.RCV_STEP_2;

			String queryString = "select UserProfile.userId, count(ReceiptHeader.icRecHeader) " +
				"from UserProfile as UserProfile, ReceiptHeader as ReceiptHeader " +
				"where UserProfile.status <> ? and ReceiptHeader.receiptNumber <> ? " +
				"and ReceiptHeader.receiptNumber is not null and ReceiptHeader.receiptNumber <> 'null' " +
				"and ReceiptHeader.forwardTo is not null and ReceiptHeader.forwardTo <> 'null' " +
				"and ReceiptHeader.icRecHeader in (Select distinct ReceiptLine.icRecHeader from ReceiptLine as ReceiptLine where ReceiptLine.status = ?) " +
				"group by UserProfile.userId order by UserProfile.userId ";

			List resultList = dbs.query(queryString,
				new Object[] {status, recNumber, recStatus},
				new Type[] {Hibernate.STRING, Hibernate.STRING, Hibernate.STRING});

			incomingRequest.put("UserPreference_property", "RECINMARKCNT");
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
