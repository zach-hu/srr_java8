package com.tsa.puridiom.xrefcombo.tasks;

import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsa.puridiom.entity.*;

public class XrefComboDelete extends Task{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		XrefCombo xrefCombo = (XrefCombo)incomingRequest.get("xrefCombo");

		try
		{
			if(xrefCombo == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("XrefCombo entity was not found!");
			}

			dbs.delete(xrefCombo);
			this.setStatus(dbs.getStatus()) ;
			return xrefCombo ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
	}
}