package com.tsa.puridiom.poheader.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class BlanketRetrieveByNumber extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		String poNumber = "";

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			poNumber = (String) incomingRequest.get("PoHeader_poNumber");
			if(Utility.isEmpty(poNumber))
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("Blanket [" + poNumber + "] was not found. AutoRelease Process");
			}
			String sql = "from PoHeader po where po.poNumber = ? and " +
            "(po.poType = 'BO' or po.poType = 'SB' or po.poType = 'DO') and po.lastRevision = 'C' and " +
            "(po.status >= 3000 and po.status < 9000)";
			List resultList = dbs.query(sql, new Object[] {poNumber } , new Type[] { Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Blanket [" + poNumber + "] was not found. AutoRelease Process", e);
		}
		return result;
	}
}