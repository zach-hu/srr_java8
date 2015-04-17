/*
 * Created on Sep 15, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.po.tasks;.PoUpdateBlanketLastRelease.java
 * 
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoUpdateBlanketLastRelease extends Task
{
    public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			BigDecimal currentRelease = (BigDecimal)incomingRequest.get("currentRelease");
			BigDecimal nextRelease = currentRelease.add(new BigDecimal(1));
			
			BigDecimal blanketIc = (BigDecimal)incomingRequest.get("blanketIc");
			if(blanketIc == null)
			{
			    this.setStatus(Status.FAILED);
				throw new TsaException("Blanket Order could not be Found!");
			}
			else
			{
			    String sql = "Update po_header set last_release = ? where ic_po_header = ?";
			    DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			    ret = nextRelease.toString();
				
			    Object [] args = {nextRelease.toString(), blanketIc.toString()};
			    Integer [] types = {Types.VARCHAR, Types.VARCHAR};
			    
			    this.setStatus(dbs.sqlUpdate(sql, args, types));
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}
}
