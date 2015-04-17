package com.tsa.puridiom.budgettran.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BudgetTranSetValuesPK
{
	public void setValues(Map incomingRequest, BudgetTran budgettran ) throws Exception
	{
		try
		{
			String tranIdString = (String) incomingRequest.get("BudgetTran_tranId");
			BigDecimal tranId = new BigDecimal ( tranIdString );
			budgettran.setTranId(tranId);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}