package com.tsa.puridiom.xrefcombo.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;

public class XrefComboCheckId extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String icXrefString = (String) incomingRequest.get("XrefCombo_icXref");

			if(Utility.isEmpty(icXrefString)) {
				this.setStatus(Status.FAILED);
				throw new TsaException("An Ic XrefCombo is necessary to retrieve an Auto Accounting item");
			}
			else {
				this.setStatus(dbs.getStatus()) ;
			}
		}

		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}