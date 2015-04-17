/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.reviewfinalize.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.*;
import java.math.*;
import org.hibernate.*;

/**
 * @author Administrator
 */
public class ReviewFinalizeDeleteByHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
			Map incomingRequest = (Map)object;
			try
			{
		        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

		        String queryString = "from ReviewFinalize as a where a.id.icHeader = ?";

				String icHeader = (String)incomingRequest.get("ReviewFinalize_icHeader");
				BigDecimal bdIcHeader = new BigDecimal(icHeader) ;

				dbs.delete(queryString,bdIcHeader,Hibernate.BIG_DECIMAL);

				this.setStatus(dbs.getStatus()) ;
			}
			catch (Exception e)
			{
				this.setStatus(Status.FAILED);
			}
		return object ;
	}
}
