package com.tsa.puridiom.disbursement.tasks;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;

public class DisbHeaderRetrieveItemUsage extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String itemNumber = (String) incomingRequest.get("InvItem_itemNumber");
			String itemLocation = (String)incomingRequest.get("InvItem_invLocation");

			String queryString = "Select disbLine.quantity from DisbHeader as disbHeader, DisbLine as disbLine where disbLine.itemNumber = ? AND disbHeader.status <> '99' " +
					" AND disbLine.itemLocation = ? AND disbHeader.disbDate >= ? AND disbHeader.disbDate <= ?";

			Date today = Dates.getDate(Dates.today("", (String) incomingRequest.get("userTimeZone")));
			Calendar cal =Calendar.getInstance();
			cal.setTime(today);
			cal.set(Calendar.HOUR, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);

			Date last = Dates.add(today, -365);
			Calendar lastCal = Calendar.getInstance();
			lastCal.setTime(last);
			lastCal.set(Calendar.HOUR, 00);
			lastCal.set(Calendar.MINUTE, 00);
			lastCal.set(Calendar.SECOND, 00);

			result = dbs.query(queryString, new Object[] {itemNumber, itemLocation, lastCal.getTime(), cal.getTime() } , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.DATE, Hibernate.DATE}) ;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}

}