package com.tsa.puridiom.docattachment.tasks;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class DocAttachmentRetrieveListByLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List ret = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			String icHeaderString = requisitionHeader.getIcReqHeader().toString();

			if (Utility.isEmpty(icHeaderString))
			{
				throw new Exception ("DocAttachment icHeader can not be empty.");
			}

			String queryString = "from DocAttachment as d where d.id.icHeader = ?";
			BigDecimal icHeader = new BigDecimal(icHeaderString);

			ret = dbs.query(queryString, icHeader, Hibernate.BIG_DECIMAL) ;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Attachments could not be Retrieved.", e);
		}
		return ret ;

	}
}
