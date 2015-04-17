package com.tsa.puridiom.budgettran.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

public class BudgetTranLog extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			String		tranId = ukg.getUniqueKey().toString() ;
			String owner = (String) incomingRequest.get("userId") ;

			incomingRequest.put("BudgetTran_TranId", tranId);
			incomingRequest.put("BudgetTran", owner) ;

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("budgettran-add.xml");
			process.executeProcess(incomingRequest);
			this.status = process.getStatus() ;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}