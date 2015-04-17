package com.tsa.puridiom.poheader.tasks;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoHeaderUpdateSupercededBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");

			if (incomingRequest.containsKey("PoHeader_linkNextOrder_icPoHeader") && !((String)incomingRequest.get("PoHeader_linkNextOrder_icPoHeader")).equals("") )
			{
				String linkNextOrder_icPoHeader = (String) incomingRequest.get("PoHeader_linkNextOrder_icPoHeader");				

				String sql = "Update PO_HEADER set LINK_PRIOR_ORDER = ? where IC_PO_HEADER = ?";
				Object [] args = {poHeader.getDisplayPoNumber().toString(), linkNextOrder_icPoHeader};
				Integer [] types = {Types.VARCHAR, Types.VARCHAR};
				dbs.sqlUpdate(sql, args, types);
			}

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}