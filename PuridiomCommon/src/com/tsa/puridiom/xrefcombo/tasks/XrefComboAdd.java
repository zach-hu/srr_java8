package com.tsa.puridiom.xrefcombo.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class XrefComboAdd extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			XrefCombo xrefCombo = (XrefCombo)incomingRequest.get("xrefCombo");
			if (xrefCombo == null)
			{
				throw new Exception ("XrefCombo was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(xrefCombo);

			result = xrefCombo;
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