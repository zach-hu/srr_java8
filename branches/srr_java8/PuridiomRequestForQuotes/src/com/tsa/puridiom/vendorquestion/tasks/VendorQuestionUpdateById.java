package com.tsa.puridiom.vendorquestion.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class VendorQuestionUpdateById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			VendorQuestion vendorQuestion = (VendorQuestion)incomingRequest.get("vendorQuestion");
			if (vendorQuestion == null)
			{
				throw new Exception ("VendorQuestion was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(vendorQuestion);
		
			result = vendorQuestion;
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