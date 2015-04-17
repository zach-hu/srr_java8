/*
 * Created on Nov 17, 2004 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisition.noncapital.tasks;

import java.math.BigDecimal;


import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo TODO To change the template for this generated type comment go
 *         to Window - Preferences - Java - Code Style - Code Templates
 */
public class AccountListFromRequisitionHeader extends Task
{

	/*
	 * (non-Javadoc)
	 *
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");

			List accountList = (List) reqHeader.getAccountList();

			if (accountList != null && accountList.size() > 0)
			{
				for (int i = 0; i < accountList.size(); i++)
				{
					Account account = (Account) accountList.get(i);
					BigDecimal amount = account.getAllocAmount();

					if (amount.compareTo(new BigDecimal(0)) == 0)
					{
						BigDecimal icHeader = account.getComp_id().getIcHeader();
						BigDecimal icLine = account.getComp_id().getIcLine();
						BigDecimal sequence = account.getComp_id().getSequence();

					}
				}
			}
			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}
}
