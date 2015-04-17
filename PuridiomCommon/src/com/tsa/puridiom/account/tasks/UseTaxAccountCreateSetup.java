package com.tsa.puridiom.account.tasks;

import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.Map;

public class UseTaxAccountCreateSetup extends Task{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		String	userId = (String) incomingRequest.get("userId") ;

		// Create new ic code
		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		incomingRequest.put("Account_icHeader",ukg.getUniqueKey().toString());
		incomingRequest.put("Account_icLine", "0");

		return null ;
	}

}