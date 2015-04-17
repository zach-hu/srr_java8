/**
 *
 */
package com.tsa.puridiom.approvallink.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Johnny Zapana
 */
public class ApprovalLinkRetrieve extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			String icApprovalLinkString = (String) incomingRequest.get("ApprovalLink_icApprovalLink");
			BigDecimal icApprovalLink = new BigDecimal(icApprovalLinkString);

			String queryString = "from ApprovalLink as approvalLink where approvalLink.icApprovalLink = ? ";
			List resultList = dbs.query(queryString, icApprovalLink, Hibernate.BIG_DECIMAL);

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}

			this.setStatus(dbs.getStatus());
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}