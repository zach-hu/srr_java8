package com.tsa.puridiom.userpreference.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class UserPreferenceDeleteUserIdDocumentCount extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;


			String property = HiltonUtility.ckNull((String)incomingRequest.get("UserPreference_property"));
			if(property == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("Property was not found.");
			}

	        String queryString = "from UserPreference as UserPreference where UserPreference.id.property = ?" ;

			dbs.delete(queryString, property, Hibernate.STRING) ;

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Error Rebuilding Routing List. ", e);
		}

		return object	;
	}
}
