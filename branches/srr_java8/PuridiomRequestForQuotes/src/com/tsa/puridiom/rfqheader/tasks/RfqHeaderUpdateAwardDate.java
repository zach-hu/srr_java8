/**
 * Created on Mar 2, 2004
 * @author renzo
 * project: HiltonRequestForQuotes
 * com.tsa.puridiom.rfqheader.tasks.RfqHeaderUpdateAwardDate.java
 *
 */
package com.tsa.puridiom.rfqheader.tasks;

import java.sql.Types;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;

public class RfqHeaderUpdateAwardDate extends Task
{
	
	/* 
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String vendor = (String)incomingRequest.get("vendor");
			String icRfqHeader = (String)incomingRequest.get("RfqHeader_icRfqHeader");
            String userTimeZone = (String)incomingRequest.get("userTimeZone");
			String today = Dates.today("", userTimeZone);
			String sql = "UPDATE rfq_header SET award_date = ?, vendor_awarded = ? WHERE ic_rfq_header = ?";
			Object [] args = {today, vendor, icRfqHeader};
			Integer [] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
			
			this.setStatus(dbs.sqlUpdate(sql, args, types));
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
