package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;

public class PoValidateDailyReleaseLimit extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;

		try
		{
			Map incomingRequest = (Map) object;

			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
			String userTimeZone = (String) incomingRequest.get("userTimeZone");

			Date s_now = Dates.getCurrentDate(userTimeZone);

			String queryString = "SELECT SUM(poHeader.total) FROM PoHeader AS poHeader " +
				"WHERE poHeader.lastRevision = 'C' " +
				"AND  poHeader.owner = '" + userId + "' " +
				"AND  poHeader.poType IN ('RO','SR','DR') " +
				"AND (poHeader.status > '" + DocumentStatus.PO_APPROVING + "' AND poHeader.status < '" + DocumentStatus.CANCELLED +  "') " +
				"AND  poHeader.appDate >= ?";

			List resultList = dbs.query(queryString, new Object[] {s_now}, new Type[] {Hibernate.DATE});

			BigDecimal totalReleased = new BigDecimal(0);

			if (resultList != null && resultList.size() > 0)
			{
				totalReleased = (BigDecimal)resultList.get(0);
				if (totalReleased == null) {
					totalReleased = new BigDecimal(0);
				}
			}

			String userDailyReleaseLimit = PropertiesManager.getInstance(organizationId).getProperty("PO OPTIONS", "UserDailyReleaseLimit", "0.00");
			BigDecimal dailyReleaseLimit = new BigDecimal(userDailyReleaseLimit);

			String dailyReleaseLimitValid = "N";

			if (totalReleased.compareTo(dailyReleaseLimit) < 0)
			{
				dailyReleaseLimitValid = "Y";
			}

			String message = "User Daily Release Total: " +
				HiltonUtility.getFormattedDollar(totalReleased, organizationId).toString() + " >= " +
				HiltonUtility.getFormattedDollar(userDailyReleaseLimit, organizationId).toString() +
				" :User Daily Release Limit";

			incomingRequest.put("dailyReleaseLimitMessage", message);
			incomingRequest.put("dailyReleaseLimitValid", dailyReleaseLimitValid);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}
