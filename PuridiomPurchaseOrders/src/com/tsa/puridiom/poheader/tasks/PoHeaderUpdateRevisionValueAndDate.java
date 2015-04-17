package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoHeaderUpdateRevisionValueAndDate extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			BigDecimal icPoHeader = (BigDecimal) incomingRequest.get("originalIc");
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			BigDecimal poTotal = new BigDecimal(0);

			String queryString = "from PoHeader as poHeader where poHeader.icPoHeader = ?";
			List resultList = dbs.query(queryString, new Object[] {icPoHeader } , new Type[] {Hibernate.BIG_DECIMAL}) ;
			
			if(resultList != null && resultList.size() > 0){
				PoHeader poHeader = (PoHeader)resultList.get(0);
				poTotal = poHeader.getTotal();
			}
			
			String sql = "Update po_header set revision_value = ?, revision_date = null where ic_po_header = ?";
			Object [] args = {poTotal, icPoHeader};
			Integer [] types = {Types.DECIMAL, Types.DECIMAL};
			this.setStatus(dbs.sqlUpdate(sql, args, types));
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}

}
