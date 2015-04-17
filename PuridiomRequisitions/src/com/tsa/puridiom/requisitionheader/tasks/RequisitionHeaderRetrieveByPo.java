package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.processengine.Status;
import java.util.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Kathleen
 */
public class RequisitionHeaderRetrieveByPo extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

			if (poHeader != null)
			{
				String icReqHeaderString = poHeader.getIcReqHeader().toString();

				if (Utility.isEmpty(icReqHeaderString))
				{
					throw new Exception("RequisitionHeader_icReqHeader cannot be empty.  Requisition header could not be retrieved.");
				}
				BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );

				String queryString = "from RequisitionHeader as RequisitionHeader where RequisitionHeader.icReqHeader = ? ";
				List resultList = dbs.query(queryString, new Object[] {icReqHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

				if (resultList != null && resultList.size() > 0)
				{
					result = resultList.get(0);
				}
				this.setStatus(dbs.getStatus());
			}
			else
				this.setStatus(Status.FAILED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}