/**
 * Created on Mar 9, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invformpart.tasks.InvFormPartDeleteByItem.java
 *
 */
package com.tsa.puridiom.invformpart.tasks;

import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import org.hibernate.*;
import org.hibernate.type.Type;

public class InvFormPartDeleteByItem extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest =(Map)object;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String queryString = "from InvFormPart as InvFormPart where InvFormPart.id.itemNumber = ?";
			String itemNumber = (String)incomingRequest.get("InvFormPart_itemNumber");
			
			this.setStatus(dbs.delete(queryString, new Object[] {itemNumber}, new Type[] {Hibernate.STRING}));
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
