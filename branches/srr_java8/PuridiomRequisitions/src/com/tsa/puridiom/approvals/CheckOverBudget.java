package com.tsa.puridiom.approvals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class CheckOverBudget extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			List accountList = (List) incomingRequest.get("accountList");
			List accountCar = new ArrayList() ;
			List balanceCar = new ArrayList() ;
			int aux = 0;
			for (int i = 0; i < accountList.size(); i++)
			{
				for (int j = 0; j < accountList.size(); j++)
				{
					Account account = (Account) accountList.get(j);
					String  fld4 = HiltonUtility.ckNull(account.getFld4());
					String  balance = "Balance" + j + fld4;
					String accountStr = HiltonUtility.ckNull((String) incomingRequest.get("account" + i + fld4));
					if(accountStr.equalsIgnoreCase(fld4) && aux == 0)
					{
						String balanceAmount = HiltonUtility.ckNull((String) incomingRequest.get(balance));
						accountCar.add(accountStr);
						balanceCar.add(balanceAmount);
						aux++;
					}
				}
				aux = 0;
			}
			for(int ii = 0; ii < accountCar.size(); ii++)
			{
			    String car = (String) accountCar.get(ii);
			    for(int ji = ii+1; ji < accountCar.size(); ji++ )
			    {
			    	String carAux = (String) accountCar.get(ji);
			        if (car.equalsIgnoreCase(carAux))
			        {
			        	accountCar.remove(ji);
			        }
			    }
			}
			incomingRequest.put("accountCar", accountCar);
			incomingRequest.put("balanceCar", balanceCar);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("An Error occurred at AppRuleAddToCatalog", e);
		}
		return result;
	}
}