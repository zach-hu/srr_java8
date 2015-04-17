/**
 * Created on Feb 19, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoForwardGetUserWarrantAmount.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.poheader.tasks.UserErrors;
import com.tsa.puridiom.usermanager.*;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoForwardGetUserWarrantAmount extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String poOption = null;
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			BigDecimal currencyFactor = poHeader.getCurrencyFactor();
			BigDecimal poAmount = (BigDecimal)incomingRequest.get("poAmount");
			
			if (currencyFactor.compareTo(new BigDecimal(0)) == 0) {
			    currencyFactor = new BigDecimal(1);
			}
			
			BigDecimal warrantPoAmt = poAmount.divide(currencyFactor, 2, BigDecimal.ROUND_UP);
			String	userId = (String) incomingRequest.get("userId");
			String	organizationId = (String) incomingRequest.get("organizationId");
			BigDecimal warrantUserAmt = UserManager.getInstance().getUser(organizationId, userId).getWarrantAmount();
			UserErrors errors = (UserErrors)incomingRequest.get("userErrors");
			if(errors == null){	errors = new UserErrors();	}
			if(warrantUserAmt != null)
			{	
				if(warrantPoAmt.compareTo(warrantUserAmt) > 0)
				{
					poOption = "F";
				}
				else
				{	
					errors.addError("poOptions", PoErrors.USERINPUT);
				}
			}
			else
			{
				errors.addError("poOptions", PoErrors.USERINPUT);
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return poOption;
	}
}
