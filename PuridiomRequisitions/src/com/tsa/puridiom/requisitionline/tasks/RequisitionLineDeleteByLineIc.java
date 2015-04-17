/**
 * Created on April 19, 2006
 * project: HiltonRequisitions
 * com.tsa.puridiom.requisitionline.tasks.RequisitionLineDeleteByLineIc.java
 *
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import org.hibernate.*;
import org.hibernate.type.Type;

public class RequisitionLineDeleteByLineIc extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest =(Map)object;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String queryString = "from RequisitionLine as RequisitionLine where RequisitionLine.icReqLine = ?";
			String icLine = (String)incomingRequest.get("deleteLine_lineIc");

			this.setStatus(dbs.delete(queryString, new Object[] {icLine}, new Type[] {Hibernate.STRING}));
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
