package com.tsa.puridiom.poheader.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import java.util.List;
import java.util.Map;

public class PoHeaderVerifyDuplicate extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");

			String verifyDuplicatePo = "N";
			String requisitionHeader = "";
			if (poHeader != null)
				requisitionHeader = poHeader.getRequisitionNumber();

			String queryString = "from PoHeader as PoHeader where PoHeader.requisitionNumber = ? and PoHeader.poNumber != 'N/A'";
			List resultList = dbs.query(queryString, new Object[] {requisitionHeader} , new Type[] { Hibernate.STRING});

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
				verifyDuplicatePo = "Y";
			}
			incomingRequest.put("verifyDuplicatePo", verifyDuplicatePo);
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